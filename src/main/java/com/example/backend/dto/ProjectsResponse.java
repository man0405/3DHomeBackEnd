package com.example.backend.dto;

import com.example.backend.models.entity.House;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsResponse {
    private int page ;
    private int per_page;
    private int total ;
    private ArrayList<House> data;
}
