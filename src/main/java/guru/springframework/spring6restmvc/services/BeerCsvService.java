package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File file); //this is the call that will convert the CSV file into a
    // list of BeerCSVRecord objects. so we can add to our database
}
