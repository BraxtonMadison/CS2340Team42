package cs.gtstudent.zwaste;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecyView;
    private RecyclerView.LayoutManager itemsRecyManager;
    private ItemRecyViewAdapter adapter;

    private ImageView searchLogo;

    private PopupWindow searchPopUp;
    private EditText editSearchItemData;
    private RadioGroup searchType;

    LocationRecyViewItem locationRecyViewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        locationRecyViewItem = (LocationRecyViewItem) getIntent().getExtras().get("LOCATION_DATA");

        itemsRecyView = findViewById(R.id.itemsRecyView);
        itemsRecyView.setHasFixedSize(true);
        itemsRecyManager = new LinearLayoutManager(this);
        itemsRecyView.setLayoutManager(itemsRecyManager);

        adapter = new ItemRecyViewAdapter(populateWithItems(locationRecyViewItem));
        itemsRecyView.setAdapter(adapter);

        searchLogo = findViewById(R.id.searchLogo);
        searchLogo.bringToFront();

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
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
                searchPopUp.setWidth(width - 200);
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

    private List<ItemRecyViewItem> populateWithItems(LocationRecyViewItem locationData) {
        final List<ItemRecyViewItem> stuff = new ArrayList<>();
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference()
                .child("locations")
                .child(locationData.getLocationName())
                .child("items");
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    stuff.add((ItemRecyViewItem)(ds.getValue(ItemRecyViewItem.class)));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        locationRecyViewItem.getLocationData().setItems(stuff);
        return stuff;
    }

    private void filterItemsWithCondition(String data, int id) {
        // Get all itemRecyViewItems of this location
        // Get their names or category
        // Re define list of items

        List<ItemRecyViewItem> itemsHere = locationRecyViewItem.getLocationData().getItems();
        List<ItemRecyViewItem> itemsConditionFit = new ArrayList<>();
        if (itemsHere.size() == 0) return;

        switch (id) {
            case R.id.searchWithName:
                for (ItemRecyViewItem item : itemsHere) {
                    String itemProperty = item.getItemName();
                    if (itemProperty.contains(data)) {
                        itemsConditionFit.add(item);
                    }
                }
                break;
            case R.id.searchWithCategory:
                for (ItemRecyViewItem item : itemsHere) {
                    String itemProperty = item.getItemType();
                    if (itemProperty.contains(data)) {
                        itemsConditionFit.add(item);
                    }
                }
                break;
        }
        adapter = new ItemRecyViewAdapter(itemsConditionFit);
        itemsRecyView.setAdapter(adapter);
    }
}
