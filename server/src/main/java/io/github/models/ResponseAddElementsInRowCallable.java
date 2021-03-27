package io.github.models;

import org.javatuples.Pair;

public class ResponseAddElementsInRowCallable {
    public long element;
    public Pair<Integer, Integer> elementLocation;

    public ResponseAddElementsInRowCallable(long element, Pair<Integer, Integer> elementLocation) {
        this.element = element;
        this.elementLocation = elementLocation;
    }

    public long getElement() {
        return element;
    }

    public void setElement(long element) {
        this.element = element;
    }

    public Pair<Integer, Integer> getElementLocation() {
        return elementLocation;
    }

    public void setElementLocation(Pair<Integer, Integer> elementLocation) {
        this.elementLocation = elementLocation;
    }
}
