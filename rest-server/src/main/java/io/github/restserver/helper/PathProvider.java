package io.github.restserver.helper;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class PathProvider {
    public String provideStoragePath() {
        final Path SERVER_BASE_PATH = Paths.get("src/main/resources/storage");
        final String projectRootLocation = System.getProperty("user.dir");
        final String UPLOAD_DIR = projectRootLocation + File.separator + SERVER_BASE_PATH.toString() + File.separator;
        return UPLOAD_DIR;
    }

    public String provideScriptPath() {
        final String projectRootLocation = System.getProperty("user.dir");
        String scriptDirectory = projectRootLocation.split("rest-server")[0];
        scriptDirectory = scriptDirectory + "scripts";
        return scriptDirectory;
    }
}
