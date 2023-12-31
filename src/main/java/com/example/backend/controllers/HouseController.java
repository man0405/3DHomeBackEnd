package com.example.backend.controllers;


import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.HouseService;
import com.example.backend.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    // expose "/owners" and return a list of owners
    @GetMapping("/{id}")
    public House findAll(@PathVariable UUID id) {
        return houseService.findById(id);
    }

}














