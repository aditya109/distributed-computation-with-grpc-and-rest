package io.github.models;

import org.javatuples.Pair;

import java.util.ArrayList;

public class ResponseMultiplyRowByColumnCallable {
    public ArrayList<Long> result;
    public Pair<Integer, Integer> elementLocation;

    public ResponseMultiplyRowByColumnCallable(ArrayList<Long> result, Pair<Integer, Integer> elementLocation) {
        this.result = result;
        this.elementLocation = elementLocation;
    }

    public ArrayList<Long> getResult() {
        return result;
    }

    public void setResult(ArrayList<Long> result) {
        this.result = result;
    }

    public Pair<Integer, Integer> getElementLocation() {
        return elementLocation;
    }

    public void setElementLocation(Pair<Integer, Integer> elementLocation) {
        this.elementLocation = elementLocation;
    }
}


