package io.github.models;

public class FileMetadata {
    private String fileId;
    private String fileType;
    private String path;

    public FileMetadata(String fileId, String fileType, String path) {
        this.fileId = fileId;
        this.fileType = fileType;
        this.path = path;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
