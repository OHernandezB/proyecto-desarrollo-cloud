const BASE_PRODUCTOS_URL = 'http://localhost:8082/api/productos';
const BASE_AUTH_URL = 'http://localhost:8081/api/auth';

export const MOCK_PRODUCTOS = [
  {
    id: 1,
    nombre: 'Teclado Mecánico RGB Apex Pro',
    descripcion: 'Switches magnéticos ajustables de 0.2mm a 3.8mm, estructura de aluminio aeronáutico e iluminación RGB por tecla.',
    precio: 149990,
    stock: 12,
    categoria: 'Teclados',
    imagenUrl: 'https://images.unsplash.com/photo-1587829741301-dc798b83add3?auto=format&fit=crop&w=800&q=80'
  },
  {
    id: 2,
    nombre: 'Mouse Gamer Wireless Viper V3 Pro',
    descripcion: 'Sensor óptico 35,000 DPI, peso ultraligero de 54g, tasa de sondeo de 8000Hz para esports.',
    precio: 99990,
    stock: 8,
    categoria: 'Periféricos',
    imagenUrl: 'https://images.unsplash.com/photo-1615663245857-ac93bb7c39e7?auto=format&fit=crop&w=800&q=80'
  },
  {
    id: 3,
    nombre: 'Monitor OLED 240Hz UltraWide 34"',
    descripcion: 'Pantalla curva 800R OLED, 0.03ms tiempo de respuesta, HDR True Black 400 y soporte G-Sync/FreeSync Premium.',
    precio: 749990,
    stock: 4,
    categoria: 'Pantallas',
    imagenUrl: 'https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?auto=format&fit=crop&w=800&q=80'
  },
  {
    id: 4,
    nombre: 'Audífonos Gaming 7.1 Surround Wireless',
    descripcion: 'Drivers de neodimio de 50mm, micrófono con cancelación de ruido por IA, autonomía de 50 horas.',
    precio: 119990,
    stock: 15,
    categoria: 'Periféricos',
    imagenUrl: 'https://images.unsplash.com/photo-1546435770-a3e426bf472b?auto=format&fit=crop&w=800&q=80'
  },
  {
    id: 5,
    nombre: 'Silla Gamer Ergonómica Titan Evo',
    descripcion: 'Soporte lumbar adaptativo de 4 vías, espuma de alta densidad lavable y reposabrazos 4D metálicos.',
    precio: 299990,
    stock: 6,
    categoria: 'Muebles Gamer',
    imagenUrl: 'https://images.unsplash.com/photo-1598550476439-6847785fcea6?auto=format&fit=crop&w=800&q=80'
  },
  {
    id: 6,
    nombre: 'Teclado 60% Custom Wireless Hotswap',
    descripcion: 'Conexión tri-modo (2.4Ghz, Bluetooth 5.1, Type-C), keycaps PBT doble inyección y amortiguación de silicona.',
    precio: 89990,
    stock: 20,
    categoria: 'Teclados',
    imagenUrl: 'https://images.unsplash.com/photo-1618384887929-16ec33fab9ef?auto=format&fit=crop&w=800&q=80'
  }
];

export const fetchProductosAPI = async () => {
  try {
    const res = await fetch(BASE_PRODUCTOS_URL);
    if (!res.ok) throw new Error('Error al conectar con Backend de Productos');
    const data = await res.json();
    return data.length > 0 ? data : MOCK_PRODUCTOS;
  } catch (error) {
    console.warn('API Productos no disponible, cargando datos mock:', error.message);
    return MOCK_PRODUCTOS;
  }
};

export const loginAPI = async (email, password) => {
  try {
    const res = await fetch(`${BASE_AUTH_URL}/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
    return await res.json();
  } catch (error) {
    console.warn('Backend Auth no respondió, usando simulación de Login local:', error.message);
    return {
      exito: true,
      mensaje: 'Inicio de sesión exitoso (Modo Demostración)',
      nombre: 'Gamer Player',
      email: email,
      rol: 'ROLE_USER',
      token: 'mock-jwt-token-demo'
    };
  }
};
