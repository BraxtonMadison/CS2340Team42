package cs.gtstudent.zwaste;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for showing items
 * that the location user selected holds.
 */
public class ShowItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecycleView;
    private RecyclerView.LayoutManager itemsRecycleManager;
    private ItemRecycleViewAdapter adapter;

    private ImageView searchLogo;

    private PopupWindow searchPopUp;
    private EditText editSearchItemData;
    private RadioGroup searchType;

    private LocationRecycleViewItem locationRecycleViewItem;
    private List<ItemRecycleViewItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        locationRecycleViewItem = (LocationRecycleViewItem) getIntent().getExtras().get("LOCATION_DATA");

        itemsRecycleView = findViewById(R.id.itemsRecyView);
        itemsRecycleView.setHasFixedSize(true);
        itemsRecycleManager = new LinearLayoutManager(this);
        itemsRecycleView.setLayoutManager(itemsRecycleManager);

        items = populateWithItems();
        adapter = new ItemRecycleViewAdapter(items);
        itemsRecycleView.setAdapter(adapter);

        searchLogo = findViewById(R.id.searchLogo);
        searchLogo.bringToFront();

        LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                                        .getSystemService(LAYOUT_INFLATER_SERVICE);
        final View customView = inflater.inflate(R.layout.search_item_bar_style,null);
        //Spinner itemSearchOptions = customView.findViewById(R.id.itemSearchOptions);
        editSearchItemData = customView.findViewById(R.id.editSearchItemData);
        editSearchItemData.setHint("Item");

        searchType = customView.findViewById(R.id.searchType);

        searchLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Appear search bar, with options, and enter button
                Point size = new Point();
                Display display = getWindowManager().getDefaultDisplay();
                display.getSize(size);
                int width = size.x;

                searchPopUp = new PopupWindow(customView);
                searchPopUp.setWidth(width - (int)(0.2*width));
                searchPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                searchPopUp.setFocusable(true);

                searchPopUp.showAtLocation(view, Gravity.CENTER,0,0);
            }
        });

        editSearchItemData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int selectedRadioBtn = searchType.getCheckedRadioButtonId();
                String data = editSearchItemData.getText().toString().trim().toLowerCase();
                filterItemsWithCondition(data, selectedRadioBtn);
            }
        });
    }

    private List<ItemRecycleViewItem> populateWithItems() {
        final List<ItemRecycleViewItem> stuff = new ArrayList<>();
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference()
                .child("items");
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String locationName = locationRecycleViewItem.getLocationName();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(ItemRecycleViewItem.class).getLocation()
                                .equals(locationName)) {
                        stuff.add((ds.getValue(ItemRecycleViewItem.class)));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return stuff;
    }

    private void filterItemsWithCondition(CharSequence data, int id) {
        // Get all itemRecycleViewItems of this location
        // Get their names or category
        // Re define list of items

        List<ItemRecycleViewItem> itemsHere = items;
        List<ItemRecycleViewItem> itemsConditionFit = new ArrayList<>();
        if (itemsHere.isEmpty()) { return; }

        switch (id) {
            case R.id.searchWithName:
                for (ItemRecycleViewItem item : itemsHere) {
                    String itemProperty = item.getItemName();
                    if (itemProperty.contains(data)) {
                        itemsConditionFit.add(item);
                    }
                }
                break;
            case R.id.searchWithCategory:
                for (ItemRecycleViewItem item : itemsHere) {
                    String itemProperty = item.getItemType();
                    if (itemProperty.contains(data)) {
                        itemsConditionFit.add(item);
                    }
                }
                break;
        }
        adapter = new ItemRecycleViewAdapter(itemsConditionFit);
        itemsRecycleView.setAdapter(adapter);
    }
}
