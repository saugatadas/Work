package com.example.FakeApiDemo.service;
import com.example.FakeApiDemo.dto.FakeStoreCartResponseDTO;
import java.util.List;

public interface CartService {
    List<FakeStoreCartResponseDTO> getCartByUserId(int userId);
}