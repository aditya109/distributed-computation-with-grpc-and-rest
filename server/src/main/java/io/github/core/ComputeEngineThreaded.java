package io.github.core;

import io.github.core.tasks.MultiplyRowByColumnCallable;
import io.github.models.ResponseAddElementsInRowCallable;
import io.github.models.ResponseMultiplyRowByColumnCallable;
import io.github.utils.loader.MatrixLoader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ComputeEngineThreaded {

    private MatrixLoader matrixLoader;

    public void setup() {
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
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Set<Future<ResponseMultiplyRowByColumnCallable>> rows = new HashSet<>();

        ArrayList<ArrayList<Long>> matrixC = new ArrayList<>();     // resultant matrixC = matrixA * matrixB
        int iterativeLimit = matrixA.size();

        for (int i = 0; i < iterativeLimit; i++) {
            for (int j = 0; j < iterativeLimit; j++) {

                Callable<ResponseMultiplyRowByColumnCallable> row =
                        new MultiplyRowByColumnCallable(
                          matrixA.get(i),
                          matrixB.get(j),
                          i,
                          j
                        );
                Future future = pool.submit(row);
                rows.add(future);
            }
        }



        return matrixC;
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

/**

 class WordLengthCallable implements Callable<Integer> {

 public String word;

 public WordLengthCallable(String word) {
 this.word = word;
 }

 @Override
 public Integer call() throws Exception {
 try {
 Thread.sleep(1500);
 } catch (InterruptedException e) {
 System.out.println(e);
 }
 return word.length();
 }
 }

 */