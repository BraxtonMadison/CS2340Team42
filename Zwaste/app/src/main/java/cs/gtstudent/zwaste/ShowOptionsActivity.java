package cs.gtstudent.zwaste;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowOptionsActivity extends AppCompatActivity {

    private TextView showItems;
    private TextView addItems;
    LocationRecyViewItem locationData;

    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_options);

        final LocationRecyViewItem locationData = (LocationRecyViewItem) getIntent().getExtras().get("LOCATION_DATA");
        this.locationData = locationData;
        final List<ItemRecyViewItem> items = new ArrayList<>();

        db = FirebaseDatabase.getInstance();

        db.getReference()
                .child("locations")
                .child(locationData.getLocationName())
                .child("items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    items.add(ds.getValue(ItemRecyViewItem.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



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
                startActivityForResult(intent, 2337);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 2337) {
            if(resultCode == Activity.RESULT_OK){
                ItemRecyViewItem newItem = (ItemRecyViewItem) intent.getExtras().get("newItem");
                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();
                dbr.child("locations")
                        .child(locationData.getLocationName())
                        .child("items")
                        .child(newItem.getItemName())
                        .setValue(newItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Adding complete", Toast.LENGTH_SHORT);
                    }
                });
                locationData.getLocationData().addItem(newItem);
            }
        }
    }
}
