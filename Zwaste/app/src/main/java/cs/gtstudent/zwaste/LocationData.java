package cs.gtstudent.zwaste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cs.gtstudent.zwaste.ItemRecyViewItem;

public class LocationData implements Serializable {

    private String locationName;
    private String locationType;
    private String longitude;
    private String latitude;
    private String address;
    private String phoneNum;

    private List<ItemRecyViewItem> itemData;

    public LocationData() {
    }

    public LocationData(String locationName, String locationType, String longitude, String latitude, String address, String phoneNum) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNum = phoneNum;
        itemData = new ArrayList<>();
    }

    /**
     *
     * @param data Unprocessed data from csv file;
     *             address must be made by adding street address, city, state, and zip code.
     */
    public LocationData(String[] data) {
        this.locationName = data[1];
        this.latitude = data[2];
        this.longitude = data[3];
        this.address = data[4] + ", " + data[5] + ", "+ data[6] + ", " + data[7];
        this.locationType = data[8];
        this.phoneNum = data[9];
        itemData = new ArrayList<>();
    }
    public String toString() {
        return "Location: " + locationName + "\n"
                + "Location Type: " + locationType + "\n"
                + "Longitude: " + longitude + "\n"
                + "Latitude: " + latitude + "\n"
                + "Address: " + address + "\n"
                + "Phone Number: " + phoneNum;
    }

    public String getLocationName() {
        return locationName;
    }
    public String getLocationType() {
        return locationType;
    }
    public String getLongitude() { return longitude; }
    public String getLatitude() { return latitude; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }

    public void addItem(ItemRecyViewItem item) {
        itemData.add(item);
    }
    public List<ItemRecyViewItem> getItems () {
        return itemData;
    }

    public void setItems(List<ItemRecyViewItem> items) { itemData = items; }
}