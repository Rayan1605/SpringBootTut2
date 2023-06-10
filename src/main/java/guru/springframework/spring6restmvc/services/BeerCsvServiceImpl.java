package guru.springframework.spring6restmvc.services;

import com.opencsv.bean.CsvToBeanBuilder;
import guru.springframework.spring6restmvc.model.BeerCSVRecord;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
@Service
public class BeerCsvServiceImpl implements BeerCsvService {

    @Override
    public List<BeerCSVRecord> convertCSV(File file) {
        // the convertCSV method takes a File object as input, reads the contents of the file
        // using a CsvToBean parser, and returns a list of BeerCSVRecord objects representing
        // the parsed data.


        List<BeerCSVRecord> beerCSVRecords = null;
        try {
            beerCSVRecords = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(file)
                    ).withType(BeerCSVRecord.class).build().parse();
            //It creates a new CsvToBeanBuilder object, which is used to parse CSV files
            // into Java objects.
            //It takes a FileReader object as a parameter, which reads the contents of the file.
            //It specifies the type of objects to be created from the CSV records as BeerCSVRecord.class
            //It builds the CsvToBean object and parses the CSV file, returning a list of
            // BeerCSVRecord objects.
            //The list of BeerCSVRecord objects is then assigned to the beerCSVRecords variable.
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return beerCSVRecords;
    }
}
