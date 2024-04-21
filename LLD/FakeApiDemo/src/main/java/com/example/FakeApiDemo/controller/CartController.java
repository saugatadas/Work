package com.example.FakeApiDemo.controller;
import com.example.FakeApiDemo.client.FakeStoreClient;
import com.example.FakeApiDemo.dto.FakeStoreCartResponseDTO;
import com.example.FakeApiDemo.dto.FakeStoreProductResponseDTO;
import com.example.FakeApiDemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{userid}")
    public ResponseEntity getCartForUser(@PathVariable("userid") int userId) {
        List<FakeStoreCartResponseDTO> fakeCartList = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(fakeCartList);
    }

}
