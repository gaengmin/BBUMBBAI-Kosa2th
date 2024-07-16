package org.kosa.project.service.fileupload;

import org.kosa.project.util.DirectoryUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public abstract class FileUploadService {

    @Value("${image.upload-dir}")
    private String uploadDir;
    private static final String PATH_SEPARATOR = getSeparator();

    private static String getSeparator() {
        return System.getProperty("os.name").contains("mac") ? File.separator : "/";
    }

    public String saveFile(MultipartFile imgFile) {
        String originalFilename = imgFile.getOriginalFilename();
        String extension = imgFile.getOriginalFilename().substring(originalFilename.lastIndexOf("."));

        // 1-1. 중복되지 않은 새로운 이름으로 파일을 저장하기 위해서.
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + extension;
        String savePath = uploadDir + PATH_SEPARATOR + getTargetUrl();

        System.out.println("fileSavePath: " + savePath);
        // 1-2. 디렉터리가 없다면 이를 생성한다.
        DirectoryUtil.createDirectoriesIfNotExists(savePath);

        // 1-3. 파일을 path에 저장한다.
        String fileSavePath = savePath + PATH_SEPARATOR + newFileName;
        System.out.println("fileSavePath:" + fileSavePath);
        File file = new File(fileSavePath);
        try {
            imgFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newFileName;
    }


//    public String saveFile(MultipartFile image, String imageRootDir) {
//        String originalFileName = image.getOriginalFilename(); //파일이름
//        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장명
//        String uniqueFileName = UUID.randomUUID() + extension; //uniqueFileName
//        String savedPath = imageRootDir + File.separator + uniqueFileName;
//        Path filePath = Paths.get(uploadDir + File.separator + savedPath); //파일경로
//        try {
//            if (!Files.exists(filePath.getParent())) {
//                Files.createDirectories(filePath.getParent());
//            }
//
//            Files.write(filePath, image.getBytes());
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // 파일 이름,
//        return savedPath;
//    }
//
//    // users 정보를 저장하는 fileService?
//    // meeting 정보를 저장하는 fileService?

    public abstract String getTargetUrl();
}
