package cs.gtstudent.zwaste;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

public class LocationRecyViewItem implements Serializable{
    private int imageID;

    private LocationData locData;

    public LocationRecyViewItem(int imageID, LocationData locData) {
        this.imageID = imageID;
        this.locData = locData;
    }

    public int getImageID() {
        return imageID;
    }

    public String getLocationName() {
        return locData.getLocationName();
    }

    public String getLocationType() {
        return locData.getLocationType();
    }

    public LocationData getLocationData() {
        return locData;
    }

}
