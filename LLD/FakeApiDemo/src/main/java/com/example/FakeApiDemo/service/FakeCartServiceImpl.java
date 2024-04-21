package com.example.FakeApiDemo.service;

import com.example.FakeApiDemo.client.FakeStoreClient;
import com.example.FakeApiDemo.dto.FakeStoreCartResponseDTO;
import com.example.FakeApiDemo.dto.FakeStoreProductResponseDTO;
import com.example.FakeApiDemo.exception.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.undo.CannotRedoException;
import java.util.List;

@Service
public class FakeCartServiceImpl implements CartService{

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public List<FakeStoreCartResponseDTO> getCartByUserId(int userId) {
        if (userId < 0) {
            throw new CartNotFoundException("cart for " + userId + " not found");
        }
        List<FakeStoreCartResponseDTO> fakeCartByUser = fakeStoreClient.getCartByUserId(userId);
        return fakeCartByUser;
    }
}
