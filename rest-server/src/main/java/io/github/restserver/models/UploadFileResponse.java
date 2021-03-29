package io.github.restserver.models;

import java.util.ArrayList;

public class UploadFileResponse {
    public ArrayList<ArrayList<Long>> multiplicationResult;
    public Status status;

    public UploadFileResponse() {
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMultiplicationResult(ArrayList<ArrayList<Long>> multiplicationResult) {
        this.multiplicationResult = multiplicationResult;
    }

}
