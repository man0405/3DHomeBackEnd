package com.example.backend.controllers;

import com.example.backend.models.entity.Image;
import com.example.backend.services.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

//    @PostMapping
//    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile[] files) throws IOException {
//        String uploadImage = imageService.uploadImage(files);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] image = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image")MultipartFile[] files) throws IOException {
        String uploadImage = "";
        for(MultipartFile file : files)
            uploadImage +=  imageService.uploadImageToFileSystem(file)+ "\n";
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageToFileSystem(@PathVariable String fileName) throws IOException {
        byte[] image = imageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


    @DeleteMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> deleteFileSystem(@PathVariable String fileName){
        String deleteFile = imageService.deleteFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(deleteFile);
    }

    @RequestMapping(value = "/image/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadImageById(@PathVariable String name){
        return imageService.downloadImage(name);
    }

    @GetMapping("/library/{offset}/{size}/{field}")
    public APIResponse<Page<ImageResponse>> getLibraryImage(@PathVariable int offset, @PathVariable int size, @PathVariable String field){
        Page<ImageResponse> imagesWithPaginationAndSort = imageService.getLibrary(offset - 1, size, field);
        return new APIResponse<>(imagesWithPaginationAndSort.getSize(), imagesWithPaginationAndSort);
    }


    @GetMapping("/library")
    public APIResponse<Page<FileDataResponse>> getLibraryImage(@RequestParam("page") int offset){
        Page<FileDataResponse> fileWithPaginationAndSort = imageService.getLibrary(offset - 1);
        return new APIResponse<>(fileWithPaginationAndSort.getSize(), fileWithPaginationAndSort);
    }
}
