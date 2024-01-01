package com.example.backend.controllers;

import com.example.backend.dto.APIResponse;
import com.example.backend.dto.CustomPage;
import com.example.backend.models.entity.House;
import com.example.backend.services.HouseService;
import com.example.backend.services.ImageService;
import com.example.backend.services.VisitService;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

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
    public CustomPage<House> getHouses(@CookieValue("uss") String cookie, @PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<House> houses = houseService.findOwnerHouses(Math.toIntExact(ExtractIdFromToken(cookie)),offset,pageSize,field);
        return new CustomPage<>(houses);

    }

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<Page<House>> getHousesWithSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<House> housesWithPaginationAndSort = houseService.findHousesWithPaginationAndSort(offset - 1, pageSize, field);
        return new APIResponse<>(housesWithPaginationAndSort.getSize(), housesWithPaginationAndSort);
    }

    @GetMapping("/id/{id}")
    public House getHouseById(@PathVariable int id, @CookieValue("uss") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        visitService.save(Math.toIntExact(customerId),id);
        return houseService.findById(id);
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
    public void leaveInformation(@PathVariable int id, @CookieValue("uss") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        visitService.updatePriority( Math.toIntExact(customerId),id );

    }


}
