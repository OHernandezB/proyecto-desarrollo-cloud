import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { Gamepad2, Shield, AlertCircle } from 'lucide-react';

export const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    const res = await login(email, password);
    setLoading(false);

    if (res.exito) {
      navigate('/');
    } else {
      setError(res.mensaje || 'Credenciales inválidas');
    }
  };

  const handleMicrosoftLogin = () => {
    alert('Iniciando flujo de autenticación Microsoft MSAL...\n(Conecta tus credenciales de Azure en Backend/Login/.env)');
  };

  return (
    <div className="max-w-md mx-auto my-12 glass-card p-8 border-cyan-500/30">
      
      {/* Header Form */}
      <div className="text-center space-y-3 mb-8">
        <div className="inline-flex p-3 rounded-2xl bg-gradient-to-tr from-[#00f2fe] to-[#7f00ff] text-black shadow-lg shadow-[#00f2fe]/30">
          <Gamepad2 className="w-8 h-8 stroke-[2.5]" />
        </div>
        <h1 className="text-2xl font-bold text-white">Iniciar Sesión</h1>
        <p className="text-xs text-slate-400">Ingresa tus credenciales para acceder a tu cuenta Gamer</p>
      </div>

      {error && (
        <div className="bg-red-500/10 border border-red-500/30 text-red-400 p-3 rounded-xl text-xs flex items-center gap-2 mb-6">
          <AlertCircle className="w-4 h-4 shrink-0" />
          <span>{error}</span>
        </div>
      )}

      {/* Formulario Estándar */}
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-xs font-bold text-slate-300 mb-1.5 uppercase">Email</label>
          <input
            type="email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="jugador@nexusgaming.cl"
            className="w-full bg-[#121622] border border-white/10 rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none focus:border-[#00f2fe] transition-colors"
          />
        </div>

        <div>
          <label className="block text-xs font-bold text-slate-300 mb-1.5 uppercase">Contraseña</label>
          <input
            type="password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="••••••••"
            className="w-full bg-[#121622] border border-white/10 rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none focus:border-[#00f2fe] transition-colors"
          />
        </div>

        <button
          type="submit"
          disabled={loading}
          className="btn-gamer-primary w-full justify-center mt-2"
        >
          {loading ? 'Ingresando...' : 'Iniciar Sesión'}
        </button>
      </form>

      {/* Separador */}
      <div className="relative my-6 text-center">
        <div className="absolute inset-0 flex items-center">
          <div className="w-full border-t border-white/10"></div>
        </div>
        <span className="relative bg-[#121622] px-3 text-[10px] text-slate-500 font-bold uppercase">
          O continúa con
        </span>
      </div>

      {/* Botón Microsoft Auth (MSAL) */}
      <button
        onClick={handleMicrosoftLogin}
        className="w-full bg-[#1e2436] hover:bg-[#283048] border border-white/10 text-white font-semibold py-2.5 px-4 rounded-xl text-xs flex items-center justify-center gap-3 transition-colors"
      >
        <svg className="w-4 h-4" viewBox="0 0 21 21">
          <rect x="1" y="1" width="9" height="9" fill="#f25022" />
          <rect x="11" y="1" width="9" height="9" fill="#7fba00" />
          <rect x="1" y="11" width="9" height="9" fill="#00a4ef" />
          <rect x="11" y="11" width="9" height="9" fill="#ffb900" />
        </svg>
        Iniciar sesión con Microsoft (MSAL)
      </button>

      <div className="text-center mt-6 text-xs text-slate-500">
        <Shield className="w-3.5 h-3.5 inline mr-1 text-[#00f2fe]" />
        Conexión segura protegida por Spring Boot & MSAL
      </div>
    </div>
  );
};
