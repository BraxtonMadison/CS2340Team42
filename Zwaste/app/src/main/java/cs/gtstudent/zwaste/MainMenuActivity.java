package cs.gtstudent.zwaste;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenuActivity extends AppCompatActivity {

    //View Component
    private RelativeLayout[] options;

    //User Firebase auth
    private FirebaseAuth auth;
    private User.UserType userType;

    //Constant
    private int screenHeight;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        options = new RelativeLayout[4];
        populateOptions();
        positionOptions();

        final Animation moveUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_option_touch);
        final Animation moveDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_option_release);

        for (final RelativeLayout rl : options) {
            rl.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            rl.startAnimation(moveUp);
                            break;
                        case MotionEvent.ACTION_UP:
                            rl.startAnimation(moveDown);
                            break;
                        case MotionEvent.ACTION_HOVER_ENTER:
                            rl.startAnimation(moveUp);
                            break;
                        case MotionEvent.ACTION_HOVER_EXIT:
                            rl.startAnimation(moveDown);
                            break;
                    }
                    return true;
                }
            });
        }
    }

    private void populateOptions() {
        options[0] = findViewById(R.id.menu1);
        options[1] = findViewById(R.id.menu2);
        options[2] = findViewById(R.id.menu3);
        options[3] = findViewById(R.id.menu4);
    }
    private void positionOptions() {
        //Get screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        height = screenHeight / 6;

        for (int x = 1; x < 5; x++) {
            RelativeLayout.LayoutParams menuOption = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2 * height);
            menuOption.topMargin = (int)(screenHeight - height);
            screenHeight -= height;
            options[x-1].setLayoutParams(menuOption);
            options[x-1].setElevation(30*(4-x));
        }
        screenHeight = size.y;
    }

    private void populateRegularUserLayout() {

    }
}
