package org.kosa.project.service;

import org.kosa.project.util.DirectoryUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileUploadService {
    @Value("${img.save.root}")
    private String imgSaveRoot;
    private static final String PATH_SEPARATOR = "/";
    private static final String SUB_DIRECTORY_PATH = "users";

    public String saveFile(MultipartFile imgFile) {
        String originalFilename = imgFile.getOriginalFilename();
        String extension = imgFile.getOriginalFilename().substring(originalFilename.lastIndexOf("."));

        // 1-1. 중복되지 않은 새로운 이름으로 파일을 저장하기 위해서.
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + extension;
        String savePath = imgSaveRoot + PATH_SEPARATOR + SUB_DIRECTORY_PATH;

        // 1-2. 디렉터리가 없다면 이를 생성한다.
        DirectoryUtil.createDirectoriesIfNotExists(savePath);

        // 1-3. 파일을 path에 저장한다.
        String fileSavePath = savePath + PATH_SEPARATOR + newFileName;
        File file = new File(fileSavePath);
        try {
            imgFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newFileName;
    }
}
