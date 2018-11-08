package cs.gtstudent.zwaste;

import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    //View Component
    private ImageView logOutIcon;
    private ConstraintLayout showLocationLayout, donationTrackerLayout,
            profileLayout, settingsLayout;
    private ImageView showLocationIcon, donationTrackerIcon,
            profileIcon, settingsIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        /**
        List<LocationData> locationDataList = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.location_data);
        CSVReader csvReader = new CSVReader(is);
        locationDataList = csvReader.read();

        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();
        for (LocationData locData : locationDataList) {
            dbr.child("locations")
                    .child(SHA256.getSHA256(locData.getLocationName()))
                    .setValue(locData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Adding complete", Toast.LENGTH_SHORT);
                }
            });
        }
        **/


        showLocationLayout = findViewById(R.id.showLocationLayout);
        donationTrackerLayout = findViewById(R.id.donationTrackerLayout);
        profileLayout = findViewById(R.id.profileLayout);
        settingsLayout = findViewById(R.id.settingsLayout);

        showLocationIcon = findViewById(R.id.showLocationIcon);
        donationTrackerIcon = findViewById(R.id.donationTrackerIcon);
        profileIcon = findViewById(R.id.profileIcon);
        settingsIcon = findViewById(R.id.settingsIcon);

        showLocationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowMapLocationActivity.class);
                startActivity(intent);
            }
        });
        donationTrackerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainScreenActivity.class);
                startActivity(intent);
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "User Profile is not yet implemented", Toast.LENGTH_SHORT);
            }
        });
        settingsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Settings is not yet implemented", Toast.LENGTH_SHORT);
            }
        });

    }

}
