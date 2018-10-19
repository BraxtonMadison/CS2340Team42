package cs.gtstudent.zwaste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyLayoutManager;
    private List<LocationData> csvDataList;
    private LocationRecyViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyView);
        recyclerView.setHasFixedSize(true);
        recyLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyLayoutManager);

        csvDataList = new ArrayList<>();

        adapter = new LocationRecyViewAdapter(populateWithLocations());
        recyclerView.setAdapter(adapter);
    }
    public void backToLogIn(View view) {
        Toast.makeText(this, "You logged out.", Toast.LENGTH_SHORT).show();
        auth.signOut();
        this.finish();
    }
    private List<LocationRecyViewItem> populateWithLocations() {
        InputStream is = getResources().openRawResource(R.raw.location_data);
        CSVReader csvReader = new CSVReader(is);
        ArrayList<LocationRecyViewItem> items = new ArrayList<>();
        csvDataList = csvReader.read();

        for (int x = 1; x < csvDataList.size(); x++) {
            LocationData d = csvDataList.get(x);
            items.add(new LocationRecyViewItem(R.drawable.title_logo, d));
        }
        return items;
    }

}
