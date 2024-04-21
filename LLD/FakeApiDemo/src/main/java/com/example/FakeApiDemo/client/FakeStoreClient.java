package com.example.FakeApiDemo.client;

import com.example.FakeApiDemo.dto.FakeStoreCartResponseDTO;
import com.example.FakeApiDemo.dto.FakeStoreProductRatingDTO;
import com.example.FakeApiDemo.dto.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.base.url}")
    private String fakeStoreURL;

    @Value("${fakestore.api.product.path}")
    private String fakeStoreProductPath;

    @Value("${fakestore.api.category.path}")
    private String fakeStoreCategoryPath;

    @Value("${fakestore.api.cart.for.user.path}")
    private String fakeStoreUserPath;

    public List<FakeStoreProductResponseDTO> getAllProducts() {
        String fakeStoreGetAllProductURL = fakeStoreURL.concat(fakeStoreProductPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray =
                restTemplate.getForEntity(fakeStoreGetAllProductURL, FakeStoreProductResponseDTO[].class);
        return List.of(productResponseArray.getBody());
    }

    public FakeStoreProductResponseDTO getProductById(int id) {
        String fakeStoreGEtAllProductURL = fakeStoreURL.concat(fakeStoreProductPath).concat("/"+id);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.getForEntity(fakeStoreGEtAllProductURL, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public List<FakeStoreCartResponseDTO> getCartByUserId(int userId) {
        String fakeStoreGetCartForUser = fakeStoreURL.concat(fakeStoreUserPath).concat(String.valueOf(userId));
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartResponseDTO[]> cartResponse =
                restTemplate.getForEntity(fakeStoreGetCartForUser, FakeStoreCartResponseDTO[].class);
        return List.of(cartResponse.getBody());
    }


}
