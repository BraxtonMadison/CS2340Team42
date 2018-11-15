package cs.gtstudent.zwaste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Controller class responsible for displaying
 * options for the user to either add an item or to
 * see available items in the location.
 * @see ShowItemsActivity
 * @see AddItemsActivity
 */
public class ShowOptionsActivity extends AppCompatActivity {

    private TextView showItems;
    private TextView addItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_options);

        final Serializable locationData = (LocationRecycleViewItem) getIntent()
                                                        .getExtras().get("LOCATION_DATA");
        //final Collection<ItemRecycleViewItem> items = new ArrayList<>();

        /*
        db.getReference()
                .child("locations")
                .child(hashedLocationName)
                .child("items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    items.add(ds.getValue(ItemRecycleViewItem.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });*/

        showItems = findViewById(R.id.showItems);
        addItems = findViewById(R.id.addItems);

        showItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowItemsActivity.class);
                intent.putExtra("LOCATION_DATA", locationData);
                startActivity(intent);
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddItemsActivity.class);
                intent.putExtra("LOCATION_DATA", locationData);
                startActivity(intent);
            }
        });
    }
}
