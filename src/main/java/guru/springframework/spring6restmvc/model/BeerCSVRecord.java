package guru.springframework.spring6restmvc.model;

import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerCSVRecord {
    @CsvBindByName
    private Integer row;
    @CsvBindByName(column = "count.x")
    private Integer count;
    @CsvBindByName
    private String abv;
    @CsvBindByName
    private String ibu;
    @CsvBindByName
    private Integer id;
    @CsvBindByName
    private String beer;
    @CsvBindByName
    private String style;
    @CsvBindByName(column = "brewery_id")
    private Integer breweryId;
    @CsvBindByName
    private Float ounces;
    @CsvBindByName
    private String style2;
    @CsvBindByName(column = "count.y")//If the name does not match then do this and put the exact
    // same name as the column name in the CSV file
    private String count_y;
    @CsvBindByName // if the same then all you can do this
    private String city;
    @CsvBindByName
    private String state;
    @CsvBindByName
    private String label;
}
