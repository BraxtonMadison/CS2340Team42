/*package cs.gtstudent.zwaste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private FrameLayout logInFragment;
    private LinearLayout logInInfoEdit;

    //Animation Components
    private Animation splash_logo;      //For Title Logo Appearance
    private Animation appear_log_in;    // For Log In Component Appearance

    //Image, Editable Textbox, etc.
    private ImageView titleLogo;            //Title Logo
    private EditText emailInput, pwInput;  //ID and PW input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInFragment = (FrameLayout)findViewById(R.id.logInFragment);
        logInInfoEdit = (LinearLayout)findViewById(R.id.loginInfoEdit);

        titleLogo = (ImageView)findViewById(R.id.titleLogo);
        emailInput = (EditText)logInFragment.findViewById(R.id.emailInput);
        pwInput = (EditText)logInFragment.findViewById(R.id.pwInput);

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
                 *//*
                logInInfoEdit.startAnimation(appear_log_in);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void checkLogInInfo(View view) {
        String email, password;
        //Dummy info for testing
        String authEmail, authPassword;

        email = emailInput.getText().toString().trim();
        password = pwInput.getText().toString().trim();

        authEmail = getResources().getString(R.string.dummy_user_email);
        authPassword = getResources().getString(R.string.dummy_user_password);

        if (!emailContainsAt(email)) {
            Toast.makeText(this, "Please check your email.", Toast.LENGTH_SHORT).show();
        } else {
            if (email.equals(authEmail) && password.equals(authPassword)) {
                Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainScreenActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Check your log in information.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean emailContainsAt(String email) {
        char[] emailChar = email.toCharArray();
        for (char c : emailChar)  {
            if (c == '@') return true;
        }
        return false;
    }

    public void registerUser(View view) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.logInFragment, new )
    }

}*/
