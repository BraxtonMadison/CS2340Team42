package cs.gtstudent.zwaste;

import java.util.List;
import java.util.ArrayList;

public class FilterItems {
    public static List<ItemRecycleViewItem> filterItems(List<ItemRecycleViewItem> itemsHere, CharSequence data, boolean byName) {
        List<ItemRecycleViewItem> itemsConditionFit = new ArrayList<>();
        for (ItemRecycleViewItem item : itemsHere) {
            String itemProperty = byName ? item.getItemName() : item.getItemType();
            if (itemProperty.contains(data)) {
                itemsConditionFit.add(item);
            }
        }
        return itemsConditionFit;
    }
}
