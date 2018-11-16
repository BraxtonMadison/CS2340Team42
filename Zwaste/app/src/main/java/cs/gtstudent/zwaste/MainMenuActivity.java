package cs.gtstudent.zwaste;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Controller class responsible for displaying main options to the user.
 * Many of the displayed functions are not yet to be implemented.
 */
public class MainMenuActivity extends AppCompatActivity {

    //View Component
    private ImageView logOutIcon;
    private ConstraintLayout showLocationLayout;
    private ConstraintLayout donationTrackerLayout;
    private ConstraintLayout profileLayout;
    private ConstraintLayout settingsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        /*
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
        */


        showLocationLayout = findViewById(R.id.showLocationLayout);
        donationTrackerLayout = findViewById(R.id.donationTrackerLayout);
        profileLayout = findViewById(R.id.profileLayout);
        settingsLayout = findViewById(R.id.settingsLayout);

        logOutIcon = findViewById(R.id.logOutIcon);


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
                Toast.makeText(getApplicationContext(),
                        "User Profile is not yet implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });
        settingsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Settings is not yet implemented", Toast.LENGTH_SHORT).show();
            }
        });

        logOutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "You signed out.", Toast.LENGTH_SHORT).show();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                finish();
            }
        });
    }

}
