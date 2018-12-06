package cs.gtstudent.zwaste;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible of displaying geographical location of each
 * donation location on the map.
 */
public class ShowMapLocationActivity extends AppCompatActivity
    implements OnMapReadyCallback {

    private DatabaseReference db;
    private List<LocationData> locationDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map_location);

        locationDataList = new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference()
                    .child("locations");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LocationData locData = ds.getValue(LocationData.class);
                    locationDataList.add(locData);
                }

                FragmentManager fragmentManager = getFragmentManager();
                MapFragment mapFragment = (MapFragment)fragmentManager
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(ShowMapLocationActivity.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onMapReady(final GoogleMap map) {
        for (LocationData locData : locationDataList) {
            double latitude = Double.parseDouble(locData.getLatitude().trim());
            double longitude = Double.parseDouble(locData.getLongitude().trim());
            String name = locData.getLocationName();
            String phoneNum = locData.getPhoneNum();
            LatLng tempLoc = new LatLng (latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(tempLoc);
            markerOptions.title(name);
            markerOptions.snippet(phoneNum);
            map.addMarker(markerOptions);
        }
    }
}
