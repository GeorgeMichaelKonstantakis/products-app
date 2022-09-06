import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    private String filepath = "test_results.csv";

    public CsvWriter() {
    }

    public void toCsv(ResultsCsv resultsCsv) {

        File file = new File(filepath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            String[] data = {resultsCsv.getTestName(), String.valueOf(resultsCsv.getTestPassed()), resultsCsv.getTestResultMessage(), resultsCsv.getTimestamp()};
            writer.writeNext(data);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
