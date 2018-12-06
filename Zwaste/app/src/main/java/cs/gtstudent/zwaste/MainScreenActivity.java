package cs.gtstudent.zwaste;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class which lists every available locations.
 */
public class MainScreenActivity extends AppCompatActivity {

    private DatabaseReference db;
    private List<LocationData> locationDataList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyLayoutManager;
    private LocationRecycleViewAdapter adapter;

    private Button showMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_screen);

        showMapButton = findViewById(R.id.showMapButton);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyLayoutManager);

        locationDataList = new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference()
                .child("locations");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LocationData locData = ds.getValue(LocationData.class);
                    locationDataList.add(locData);
                    Log.i("Lit", "ADDED LOCATION!");
                    Log.i("Info:", locData.toString());
                }

                List<LocationRecycleViewItem> locationRecycleViewItems = new ArrayList<>();
                for (LocationData data : locationDataList) {
                    locationRecycleViewItems.add(new LocationRecycleViewItem(R.drawable.title_logo, data));
                    Log.i("Info:", data.toString());
                }
                adapter = new LocationRecycleViewAdapter(locationRecycleViewItems);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        showMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowMapLocationActivity.class);
                startActivity(intent);
            }
        });
    }

}
