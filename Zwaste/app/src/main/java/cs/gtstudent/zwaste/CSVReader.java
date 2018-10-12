package cs.gtstudent.zwaste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private InputStream is;
    private BufferedReader bf;


    public CSVReader(InputStream is) {
        this.is = is;
    }

    public List<LocationData> read() {
        List<LocationData> readData = new ArrayList<>();
        bf = new BufferedReader(new InputStreamReader(is));
        try {
            String csvLine;
            while ((csvLine = bf.readLine()) != null) {
                String[] row = csvLine.split(",");
                readData.add(new LocationData(row));
            }
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return readData;
    }

}
