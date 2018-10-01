package cs.gtstudent.zwaste;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    //Internal View Components
    private FrameLayout logInRegistrationFrame;
    private LogInFragment logInFragment;
    private RegistrationFragment registrationFragment;

    //Animation Components
    private Animation splash_logo;      //For Title Logo Appearance
    private Animation appear_log_in;    // For Log In Component Appearance

    //Image, Editable Textbox, etc.
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
        setFragment(1);

        titleLogo = (ImageView)findViewById(R.id.titleLogo);

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

    public void setFragment(int num) {
        FragmentTransaction fragmentExchange = getFragmentManager()
                .beginTransaction();
        switch (num) {
            case 0:
                fragmentExchange.replace(R.id.logInRegistrationFragment, logInFragment);
                fragmentExchange.setCustomAnimations(R.animator.slide_left_in, R.animator.slide_right_out);
                fragmentExchange.commit();
                break;
            case 1:
                fragmentExchange.replace(R.id.logInRegistrationFragment, registrationFragment);
                fragmentExchange.setCustomAnimations(R.animator.slide_right_in, R.animator.slide_left_out);
                fragmentExchange.commit();
                break;
        }
    }

}
