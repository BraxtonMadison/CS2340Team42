package cs.gtstudent.zwaste;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class FilterItemsTests {
    List<ItemRecycleViewItem> itemsHere;
    CharSequence data;

    @Before
    public void setup() {
        itemsHere = new ArrayList<>();
        itemsHere.add(new ItemRecycleViewItem(0, "", "Ikea bed", "Furniture"));
        itemsHere.add(new ItemRecycleViewItem(0, "", "Ethan Allen couch", "Furniture"));
        itemsHere.add(new ItemRecycleViewItem(0, "", "Twin size bedding", "Blankets, towels, etc."));
        itemsHere.add(new ItemRecycleViewItem(0, "", "Beach towel", "Blankets, towels, etc."));
        itemsHere.add(new ItemRecycleViewItem(0, "", "Coffee table", "Furniture"));
        itemsHere.add(new ItemRecycleViewItem(0, "", "Coffee mug", "Plates, cups, etc."));
    }

    @Test
    public void testFilterItems01ByName() {
        data = "bed";
        ArrayList<ItemRecycleViewItem> expected = new ArrayList<>();
        expected.add(new ItemRecycleViewItem(0, "", "Ikea bed", "Furniture"));
        expected.add(new ItemRecycleViewItem(0, "", "Twin size bedding", "Blankets, towels, etc."));

        List<ItemRecycleViewItem> actual = FilterItems.filterItems(itemsHere, data, true);
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterItems02ByName() {
        data = "Coffee";
        ArrayList<ItemRecycleViewItem> expected = new ArrayList<>();
        expected.add(new ItemRecycleViewItem(0, "", "Coffee table", "Furniture"));
        expected.add(new ItemRecycleViewItem(0, "", "Coffee mug", "Plates, cups, etc."));

        List<ItemRecycleViewItem> actual = FilterItems.filterItems(itemsHere, data, true);
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterItems03ByType() {
        data = "Blankets, towels, etc.";
        ArrayList<ItemRecycleViewItem> expected = new ArrayList<>();
        expected.add(new ItemRecycleViewItem(0, "", "Twin size bedding", "Blankets, towels, etc."));
        expected.add(new ItemRecycleViewItem(0, "", "Beach towel", "Blankets, towels, etc."));

        List<ItemRecycleViewItem> actual = FilterItems.filterItems(itemsHere, data, false);
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterItems04ByType() {
        data = "Furniture";
        ArrayList<ItemRecycleViewItem> expected = new ArrayList<>();
        expected.add(new ItemRecycleViewItem(0, "", "Ikea bed", "Furniture"));
        expected.add(new ItemRecycleViewItem(0, "", "Ethan Allen couch", "Furniture"));
        expected.add(new ItemRecycleViewItem(0, "", "Coffee table", "Furniture"));

        List<ItemRecycleViewItem> actual = FilterItems.filterItems(itemsHere, data, false);
        assertEquals(expected, actual);
    }
}
