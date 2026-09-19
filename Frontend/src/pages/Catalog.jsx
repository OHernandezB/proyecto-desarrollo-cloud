import { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import { fetchProductosAPI } from '../utils/api';
import { ProductCard } from '../components/ProductCard';
import { CategoryFilter } from '../components/CategoryFilter';

export const Catalog = ({ searchTerm }) => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchParams] = useSearchParams();
  const initialCategory = searchParams.get('cat') || 'Todas';
  const [selectedCategory, setSelectedCategory] = useState(initialCategory);

  useEffect(() => {
    fetchProductosAPI().then((data) => {
      setProducts(data);
      setLoading(false);
    });
  }, []);

  const categories = ['Todas', 'Teclados', 'Periféricos', 'Pantallas', 'Muebles Gamer'];

  const filtered = products.filter((p) => {
    const matchesCat = selectedCategory === 'Todas' || p.categoria === selectedCategory;
    const matchesSearch = !searchTerm ||
      p.nombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
      p.descripcion.toLowerCase().includes(searchTerm.toLowerCase());
    return matchesCat && matchesSearch;
  });

  return (
    <div className="space-y-8">
      <div>
        <h1 className="text-3xl font-extrabold text-white">
          Catálogo <span className="text-[#00f2fe]">Gamer</span>
        </h1>
        <p className="text-xs text-slate-400 mt-1">
          Filtra por categoría o encuentra el producto ideal para tu setup.
        </p>
      </div>

      <CategoryFilter
        categories={categories}
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
      />

      {loading ? (
        <div className="text-center py-12 text-slate-400">Cargando catálogo...</div>
      ) : filtered.length === 0 ? (
        <div className="glass-card p-12 text-center text-slate-400">
          No se encontraron productos en esta categoría o búsqueda.
        </div>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {filtered.map((prod) => (
            <ProductCard key={prod.id} product={prod} />
          ))}
        </div>
      )}
    </div>
  );
};
