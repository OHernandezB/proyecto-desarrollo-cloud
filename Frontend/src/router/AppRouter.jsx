import { Routes, Route } from 'react-router-dom';
import { Home } from '../pages/Home';
import { Catalog } from '../pages/Catalog';
import { Login } from '../pages/Login';
import { CartPage } from '../pages/CartPage';

export const AppRouter = ({ searchTerm }) => {
  return (
    <Routes>
      <Route path="/" element={<Home searchTerm={searchTerm} />} />
      <Route path="/catalogo" element={<Catalog searchTerm={searchTerm} />} />
      <Route path="/login" element={<Login />} />
      <Route path="/carrito" element={<CartPage />} />
      <Route path="*" element={<Home searchTerm={searchTerm} />} />
    </Routes>
  );
};
