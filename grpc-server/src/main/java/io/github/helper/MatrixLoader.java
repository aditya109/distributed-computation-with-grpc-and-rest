package io.github.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixLoader {
    public List<List<Long>> loadMatrix(List<String> m) {
//        "1, 2, 3"
        List<List<Long>> matrix = null;
        for (String r: m) {
            List<Long> row = Arrays.stream(r.split(",")).map(Long::valueOf).collect(Collectors.toList());
            matrix.add(row);
        }
        return matrix;
    }
}
