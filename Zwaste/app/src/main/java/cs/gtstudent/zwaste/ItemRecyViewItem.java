package cs.gtstudent.zwaste;

import android.content.ClipData;

import java.io.Serializable;

public class ItemRecyViewItem implements Serializable {
    private int imageID;
    private String location;
    private String itemName;
    private String itemType;

    public ItemRecyViewItem() {
        this.imageID = R.id.titleLogo;
        this.location = "";
        this.itemName = "";
        this.itemName = "";
    }

    public ItemRecyViewItem (int imageID, String location, String itemName, String itemType) {
        this.imageID = imageID;
        this.location = location;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public int getImageID() {
        return imageID;
    }
    public String getLocation() { return location; }
    public String getItemName() {
        return itemName;
    }
    public String getItemType() { return itemType; }
}
