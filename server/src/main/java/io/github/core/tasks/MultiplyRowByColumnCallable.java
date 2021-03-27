package io.github.core.tasks;

import io.github.models.ResponseMultiplyRowByColumnCallable;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MultiplyRowByColumnCallable implements Callable<ResponseMultiplyRowByColumnCallable> {
    public ArrayList<Long> row;
    public ArrayList<Long> column;
    public Pair<Integer, Integer> elementLocation;

    public MultiplyRowByColumnCallable(ArrayList<Long> row, ArrayList<Long> column, int rowId, int columnId) {
        this.row = row;
        this.column = column;
        this.elementLocation = Pair.with(rowId, columnId);
    }

    @Override
    public ResponseMultiplyRowByColumnCallable call() throws Exception {
        ArrayList<Long> resultantMultipliedRow = new ArrayList<>();
        ResponseMultiplyRowByColumnCallable response = null;
        for (int i = 0; i < row.size(); i++) {
            resultantMultipliedRow.add(row.get(i) * column.get(i));
        }
        response.setResult(resultantMultipliedRow);
        response.setElementLocation(elementLocation);

        return response;
    }


}
