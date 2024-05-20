package com.example.backend.dto;

import com.example.backend.models.entity.Furniture;

public record FurnitureResponse(
    Furniture furniture,
    boolean heart
) {
}
