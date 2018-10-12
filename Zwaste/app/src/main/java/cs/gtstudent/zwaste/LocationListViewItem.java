package cs.gtstudent.zwaste;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class LocationListViewItem {
    private Drawable imageID;
    private String locationName;
    private String locationType;

    private LocationData locData;

    public Drawable getImageID() {
        return imageID;
    }

    public void setImageID(Drawable imageID) {
        this.imageID = imageID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public LocationData getLocationData() {
        return locData;
    }

    public void setLocationData(LocationData locData) {
        this.locData = locData;
    }
}
