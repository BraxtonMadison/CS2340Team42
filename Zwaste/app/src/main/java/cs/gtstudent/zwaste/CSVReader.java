package cs.gtstudent.zwaste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for reading raw CSV file that holds information about donation locations
 * near Atlanta.
 * With all information already migrated to the Firebase, this class is only being used for
 * debugging purposes.
 */
class CSVReader {
    private final InputStream is;


    /**
     * Constructor for CSVReader class.
     * @param is instance of InputStream. Holds file location of raw data that will be read.
     */
    public CSVReader(InputStream is) {
        this.is = is;
    }

    /**
     * Reads the raw file, and parse the read information into an array.
     * @return List of instances of LocationData.
     */
    public List<LocationData> read() {
        List<LocationData> readData = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        try {
            String csvLine = bf.readLine();
            while (csvLine != null) {
                String[] row = csvLine.split(",");
                readData.add(new LocationData(row));
                csvLine = bf.readLine();
            }
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return readData;
    }

}
