package cs.gtstudent.zwaste;

import java.io.Serializable;

/**
 * Model class, responsible for representing LocationData in a ListView.
 * While this class may seem redundant by having LocationData class,
 * this is a temporary class made to ease the process of migrating image files
 * to the Firebase.
 */

class LocationRecycleViewItem implements Serializable{
    private final int imageID;
    private final LocationData locData;

    /**
     * Receives imageID for representing image for the location, and
     *  data of the location.
     * @param imageID id number of placeholder image for the location.
     *                This will be replaced with Uri or other data type
     *                that is bound to Firebase database.
     * @param locData Instance which holds information about the location.
     */
    public LocationRecycleViewItem(int imageID, LocationData locData) {
        this.imageID = imageID;
        this.locData = locData;
    }

    /**
     * Getter method for imageID.
     * @return id number of placeholder image for the location.
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * Getter method for location name.
     * @return Name of the location.
     */
    public String getLocationName() {
        return locData.getLocationName();
    }

    /**
     * Getter method for location type.
     * @return Type of the location, like Drop off, warehouse, etc.
     */
    public CharSequence getLocationType() {
        return locData.getLocationType();
    }

}
