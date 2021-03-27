package io.github.core;

import io.github.utils.loader.MatrixLoader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ComputeEngine {
    private MatrixLoader matrixLoader;

    public void setupNonAsync() {
        final Path SERVER_BASE_PATH = Paths.get("src/test/resources");
        String file1Path = SERVER_BASE_PATH + "\\a.txt";
        String file2Path = SERVER_BASE_PATH + "\\b.txt";
        matrixLoader = new MatrixLoader();
        ArrayList<ArrayList<Long>> matrixA = matrixLoader.loadFileDataOntoMatrix(file1Path);
        System.out.println("=================================");
        System.out.println("Matrix A : 👇");
        displayMatrix(matrixA);
        System.out.println("=================================");

        ArrayList<ArrayList<Long>> matrixB = matrixLoader.loadFileDataOntoMatrix(file2Path);
        System.out.println("Initiating matrix transpose.... 👇");
        matrixB = returnTranspose(matrixB);
        System.out.println("Matrix B now : 👇");
        System.out.println("=================================");
        displayMatrix(matrixB);

        if (isMultiplicationPossible(matrixA, matrixB)) {
            // move ahead and trigger asynchronous row-column multiplications
            ArrayList<ArrayList<Long>> matrixC = multiplyMatrixToMatrix(matrixA, matrixB);    // resultant matrixC = matrixA * matrixB
            System.out.println("=================================");
            System.out.println("Printing the resultant matrix.... 👇");
            displayMatrix(matrixC);
            System.out.println("=================================");
        } else {
            // throw error response
            System.out.println("Error in input data; invalid dimensions");
        }
    }


    public boolean isMultiplicationPossible(ArrayList<ArrayList<Long>> matrixA, ArrayList<ArrayList<Long>> matrixB) {
        if (matrixA.size() == matrixB.get(0).size()) return true;
        else return false;
    }

    /**
     * multiplyMatrixToMatrix multiplies two matrices together and returns resultant matrix
     * matrixB - has to be transposed
     */
    public ArrayList<ArrayList<Long>> multiplyMatrixToMatrix(ArrayList<ArrayList<Long>> matrixA, ArrayList<ArrayList<Long>> matrixB) {
        ArrayList<ArrayList<Long>> matrixC = new ArrayList<>();     // resultant matrixC = matrixA * matrixB

        for (ArrayList<Long> row : matrixA) {
            matrixC.add(multiplyRowToMatrix(row, matrixB));
        }
        return matrixC;
    }

    /**
     * multiplyRowToMatrix multiplies a row to a matrix and returns resultant list
     */
    public ArrayList<Long> multiplyRowToMatrix(ArrayList<Long> row, ArrayList<ArrayList<Long>> matrix) {
        ArrayList<Long> resultantRow = new ArrayList<>();

        for (ArrayList<Long> rowInMatrix : matrix) {
            ArrayList<Long> intermediateRow = multiplyRowByColumn(row, rowInMatrix);
            long resultantSingularElement = addElementsInRow(intermediateRow);
            resultantRow.add(resultantSingularElement);
        }

        return resultantRow;
    }

    /**
     * MultiplyRowByColumnCallable multiplies a corresponding row elements to column elements and returns resultant list
     */
    public ArrayList<Long> multiplyRowByColumn(ArrayList<Long> row, ArrayList<Long> column) {


        ArrayList<Long> resultantMultipliedRow = new ArrayList<>();

        for (int i = 0; i < row.size(); i++) {
            resultantMultipliedRow.add(row.get(i) * column.get(i));
        }
        return resultantMultipliedRow;
    }

    /**
     * addCorrespondingRowElementsInMatrix adds all row elements in a matrix and returns resultant list
     */
    public ArrayList<Long> addCorrespondingRowElementsInMatrix(ArrayList<ArrayList<Long>> matrix) {
        ArrayList<Long> resultantRow = new ArrayList<>();
        for (ArrayList<Long> row : matrix) {
            resultantRow.add(addElementsInRow(row));
        }
        return resultantRow;
    }

    /**
     * addCorrespondingRowElementsInMatrix adds all row elements in a matrix and returns resultant list
     */
    public long addElementsInRow(ArrayList<Long> row) {
        long sum = 0l;
        for (long rowElement : row) {
            sum += rowElement;
        }
        return sum;
    }

    /**
     * returnTranspose returns the transpose of the input matrix
     */
    ArrayList<ArrayList<Long>> returnTranspose(ArrayList<ArrayList<Long>> table) {

        ArrayList<ArrayList<Long>> transposedList = new ArrayList<>();//list of list to hold transpose

        final int firstListSize = table.get(0).size();
        for (int i = 0; i < firstListSize; i++) {
            ArrayList<Long> tempList = new ArrayList<>();//Temp list to hold each transposed row which was column initially

            for (ArrayList<Long> row : table) { // iterate outer list to get sublist each time in the iteration
                tempList.add(row.get(i));//take sublist element at ith position each time and add it to temp list
            }

            //here col list have taken one one element from each element :
            //ex: during first iteration of outer loop, it takes 1st element from each sublist
            //during second iteration of outer loop, it takes 2nd element from each sublist
            transposedList.add(tempList);//each transposed list is added to transposedList
        }
        return transposedList;
    }

    /**
     * displayList displays a 1-D array
     */
    public void displayList(ArrayList<Long> list) {
        for (long element : list) {
            System.out.print(element + " ");
        }
    }

    /**
     * displayMatrix displays a 2-D array
     */
    public void displayMatrix(ArrayList<ArrayList<Long>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            displayList(matrix.get(i));
            System.out.println();
        }
    }
}
