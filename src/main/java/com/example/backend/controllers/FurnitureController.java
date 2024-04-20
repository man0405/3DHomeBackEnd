package com.example.backend.controllers;

import com.example.backend.dto.APIResponse;
import com.example.backend.dto.CustomPage;
import com.example.backend.models.entity.Furniture;
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

    public FurnitureController(FurnitureService furnitureService, ImageService imageService, VisitService visitService) {
        this.furnitureService = furnitureService;
        this.imageService = imageService;
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
    public APIResponse<Page<Furniture>> getFurnituresWithSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<Furniture> furnituresWithPaginationAndSort = furnitureService.findFurnituresWithPaginationAndSort(offset - 1, pageSize, field);
        return new APIResponse<>(furnituresWithPaginationAndSort.getSize(), furnituresWithPaginationAndSort);
    }

    @GetMapping("/id/{id}")
    public Furniture getFurnitureById(@PathVariable Long id){
        return furnitureService.findById(id);
    }

    @PutMapping("/add-image/{furnitureId}")
    public Furniture addImageToFurniture(@PathVariable Long furnitureId, @RequestParam("image")MultipartFile[] files) throws Exception {
        System.out.println("hello: ");
        Furniture theFurniture = furnitureService.findById(furnitureId);

        imageService.addImagesToFurniture(files, theFurniture);
        return theFurniture;
    }

}
