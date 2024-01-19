package com.proyecto.api.domain.repository;

import com.proyecto.api.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScasosProducto(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
