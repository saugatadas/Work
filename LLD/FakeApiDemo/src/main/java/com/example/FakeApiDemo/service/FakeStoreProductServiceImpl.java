package com.example.FakeApiDemo.service;

import com.example.FakeApiDemo.client.FakeStoreClient;
import com.example.FakeApiDemo.dto.FakeStoreProductResponseDTO;
import com.example.FakeApiDemo.entity.Product;
import com.example.FakeApiDemo.exception.InvalidInputException;
import com.example.FakeApiDemo.exception.NoProductPresentException;
import com.example.FakeApiDemo.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public List<FakeStoreProductResponseDTO> getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProducts = fakeStoreClient.getAllProducts();
        if (fakeStoreProducts == null) {
            throw new NoProductPresentException("No product is found");
        }
        return fakeStoreProducts;
    }

    @Override
    public FakeStoreProductResponseDTO getProduct(int productId) {
        if (productId < 1) {
            throw new InvalidInputException("input not correct " + productId);
        }

        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreClient.getProductById(productId);
        if (fakeStoreProductResponseDTO == null) {
            throw new ProductNotFoundException("Product " + productId + " not found");
        }
        return fakeStoreProductResponseDTO;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product updateProduct, int productId) {
        return null;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }
}