package cs.gtstudent.zwaste;

import java.io.Serializable;

public class ItemRecyViewItem implements Serializable {
    private int imageID;
    private String itemInfo;

    public ItemRecyViewItem (int imageID, String itemInfo) {
        this.imageID = imageID;
        this.itemInfo = itemInfo;
    }

    public int getImageID() {
        return imageID;
    }
    public String getItemInfo() {
        return itemInfo;
    }
}
