package com.backend.service;

import com.backend.dto.ProductoRequest;
import com.backend.dto.ProductoResponse;
import com.backend.model.ProductoModel;
import com.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ==========================================
 * CAPA DE SERVICIO (LÓGICA DE NEGOCIO)
 * ==========================================
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponse> obtenerTodos() {
        return productoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProductoResponse> obtenerPorId(Long id) {
        return productoRepository.findById(id).map(this::mapToResponse);
    }

    public ProductoResponse crear(ProductoRequest request) {
        ProductoModel nuevoProducto = ProductoModel.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .imagenUrl(request.getImagenUrl())
                .categoria(request.getCategoria())
                .build();

        ProductoModel guardado = productoRepository.save(nuevoProducto);
        return mapToResponse(guardado);
    }

    public Optional<ProductoResponse> actualizar(Long id, ProductoRequest request) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(request.getNombre());
            producto.setDescripcion(request.getDescripcion());
            producto.setPrecio(request.getPrecio());
            producto.setStock(request.getStock());
            producto.setImagenUrl(request.getImagenUrl());
            producto.setCategoria(request.getCategoria());
            ProductoModel actualizado = productoRepository.save(producto);
            return mapToResponse(actualizado);
        });
    }

    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProductoResponse mapToResponse(ProductoModel producto) {
        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .imagenUrl(producto.getImagenUrl())
                .categoria(producto.getCategoria())
                .build();
    }
}
