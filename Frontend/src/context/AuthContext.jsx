import { createContext, useContext, useState, useEffect } from 'react';
import { loginAPI } from '../utils/api';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('gamer_user');
    return saved ? JSON.parse(saved) : null;
  });

  const login = async (email, password) => {
    const response = await loginAPI(email, password);
    if (response.exito) {
      const userData = {
        nombre: response.nombre || 'Gamer User',
        email: response.email || email,
        token: response.token,
        rol: response.rol || 'ROLE_USER'
      };
      setUser(userData);
      localStorage.setItem('gamer_user', JSON.stringify(userData));
    }
    return response;
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('gamer_user');
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, isAuthenticated: !!user }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
