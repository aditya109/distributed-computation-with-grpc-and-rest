package io.github.engine;

import java.util.ArrayList;
import java.util.List;

public class MultiplyBlock {
    public List<Long> row;
    public List<Long> column;

    public MultiplyBlock(List<Long> row, List<Long> column) {
        this.row = row;
        this.column = column;
    }

    public List<Long> performCompute() {
        ArrayList<Long> resultantMultipliedRow = new ArrayList<>();
        for (int i = 0; i < row.size(); i++) {
            resultantMultipliedRow.add(row.get(i) * column.get(i));
        }
        return resultantMultipliedRow;
    }
}
