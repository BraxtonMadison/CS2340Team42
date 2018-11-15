package cs.gtstudent.zwaste;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class which lists every available locations.
 */
public class MainScreenActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private List<LocationData> csvDataList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyLayoutManager;
    private LocationRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_screen);
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyView);
        recyclerView.setHasFixedSize(true);
        recyLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyLayoutManager);

        csvDataList = new ArrayList<>();

        adapter = new LocationRecycleViewAdapter(populateWithLocations());
        recyclerView.setAdapter(adapter);
    }

    /**
     * Signs the user out when the Log out button is clicked.
     * This method is triggered by R.id.LogOutButton.
     * @param view Instance of the view that is currently active.
     */
    public void backToLogIn(View view) {
        Toast.makeText(this, "You logged out.", Toast.LENGTH_SHORT).show();
        auth.signOut();
        this.finish();
    }
    private List<LocationRecycleViewItem> populateWithLocations() {
        Resources r = getResources();
        InputStream is = r.openRawResource(R.raw.location_data);
        CSVReader csvReader = new CSVReader(is);
        List<LocationRecycleViewItem> items = new ArrayList<>();
        csvDataList = csvReader.read();

        for (int x = 1; x < csvDataList.size(); x++) {
            LocationData d = csvDataList.get(x);
            items.add(new LocationRecycleViewItem(R.drawable.title_logo, d));
        }
        return items;
    }

}
