package io.github.core.tasks;

import io.github.models.ResponseAddElementsInRowCallable;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class AddElementsInRowCallable implements Callable<ResponseAddElementsInRowCallable> {
    public ArrayList<Long> row;
    public Pair<Integer, Integer> elementLocation;

    public AddElementsInRowCallable(ArrayList<Long> row, Pair<Integer, Integer> elementLocation) {
        this.row = row;
        this.elementLocation = elementLocation;
    }

    @Override
    public ResponseAddElementsInRowCallable call() throws Exception {
        long sum = 0l;
        ResponseAddElementsInRowCallable response = null;
        for (long rowElement : row) {
            sum += rowElement;
        }
        response.setElementLocation(elementLocation);
        response.setElement(sum);
        return response;
    }
}
