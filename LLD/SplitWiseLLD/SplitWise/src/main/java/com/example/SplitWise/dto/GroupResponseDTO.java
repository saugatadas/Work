package com.example.SplitWise.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponseDTO {
    private int id;
    private String name;
    private List<UserResponseDTO> members;
}
