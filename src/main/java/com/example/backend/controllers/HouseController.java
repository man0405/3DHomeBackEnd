package com.example.backend.controllers;

import com.example.backend.dto.APIResponse;
import com.example.backend.models.entity.House;
import com.example.backend.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;
    @GetMapping("/{field}")
    private APIResponse<List<House>> getHousesWithSort(@PathVariable String field) {
        List<House> allProducts = houseService.findHousesWithSorting(field);
        return new APIResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<Page<House>> getHousesWithSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<House> housesWithPaginationAndSort = houseService.findHousesWithPaginationAndSort(offset - 1, pageSize, field);
        return new APIResponse<>(housesWithPaginationAndSort.getSize(), housesWithPaginationAndSort);
    }

    @GetMapping("/id/{id}")
    public House getHouseById(@PathVariable int id){
        return houseService.findById(id);
    }

}
