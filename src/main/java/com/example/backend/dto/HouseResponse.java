package com.example.backend.dto;

import com.example.backend.models.entity.House;

public record HouseResponse(
    House house,
    boolean heart
) {
}
