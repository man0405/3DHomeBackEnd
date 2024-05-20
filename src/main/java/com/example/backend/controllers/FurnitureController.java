package com.example.backend.controllers;

import com.example.backend.dto.APIResponse;
import com.example.backend.dto.CheckResponse;
import com.example.backend.dto.CustomPage;
import com.example.backend.dto.FurnitureResponse;
import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.House;
import com.example.backend.services.FurnitureService;
import com.example.backend.services.ImageService;
import com.example.backend.services.VisitService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

@RestController
@RequestMapping("/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;
    private final ImageService imageService;

    private final VisitService visitService;

    public FurnitureController(FurnitureService furnitureService, ImageService imageService, VisitService visitService) {
        this.furnitureService = furnitureService;
        this.imageService = imageService;
        this.visitService = visitService;
    }


    @GetMapping("/{field}")
    private APIResponse<List<Furniture>> getFurnituresWithSort(@PathVariable String field) {
        List<Furniture> allProducts = furnitureService.findFurnituresWithSorting(field);
        return new APIResponse<>(allProducts.size(), allProducts);
    }

//    @GetMapping("/owner-furnitures/{offset}/{pageSize}/{field}")
//    public CustomPage<Furniture> getFurnitures(@RequestHeader("Authorization") String cookie, @PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
//        Page<Furniture> furnitures = furnitureService.findOwnerFurnitures(Math.toIntExact(ExtractIdFromToken(cookie)),offset,pageSize,field);
//        return new CustomPage<>(furnitures);
//    }



    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<Page<FurnitureResponse>> getFurnituresWithSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field, @RequestHeader("Authorization") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        Page<FurnitureResponse> furnituresWithPaginationAndSort = furnitureService.findFurnituresWithPaginationAndSort(offset - 1, pageSize, field, customerId.intValue());
        return new APIResponse<>(furnituresWithPaginationAndSort.getSize(), furnituresWithPaginationAndSort);
    }


    @PutMapping("/like/{id}")
    public Boolean updateFav(@PathVariable int id, @RequestHeader("Authorization") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        return visitService.updateFavF(Math.toIntExact(customerId), id);
    }


    @GetMapping("pagination/{offset}/{pageSize}/search={name}")
    public APIResponse<Page<Furniture>> searchingFunction(@PathVariable String name, @PathVariable int offset, @PathVariable int pageSize){
        System.out.println("Search name " + name);
        Page<Furniture> furnitures = furnitureService.searchFurniture(name, offset, pageSize);
        return new APIResponse<>(furnitures.getSize(), furnitures);
    }



    @GetMapping("/id/{id}")
    public Furniture getFurnitureById(@PathVariable int id, @RequestHeader("Authorization") String cookie){
        Long customerId = ExtractIdFromToken(cookie);
        visitService.saveFurniture(Math.toIntExact(customerId),id);
        return furnitureService.findById(Long.valueOf(id));
    }

    @PutMapping("/add-image/{furnitureId}")
    public Furniture addImageToFurniture(@PathVariable Long furnitureId, @RequestParam("image")MultipartFile[] files) throws Exception {
        System.out.println("hello: ");
        Furniture theFurniture = furnitureService.findById(furnitureId);

        imageService.addImagesToFurniture(files, theFurniture);
        return theFurniture;
    }


}
