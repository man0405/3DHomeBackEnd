package com.example.backend.dto;

import com.example.backend.models.entity.Item;

import java.util.List;

public record CartResponse(
        int id,
        List<Item> items
) {
}
