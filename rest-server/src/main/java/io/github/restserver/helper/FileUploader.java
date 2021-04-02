package io.github.restserver.helper;

import io.github.restserver.middleware.LoggerProvider;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploader {
    private final PathProvider pathProvider;
    private final Logger logger;

    public FileUploader(PathProvider pathProvider) {
        this.pathProvider = pathProvider;
        this.logger = new LoggerProvider(DeadlineFootPrinter.class).provideLoggerInstance();
    }

    public boolean uploadFile(MultipartFile file) {
        boolean flag = false;
        String filePath = pathProvider.provideStoragePath() + file.getOriginalFilename();
        try {
            Files.copy(
                    file.getInputStream(),
                    Paths.get(filePath),
                    StandardCopyOption.REPLACE_EXISTING
            );
            flag = true;
        } catch (Exception e) {
            logger.error("error occurred while copying to " + filePath);
        }
        return flag;
    }
}
