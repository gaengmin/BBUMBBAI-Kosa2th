package org.kosa.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${image.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile image, String imageRootDir) {
        String originalFileName = image.getOriginalFilename(); //파일이름
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장명
        String uniqueFileName = UUID.randomUUID() + extension; //uniqueFileName
        String savedPath = imageRootDir + File.separator + uniqueFileName;
        Path filePath = Paths.get(uploadDir + File.separator + savedPath); //파일경로
        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            Files.write(filePath, image.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 파일 이름,
        return savedPath;
    }
    // //

//    String originalFileName = image.getOriginalFilename(); //파일이름
//    String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장명
//    String uniqueFileName = UUID.randomUUID().toString() + extension; //uniqueFileName
//    Path filePath = Paths.get(uploadDir + File.separator + uniqueFileName); //파일경로
//
//            if (!Files.exists(filePath.getParent())) {
//        Files.createDirectories(filePath.getParent());
//    }
//            Files.write(filePath, image.getBytes());
//    //
//
//            meetingDto.setFileName(uniqueFileName);
}
