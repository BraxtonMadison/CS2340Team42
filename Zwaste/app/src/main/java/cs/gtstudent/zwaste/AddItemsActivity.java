package cs.gtstudent.zwaste;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Controller class responsible for adding a donated good(s) to the location.
 * Added item is updated onto Firebase.
 */
public class AddItemsActivity extends AppCompatActivity {

    private EditText addItemName;
    private EditText addItemType;
    private Button submitItemButton;

    private LocationRecycleViewItem locationRecycleViewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        addItemName = findViewById(R.id.addItemName);
        addItemType = findViewById(R.id.addItemType);
        submitItemButton = findViewById(R.id.submitItem);

        locationRecycleViewItem = (LocationRecycleViewItem) getIntent()
                                            .getExtras().get("LOCATION_DATA");


        submitItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = addItemName.getText().toString().trim();
                String itemType = addItemType.getText().toString().trim();
                int imageID = R.drawable.title_logo;

                if (!itemName.isEmpty() && !itemType.isEmpty()) {
                    ItemRecycleViewItem newItem = new ItemRecycleViewItem(imageID,
                                    locationRecycleViewItem.getLocationName(), itemName, itemType);

                    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();
                    dbr.child("items")
                            .child(newItem.getItemName())
                            .setValue(newItem)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(),
                                    "Adding complete", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                } else {
                    Toast.makeText(view.getContext(),
                            "Please fill both Item name and its type",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
