package com.example.FakeApiDemo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ProductQuantityDTO {
    private int productId;
    private int quantity;
}
