package cs.gtstudent.zwaste;

import android.app.FragmentTransaction;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Controller class responsible for initial user log-in / registration.
 * This activity holds two fragments, each responsible for Log-in and
 * registration.
 */
public class LogInActivity extends AppCompatActivity {

    //Internal View Components
    private FrameLayout logInRegistrationFrame;
    private LogInFragment logInFragment;
    private RegistrationFragment registrationFragment;

    //Animation Components
    private Animation splash_logo;      //For Title Logo Appearance
    private Animation appear_log_in;    // For Log In Component Appearance

    //Image, Editable Text box, etc.
    private ImageView titleLogo;            //Title Logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Fragment for log-in and user registration.
        logInRegistrationFrame = findViewById(R.id.logInRegistrationFragment);
        logInFragment = new LogInFragment();
        registrationFragment = new RegistrationFragment();

        //Set Log in fragment as initial fragment
        setFragment(0);

        //Get screen size
        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);

        titleLogo = findViewById(R.id.titleLogo);
        titleLogo.setMaxWidth((int)(size.x / 2.5));
        titleLogo.setMaxHeight((int)(size.y / 2.5));

        //Initialize Animation
        splash_logo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_logo);
        appear_log_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.appear_log_in);
        splash_logo.setFillAfter(true);

        //Start animation for logo
        titleLogo.startAnimation(splash_logo);

        splash_logo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /* Let ID/PW Input screen and register user button appear only after when
                 * Animation for Title logo is over.
                 */
                logInRegistrationFrame.startAnimation(appear_log_in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    /**
     * Method for switching from LogInFragment to RegistrationFragment and vise-versa.
     * This is called from either fragment to call for switch to the other fragment.
     * @param num Hard-coded id number assigned to each fragment. Fragment will be replaced
     *            based on this value.
     */
    public void setFragment(int num) {
        FragmentTransaction fragmentExchange = getFragmentManager()
                .beginTransaction();
        switch (num) {
            case 0:
                fragmentExchange.setCustomAnimations(R.animator.slide_left_in,
                                                            R.animator.slide_right_out);
                fragmentExchange.replace(R.id.logInRegistrationFragment, logInFragment);
                fragmentExchange.commit();
                break;
            case 1:
                fragmentExchange.setCustomAnimations(R.animator.slide_right_in,
                                                            R.animator.slide_left_out);
                fragmentExchange.replace(R.id.logInRegistrationFragment, registrationFragment);
                fragmentExchange.commit();
                break;
        }
    }

}
