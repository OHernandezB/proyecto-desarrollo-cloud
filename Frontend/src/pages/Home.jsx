import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { fetchProductosAPI } from '../utils/api';
import { ProductCard } from '../components/ProductCard';
import { Sparkles, ArrowRight, Monitor, Keyboard, Mouse, Tv } from 'lucide-react';

export const Home = ({ searchTerm }) => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchProductosAPI().then((data) => {
      setProducts(data);
      setLoading(false);
    });
  }, []);

  const filteredProducts = products.filter((p) =>
    searchTerm
      ? p.nombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
        p.categoria.toLowerCase().includes(searchTerm.toLowerCase())
      : true
  );

  return (
    <div className="w-full space-y-14 md:space-y-20">
      
      {/* Hero Banner Gamer */}
      <section className="relative glass-card p-8 sm:p-12 md:p-14 overflow-hidden border-cyan-500/20 w-full">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-10 items-center w-full">
          
          {/* Texto e Información Hero */}
          <div className="lg:col-span-7 space-y-6 z-10">
            <div className="inline-flex items-center gap-2 badge-neon">
              <Sparkles className="w-3.5 h-3.5" /> Equipamiento de Alto Rendimiento
            </div>
            <h1 className="text-3xl sm:text-5xl lg:text-6xl font-extrabold text-white leading-tight">
              ELEVA TU <span className="bg-gradient-to-r from-[#00f2fe] via-[#4facfe] to-[#7f00ff] bg-clip-text text-transparent">SETUP GAMER</span>
            </h1>
            <p className="text-slate-300 text-sm sm:text-base leading-relaxed">
              Explora nuestra colección de periféricos pro, pantallas 240Hz, teclados mecánicos personalizados y accesorios diseñados para ganar.
            </p>
            <div className="flex flex-wrap items-center gap-4 pt-2">
              <Link to="/catalogo" className="btn-gamer-primary">
                Ver Catálogo <ArrowRight className="w-4 h-4" />
              </Link>
            </div>
          </div>

          {/* Previsualización Producto Hero */}
          <div className="lg:col-span-5 relative flex justify-center w-full">
            <div className="relative w-full aspect-video lg:aspect-[4/3] max-h-[380px] overflow-hidden rounded-2xl border border-white/10 shadow-2xl shadow-[#00f2fe]/20">
              <img
                src="https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?auto=format&fit=crop&w=800&q=80"
                alt="Setup Gamer"
                className="w-full h-full object-cover transform hover:scale-105 transition-transform duration-500"
              />
            </div>
          </div>

        </div>
      </section>

      {/* Categorías Principales */}
      <section className="space-y-6 w-full">
        <h2 className="text-xl sm:text-2xl font-bold text-white flex items-center gap-2">
          Categorías <span className="text-[#00f2fe]">Destacadas</span>
        </h2>
        <div className="grid grid-cols-2 sm:grid-cols-2 lg:grid-cols-4 gap-6 w-full">
          <Link to="/catalogo?cat=Teclados" className="glass-card p-6 flex flex-col items-center gap-4 text-center hover:border-[#00f2fe] group">
            <div className="p-4 rounded-2xl bg-[#00f2fe]/10 text-[#00f2fe] group-hover:scale-110 transition-transform">
              <Keyboard className="w-8 h-8" />
            </div>
            <span className="font-bold text-sm sm:text-base text-white">Teclados Mecánicos</span>
          </Link>
          
          <Link to="/catalogo?cat=Pantallas" className="glass-card p-6 flex flex-col items-center gap-4 text-center hover:border-[#00f2fe] group">
            <div className="p-4 rounded-2xl bg-[#7f00ff]/10 text-[#7f00ff] group-hover:scale-110 transition-transform">
              <Tv className="w-8 h-8" />
            </div>
            <span className="font-bold text-sm sm:text-base text-white">Pantallas & Monitores</span>
          </Link>

          <Link to="/catalogo?cat=Periféricos" className="glass-card p-6 flex flex-col items-center gap-4 text-center hover:border-[#00f2fe] group">
            <div className="p-4 rounded-2xl bg-[#f107a3]/10 text-[#f107a3] group-hover:scale-110 transition-transform">
              <Mouse className="w-8 h-8" />
            </div>
            <span className="font-bold text-sm sm:text-base text-white">Mouse & Audio</span>
          </Link>

          <Link to="/catalogo?cat=Muebles Gamer" className="glass-card p-6 flex flex-col items-center gap-4 text-center hover:border-[#00f2fe] group">
            <div className="p-4 rounded-2xl bg-[#00f2fe]/10 text-[#00f2fe] group-hover:scale-110 transition-transform">
              <Monitor className="w-8 h-8" />
            </div>
            <span className="font-bold text-sm sm:text-base text-white">Sillas & Escritorios</span>
          </Link>
        </div>
      </section>

      {/* Productos Gamer - Cuadrícula Adaptable con Separación Generosa (gap-8) */}
      <section className="space-y-6 w-full">
        <div className="flex items-center justify-between">
          <h2 className="text-xl sm:text-2xl font-bold text-white">
            Productos <span className="text-[#00f2fe]">Gamer</span>
          </h2>
          <Link to="/catalogo" className="text-xs font-bold text-[#00f2fe] hover:underline flex items-center gap-1">
            Ver Todos <ArrowRight className="w-3.5 h-3.5" />
          </Link>
        </div>

        {loading ? (
          <div className="text-center py-12 text-slate-400">Cargando productos gamer...</div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 2xl:grid-cols-6 gap-8 w-full">
            {filteredProducts.map((prod) => (
              <ProductCard key={prod.id} product={prod} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
};
