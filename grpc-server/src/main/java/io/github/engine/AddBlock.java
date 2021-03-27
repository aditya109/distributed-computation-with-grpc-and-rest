package io.github.engine;

import java.util.List;

public class AddBlock {
    public List<Long> row;

    public AddBlock(List<Long> row) {
        this.row = row;
    }

    public long performCompute() {
        long sum = 0l;
        for (long rowElement : row) {
            sum += rowElement;
        }
        return sum;
    }
}
