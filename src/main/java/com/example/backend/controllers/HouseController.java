package com.example.backend.controllers;

import com.example.backend.dto.*;
import com.example.backend.models.entity.House;
import com.example.backend.services.HouseService;
import com.example.backend.services.ImageService;
import com.example.backend.services.VisitService;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;
import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;
    private final ImageService imageService;
    private final VisitService visitService;

    public HouseController(HouseService houseService, ImageService imageService, VisitService visitService) {
        this.houseService = houseService;
        this.imageService = imageService;
        this.visitService = visitService;
    }


    @GetMapping("/{field}")
    private APIResponse<List<House>> getHousesWithSort(@PathVariable String field) {
        List<House> allProducts = houseService.findHousesWithSorting(field);
        return new APIResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/owner-houses/{offset}/{pageSize}/{field}")
    public CustomPage<House> getHouses(@RequestHeader("Authorization") String cookie, @PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<House> houses = houseService.findOwnerHouses(Math.toIntExact(ExtractIdFromToken(cookie)),offset,pageSize,field);
        return new CustomPage<>(houses);
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<Page<HouseResponse>> getHousesWithSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field, @RequestHeader("Authorization") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        Page<HouseResponse> housesWithPaginationAndSort = houseService.findHousesWithPaginationAndSort(offset - 1, pageSize, field, customerId);
        return new APIResponse<>(housesWithPaginationAndSort.getSize(), housesWithPaginationAndSort);
    }

    @GetMapping("pagination/{offset}/{pageSize}")
    public APIResponse<Page<HouseResponse>> searchingFunction(@RequestParam(required = false) String search,@RequestParam(required = false) String landSize,@RequestParam(required = false) Integer bedroom,@RequestParam(required = false) String price, @PathVariable int offset, @PathVariable int pageSize,@RequestHeader("Authorization") String cookie){
        System.out.println("search: " + search);
        System.out.println("landSize: " + landSize);
        System.out.println("bedroom: " + bedroom);
        System.out.println("price: " + price);
        int[] land_size = {0, 0};
        int[] price_range = {0,0};
        if (landSize != null){
            land_size = Arrays.stream(landSize.trim().split("-")).mapToInt(Integer::parseInt).toArray();
        }
        if (price!= null){
            price_range = Arrays.stream(price.trim().split("-")).mapToInt(Integer::parseInt).toArray();
        }
        Long customerId = ExtractIdFromToken(cookie);
        Page<HouseResponse> houses = houseService.searchHouse(customerId,search != null ? search : "",landSize,land_size[0],land_size[1],bedroom != null ? bedroom : 0,price,price_range[0],price_range[1], offset, pageSize);
        return new APIResponse<>(houses.getSize(), houses);
    }

    @GetMapping("filter")
    public FilterResponse filters(){

        return houseService.getFilter();
    }

    @GetMapping("/id/{id}")
    public House getHouseById(@PathVariable int id, @RequestHeader("Authorization") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        visitService.saveHouse(Math.toIntExact(customerId),id);
        return houseService.findById(id);
    }

    @PutMapping("/like/{id}")
    public Boolean updateFav(@PathVariable int id, @RequestHeader("Authorization") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        return visitService.updateFav(Math.toIntExact(customerId), id);
    }


    @GetMapping("/owner/id/{id}")
    public House getHouseById(@PathVariable int id){
        return houseService.findById(id);
    }

    @PutMapping("/add-image/{houseId}")
    public House addImageToHouse(@PathVariable int houseId, @RequestParam("image")MultipartFile[] files) throws Exception {
        House theHouse = houseService.findById(houseId);
        imageService.addImagesToHouse(files, theHouse);
        return theHouse;
    }

    @PutMapping("/leave-info/{id}")
    public void leaveInformation(@PathVariable int id,@RequestHeader("Authorization") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        visitService.updatePriority( Math.toIntExact(customerId),id );
    }


    @GetMapping("/visitPerMonth/{houseId}")
    public List<Integer> visitPerMonth(@PathVariable int houseId){
        return visitService.visitPerWeek(houseId);
    }


}
