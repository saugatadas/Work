package com.example.FakeApiDemo.mapper;

import com.example.FakeApiDemo.dto.ProductResponseDTO;
import com.example.FakeApiDemo.entity.Product;

public class ProductEntityDTOMapper {
    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setCategory(product.getCategory());
        productResponseDTO.setProductId(product.getId());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setRating(product.getRating());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setTitle(product.getTitle());
        productResponseDTO.setImageURL(product.getImageURL());
        return productResponseDTO;
    }
}
