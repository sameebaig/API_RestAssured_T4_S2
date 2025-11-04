package api.utils;

import org.testng.annotations.DataProvider;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVReader;

public class DataProviders {

    @DataProvider(name = "userIds")
    public static Iterator<Object[]> getUserIds() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("src/test/resources/user_ids.csv"));
        List<String[]> rows = reader.readAll();
        rows.remove(0);
        return rows.stream().map(r -> new Object[]{Integer.parseInt(r[0])}).iterator();
    }
}
