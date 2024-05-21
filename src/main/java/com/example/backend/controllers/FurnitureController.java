package com.example.backend.controllers;

import com.example.backend.dto.*;
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


    @GetMapping("pagination/{offset}/{pageSize}")
    public APIResponse<Page<FurnitureResponse>> searchingFunction( @RequestHeader("Authorization") String cookie,@RequestParam(required = false) String search,@RequestParam(required = false) String price,@RequestParam(required = false) String warranty, @RequestParam(required = false) String material, @PathVariable int offset, @PathVariable int pageSize){
        Long customerId = ExtractIdFromToken(cookie);
        System.out.println("search: " + search);
        System.out.println("price: " + price);
        System.out.println("warranty: " + warranty);
        System.out.println("material: " + material);

        Page<FurnitureResponse> furnitures = furnitureService.searchFurniture(customerId.intValue(),search != null ? search.trim() : "",price,warranty,material, offset, pageSize);
        return new APIResponse<>(furnitures.getSize(), furnitures);
    }

    @GetMapping("filter")
    public FurnitureFilter filters(){

        return furnitureService.getFilter();
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
