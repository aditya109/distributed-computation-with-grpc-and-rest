package io.github.restserver.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ComputeService {
    public boolean isMultiplicationPossible(ArrayList<ArrayList<Long>> matrixA, ArrayList<ArrayList<Long>> matrixB) {
        boolean isMatrixAHasSizeInPowersOfTwo = (matrixA.size() & matrixA.size() - 1) == 0; // bitwise check ;)
        boolean isMatrixBHasSizeInPowersOfTwo = (matrixB.size() & matrixB.size() - 1) == 0; // bitwise check ;)
        if (matrixA.size() == matrixB.get(0).size()
                && isMatrixAHasSizeInPowersOfTwo
                && isMatrixBHasSizeInPowersOfTwo)
            return true;
        else return false;
    }

    /**
     * returnTranspose returns the transpose of the input matrix
     */
    public ArrayList<ArrayList<Long>> returnTranspose(ArrayList<ArrayList<Long>> table) {

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
