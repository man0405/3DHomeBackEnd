package com.example.backend.services.Impl;

import com.example.backend.dto.FileDataResponse;
import com.example.backend.dto.ImageResponse;
import com.example.backend.models.entity.FileData;
import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Image;
import com.example.backend.repository.FileDataRepo;
import com.example.backend.repository.ImageRepo;
import com.example.backend.services.ImageService;
import com.example.backend.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepo imageRepo;

    private final FileDataRepo fileDataRepo;

    @Value("${local.path}")
    private String FOLDER_PATH;

    public ImageServiceImpl(ImageRepo imageRepo, FileDataRepo fileDataRepo) {
        this.imageRepo = imageRepo;
        this.fileDataRepo = fileDataRepo;
    }

//    @Transactional
    public String uploadImage(MultipartFile[] files) throws IOException {
        String status = null;
        for(MultipartFile file : files){
            Optional<Image> theImage = imageRepo.findByName(file.getOriginalFilename());
            if(theImage.isPresent())
                status += "image is already existed \n";
            String getPath = "http://localhost:8080/image/" + file.getOriginalFilename();
            Image imageData = imageRepo.save(Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .getImage(getPath)
                    .build());
            if (imageData != null)
                status += "file uploaded successfully: " + file.getOriginalFilename() + "\n";
        }
        return status;
    }

    @Transactional
    public House addImagesToHouse(MultipartFile[] files, House theHouse) throws IOException {
        for(MultipartFile file : files){
            UUID id = UUID.randomUUID();
            String filePath = FOLDER_PATH + id;
            String getPath = "http://localhost:8080/image/fileSystem/" + id;
            FileData fileData = fileDataRepo.save(FileData.builder()
                    .Id(id)
                    .type(file.getContentType())
                    .filePath(filePath)
                    .getPath(getPath)
                    .build());
            file.transferTo(new File(filePath));
            theHouse.addImage(fileData);
        }
        return theHouse;
    }



    @Override
    public Furniture addImagesToFurniture(MultipartFile[] files, Furniture theFurniture) throws Exception {
        for(MultipartFile file : files){
            UUID id = UUID.randomUUID();
            String filePath = FOLDER_PATH + id;
            String getPath = "http://localhost:8080/image/fileSystem/" + id;
            FileData fileData = fileDataRepo.save(FileData.builder()
                    .Id(id)
                    .type(file.getContentType())
                    .filePath(filePath)
                    .getPath(getPath)
                            .furnitureId(theFurniture.getId().intValue())
                    .build());
            file.transferTo(new File(filePath));
            theFurniture.addImage(fileData);
        }
        return theFurniture;
    }

    @Transactional
    public Optional<Image> findById(Long Id){
        return imageRepo.findById(Id);
    }

    @Transactional
    public byte[] downloadImage(String fileName){
       Optional<Image> image = imageRepo.findByName(fileName);
       return ImageUtils.decompressImage(image.get().getImageData());
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        UUID id = null;
        Optional<FileData> holder = null;
        do{
            id = UUID.randomUUID();
            holder = fileDataRepo.findById(id);
        }while(holder.isPresent());
        String filePath = FOLDER_PATH + id;
        String getPath = "http://localhost:8080/image/fileSystem/" + id;
        FileData fileData = fileDataRepo.save(FileData.builder()
                .Id(id)
                .type(file.getContentType())
                .filePath(filePath)
                .getPath(getPath)
                .build());

        file.transferTo(new File(filePath));
        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
            return null;
    }

    @Transactional
    public byte[] downloadImageFromFileSystem(UUID fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepo.findById(fileName);
        String filePath = fileData.get().getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    @Override
    public String deleteFileSystem(UUID fileName) {
        Optional<FileData> theFileData = fileDataRepo.findById(fileName);
        if(theFileData.isEmpty())
            return "File doesn't exist";
        String filePath = theFileData.get().getFilePath();
        File theFile = new File(filePath);
        if(!theFile.delete())
            return "Delete Unsuccessfully";
        fileDataRepo.delete(theFileData.get());
        return "Deleted Successfully";
    }

    public Page<ImageResponse> getLibrary(int offset, int size, String field){
        return imageRepo.findAll(PageRequest.of(offset, size).withSort(Sort.by(field)))
                .map(image -> new ImageResponse(
                        image.getId(),
                        image.getName(),
                        image.getGetImage()
                ));
    }

    @Override
    public Page<FileDataResponse> getLibrary(int offset) {
        return fileDataRepo.findAll(PageRequest.of(offset, 10))
                .map(fileData -> new FileDataResponse(
                        fileData.getId(),
                        fileData.getGetPath()
                ));
    }
}
