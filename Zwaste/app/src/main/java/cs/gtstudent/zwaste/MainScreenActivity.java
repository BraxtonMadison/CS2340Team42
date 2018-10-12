package cs.gtstudent.zwaste;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private LinearLayout mainScreen;
    private ListView list;
    private List<LocationData> csvDataList;
    private LocationListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        mainScreen = findViewById(R.id.mainScreen);
        auth = FirebaseAuth.getInstance();
        list = findViewById(R.id.listView);
        csvDataList = new ArrayList<>();

        adapter = new LocationListAdapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LocationListViewItem temp = (LocationListViewItem)adapter.getItem(i);
                LocationData locData = temp.getLocationData();

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.location_pop_up,null);
                TextView locText = customView.findViewById(R.id.fullLocData);
                locText.setText(locData.toString());
                PopupWindow mPopupWindow = new PopupWindow(
                        customView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getApplicationContext().getResources(),
                        Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)));
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.showAtLocation(mainScreen, Gravity.CENTER,0,0);
            }
        });
        populateListView();
    }
    public void backToLogIn(View view) {
        Toast.makeText(this, "You logged out.", Toast.LENGTH_SHORT).show();
        auth.signOut();
        this.finish();
    }
    private void populateListView() {
        InputStream is = getResources().openRawResource(R.raw.location_data);
        CSVReader csvReader = new CSVReader(is);
        csvDataList = csvReader.read();

        for (int x = 1; x < csvDataList.size(); x++) {
            LocationData d = csvDataList.get(x);
            adapter.addItem(ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.title_logo), d.getLocationName(), d.getLocationType(), d);
        }
    }

}
