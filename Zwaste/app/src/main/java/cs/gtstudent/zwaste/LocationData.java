package cs.gtstudent.zwaste;

import java.io.Serializable;

/**
 * Model class that holds information about the location.
 */
class LocationData implements Serializable {

    private final String locationName;
    private final String locationType;
    private final String longitude;
    private final String latitude;
    private final String address;
    private final String phoneNum;

    /**
     * Default constructor of LocationData.
     * Serves no significant purpose but to let this class allowed to be uploaded to
     * Firebase.
     */
    public LocationData() {
        this.locationName = "";
        this.locationType = "";
        this.longitude = "";
        this.latitude = "";
        this.address = "";
        this.phoneNum = "";
    }

    /**
     * Constructor for LocationData. Receives all needed information to
     * describe the location.
     * @param locationName Name of the location.
     * @param locationType Type of the location.
     * @param longitude Longitude of the location
     * @param latitude Latitude of the location.
     * @param address Address of the location.
     * @param phoneNum Contact number of the location.
     */
    public LocationData(String locationName, String locationType,
                        String longitude, String latitude, String address, String phoneNum) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    /**
     * Constructor for LocationData class, but received data are in an array.
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
    }
    public String toString() {
        return "Location: " + locationName + "\n"
                + "Location Type: " + locationType + "\n"
                + "Longitude: " + longitude + "\n"
                + "Latitude: " + latitude + "\n"
                + "Address: " + address + "\n"
                + "Phone Number: " + phoneNum;
    }

    /**
     * Getter method for Location Name.
     * @return name of the location.
     */
    public String getLocationName() { return locationName; }

    /**
     * Getter method for location type.
     * @return type of the location.
     */
    public CharSequence getLocationType() { return locationType; }

    /**
     * Getter method for longitude.
     * @return longitude of the location.
     */
    public String getLongitude() { return longitude; }

    /**
     * Getter method for latitude.
     * @return latitude of the location.
     */
    public String getLatitude() { return latitude; }

    /**
     * Getter method for address of the location.
     * @return address of the location.
     */
    public String getAddress() { return address; }

    /**
     * Getter method for contact number of the location.
     * @return Contact phone number of the location.
     */
    public String getPhoneNum() { return phoneNum; }
}