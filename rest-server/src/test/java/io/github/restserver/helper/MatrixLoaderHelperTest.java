package io.github.restserver.helper;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixLoaderHelperTest {

    private String filepath;
    private PathProvider pathProvider;
    private MatrixLoaderHelper matrixLoaderHelper;

    public MatrixLoaderHelperTest() {
        this.filepath = "";
        pathProvider = new PathProvider();
        matrixLoaderHelper = new MatrixLoaderHelper();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Checking if the the loaded file gives correct matrix or not")
    void checkIfLoadFileDataOntoMatrixForValidity() {
        filepath = pathProvider.provideStoragePath() + File.separator + "a.txt";
        ArrayList<ArrayList<Long>> actualMatrix =  matrixLoaderHelper.loadFileDataOntoMatrix(filepath);
        ArrayList<ArrayList<Long>> expectedMatrix = new ArrayList<>();
        long count = 1L;
        for (int i = 0; i < 4; i++) {
            ArrayList<Long> row = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                row.add(count++);
            }
            expectedMatrix.add(row);
        }
        Assertions.assertTrue(actualMatrix.equals(expectedMatrix));

    }
}