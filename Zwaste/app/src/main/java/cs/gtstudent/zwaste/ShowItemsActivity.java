package cs.gtstudent.zwaste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecyView;
    private RecyclerView.LayoutManager itemsRecyManager;
    private ItemRecyViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        final LocationRecyViewItem locationData = (LocationRecyViewItem) getIntent().getExtras().get("LOCATION_DATA");

        itemsRecyView = findViewById(R.id.itemsRecyView);
        itemsRecyView.setHasFixedSize(true);
        itemsRecyManager = new LinearLayoutManager(this);
        itemsRecyView.setLayoutManager(itemsRecyManager);

        adapter = new ItemRecyViewAdapter(populateWithItems(locationData));
        itemsRecyView.setAdapter(adapter);
    }

    private List<ItemRecyViewItem> populateWithItems(LocationRecyViewItem locationData) {
        List<ItemRecyViewItem> stuff = locationData.getLocationData().getItems();
        if (stuff == null) {
            Log.i("Info: ", "NULL!");
        }
        return locationData.getLocationData().getItems();
    }
}
