package cs.gtstudent.zwaste;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowOptionsActivity extends AppCompatActivity {

    private TextView showItems;
    private TextView addItems;
    LocationRecyViewItem locationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_options);

        final LocationRecyViewItem locationData = (LocationRecyViewItem) getIntent().getExtras().get("LOCATION_DATA");
        this.locationData = locationData;

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
                locationData.getLocationData().addItem(newItem);
            }
        }
    }
}
