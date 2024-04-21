package com.example.FakeApiDemo.service;

import com.example.FakeApiDemo.entity.Product;
import com.example.FakeApiDemo.dto.FakeStoreProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<FakeStoreProductResponseDTO> getAllProducts();
    FakeStoreProductResponseDTO getProduct(int productId);
    Product createProduct(Product product);
    Product updateProduct(Product updateProduct, int productId);
    boolean deleteProduct(int productId);
}
