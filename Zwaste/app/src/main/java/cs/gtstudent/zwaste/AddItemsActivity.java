package cs.gtstudent.zwaste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AddItemsActivity extends AppCompatActivity {

    private TextView instruction;
    private EditText addItemName;
    private EditText addItemType;
    private Button submitItemButton;

    private LocationRecyViewItem locationRecyViewItem;

    private FirebaseAuth auth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        instruction = findViewById(R.id.instruction);
        addItemName = findViewById(R.id.addItemName);
        addItemType = findViewById(R.id.addItemType);
        submitItemButton = findViewById(R.id.submitItem);

        locationRecyViewItem = (LocationRecyViewItem) getIntent().getExtras().get("LOCATION_DATA");

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        submitItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = addItemName.getText().toString().trim();
                String itemType = addItemType.getText().toString().trim();
                int imageID = R.drawable.title_logo;

                if (itemName.length() > 0) {
                    ItemRecyViewItem newItem = new ItemRecyViewItem(imageID, locationRecyViewItem.getLocationName(), itemName, itemType);

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("newItem", newItem);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }
            }
        });
    }
}
