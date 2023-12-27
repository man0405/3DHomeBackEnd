package com.example.backend.dto;

import com.example.backend.models.entity.FileData;

import java.util.List;

public record HouseResponse(
    int id,
    String name,
    String description,
    FileData fileDataResponse
) {
}
