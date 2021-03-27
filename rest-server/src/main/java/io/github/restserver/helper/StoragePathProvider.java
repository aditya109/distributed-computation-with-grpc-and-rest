package io.github.restserver.helper;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StoragePathProvider {
    public String provideStoragePath() {
        final Path SERVER_BASE_PATH = Paths.get("src/main/resources/storage");
        final String projectRootLocation = System.getProperty("user.dir");
        final String UPLOAD_DIR = projectRootLocation + "\\" + SERVER_BASE_PATH.toString() + "\\";
        return UPLOAD_DIR;
    }
}
