package com.example.backend.services.Impl;

import com.example.backend.models.entity.Image;
import com.example.backend.repository.ImageRepo;
import com.example.backend.services.ImageService;
import com.example.backend.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepo imageRepo;

    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = imageRepo.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null)
            return "file uploaded successfully: " + file.getOriginalFilename();
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<Image> dbImageData = imageRepo.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }

    public byte[] downloadImage(Long imageId){
        Optional<Image> dbImageData = imageRepo.findById(imageId);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }

}
