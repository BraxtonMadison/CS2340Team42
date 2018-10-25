package cs.gtstudent.zwaste;

import java.io.Serializable;

public class ItemRecyViewItem implements Serializable {
    private int imageID;
    private String itemName;
    private String itemType;

    public ItemRecyViewItem (int imageID, String itemName, String itemType) {
        this.imageID = imageID;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public int getImageID() {
        return imageID;
    }
    public String getItemName() {
        return itemName;
    }
    public String getItemType() { return itemType; }
}
