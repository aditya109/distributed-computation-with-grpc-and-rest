package io.github.services;

import io.github.models.FileMetadata;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DiskFileStore {
    private String outputFolder;
    private ConcurrentHashMap<String, FileMetadata> data;

    public DiskFileStore(String outputFolder) {
        this.outputFolder = outputFolder;
        this.data = new ConcurrentHashMap<>(0);
    }
    public String save(String fileId, String fileType, ByteArrayOutputStream fileData) throws IOException {
        String id = UUID.randomUUID().toString();
        String filePath = String.format("%s/%s%s",outputFolder, id, fileType);

        FileOutputStream fos = new FileOutputStream(filePath);
        fileData.writeTo(fos);
        fos.close();

        FileMetadata metadata = new FileMetadata(fileId, fileType, filePath);
        this.data.put(id, metadata);

        return id;
    }
}
