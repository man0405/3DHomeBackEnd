package com.example.backend.services;

import com.example.backend.dto.FileDataResponse;
import com.example.backend.dto.ImageResponse;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface ImageService {
    String uploadImage(MultipartFile[] file) throws IOException;

    House addImagesToHouse(MultipartFile[] file, House theHouse) throws Exception;

    byte[] downloadImage(String fileName);

    Optional<Image> findById(Long theId);

    String uploadImageToFileSystem(MultipartFile file) throws IOException;

    byte[] downloadImageFromFileSystem(UUID fileName) throws IOException;

    String deleteFileSystem(UUID fileName);

    Page<ImageResponse> getLibrary(int offset, int size, String field);

    Page<FileDataResponse> getLibrary(int offset);
}
