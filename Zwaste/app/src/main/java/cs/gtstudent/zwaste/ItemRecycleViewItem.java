package cs.gtstudent.zwaste;

import java.io.Serializable;

/**
 * Model class responsible for representing item data in a ListView.
 *
 */
class ItemRecycleViewItem implements Serializable {
    private final int imageID;
    private final String location;
    private String itemName;
    private String itemType;

    /**
     * Default constructor of ItemRecycleViewItem class.
     */
    public ItemRecycleViewItem() {
        this.imageID = R.id.titleLogo;
        this.location = "";
        this.itemName = "";
        this.itemName = "";
    }

    /**
     * Constructor that receives information that describes the item.
     * @param imageID Temporary placeholder, as a representation of image data of the item.
     *                Will be replaced with Firebase implementation in the future.
     * @param location Name of the location where this item belongs.
     * @param itemName Name of the item.
     * @param itemType Type of the item.
     */
    public ItemRecycleViewItem (int imageID, String location, String itemName, String itemType) {
        this.imageID = imageID;
        this.location = location;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    /**
     * Getter method of the imageID. Temporary placeholder for functionality
     * that connects to the Firebase to retrieve image data of this item.
     * @return Resource id of the image of this item.
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * Getter method for the name of the location where this item belongs.
     * @return Name of the location where this item belongs.
     */
    public String getLocation() { return location; }

    /**
     * Getter method for item name.
     * @return Name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Getter method for item type / category.
     * @return Type / category of the item.
     */
    public String getItemType() { return itemType; }
}
