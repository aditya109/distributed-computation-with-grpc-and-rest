package io.github.restserver.models;

import java.util.ArrayList;

public class UploadFileResponse {
    public ArrayList<ArrayList<Long>> multiplicationResult;
    public Status status;

    public ArrayList<ArrayList<Long>> getMultiplicationResult() {
        return multiplicationResult;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMultiplicationResult(ArrayList<ArrayList<Long>> multiplicationResult) {
        this.multiplicationResult = multiplicationResult;
    }

    public UploadFileResponse(ArrayList<ArrayList<Long>> multiplicationResult) {
        this.multiplicationResult = multiplicationResult;
        this.status = Status.UNKNOWN;
    }
}
