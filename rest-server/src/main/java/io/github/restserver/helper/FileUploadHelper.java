package io.github.restserver.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    @Autowired
    private PathProvider pathProvider;

    public boolean uploadFile(MultipartFile file) {
        boolean flag = false;
        try {
            Files.copy(
                    file.getInputStream(),
                    Paths.get(pathProvider.provideStoragePath() + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
