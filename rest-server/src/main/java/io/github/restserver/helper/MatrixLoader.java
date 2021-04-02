package io.github.restserver.helper;

import io.github.restserver.middleware.LoggerProvider;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class MatrixLoader {
    private Logger logger;

    public MatrixLoader() {
        this.logger = new LoggerProvider(MatrixLoader.class).provideLoggerInstance();
    }

    public ArrayList<ArrayList<Long>> loadFileDataOntoMatrix(String filePath) {
        ArrayList<ArrayList<Long>> matrix = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                ArrayList<Long> row = new ArrayList<>();
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
            logger.error("error occurred while loading into output matrix");
        }
        return matrix;
    }
}
