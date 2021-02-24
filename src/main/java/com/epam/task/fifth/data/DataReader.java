package com.epam.task.fifth.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataReader {

    private static final String DELIMITER = "\n";

    public String readData(String filePath) throws DataException {

        StringBuilder data = new StringBuilder();
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                data.append(line + DELIMITER);
                line = bufferedReader.readLine();
            }


        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new DataException(e.getMessage(), e);
            }

        }

        return data.toString();
    }
}
