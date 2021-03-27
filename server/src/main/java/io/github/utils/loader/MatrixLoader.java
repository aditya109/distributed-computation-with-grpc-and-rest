package io.github.utils.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixLoader {
    public ArrayList<ArrayList<Long>> loadFileDataOntoMatrix(String filePath) {
        ArrayList<ArrayList<Long>> matrix = new ArrayList<ArrayList<Long>>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                ArrayList<Long> row = new ArrayList<Long>();
                String line = scanner.nextLine();
                String[] rowElementsInString = line.split(" ");

                for (String e : rowElementsInString) {
                    if (e.length() > 0) {
                        long element = Long.parseLong(e);
                        row.add(element);
                    }
                }
                matrix.add(row);
            }
            scanner.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return matrix;
    }
}
