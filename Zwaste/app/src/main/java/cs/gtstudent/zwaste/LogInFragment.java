package cs.gtstudent.zwaste;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Controller Class responsible for log-in functionality.
 * Receives information from the user, checks the information for correctness and safety,
 * and checks the validity of the user information with the Firebase authentication system.
 */
public class LogInFragment extends Fragment {

    private Button logInButton;
    private Button registerUserButton;
    private EditText emailInput;
    private EditText pwInput;  //ID and PW input

    private FirebaseAuth auth;

    //For Debugging purpose
    private boolean isLogInSuccessful;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        logInButton = view.findViewById(R.id.logInButton);
        registerUserButton = view.findViewById(R.id.registerUserButton);

        emailInput = view.findViewById(R.id.emailInput);
        pwInput = view.findViewById(R.id.pwInput);

        auth = FirebaseAuth.getInstance();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogInInfo(view);
            }
        });
        registerUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LogInActivity)getActivity()).setFragment(1);
            }
        });

        return view;
    }

    private void checkLogInInfo(final View view) {
        isLogInSuccessful = false;

        String email;
        String password;

        email = emailInput.getText().toString().trim();
        password = pwInput.getText().toString().trim();

        String emailStr = DataValidation.validateEmail(email);
        String pwStr = DataValidation.validatePassword(password);

        if (!emailStr.equals("Valid email.")) {
            Toast.makeText(this.getActivity(), emailStr, Toast.LENGTH_SHORT).show();
        } else if (!pwStr.equals("Valid password.")) {
            Toast.makeText(this.getActivity(), pwStr, Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this.getActivity(),
                            new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(view.getContext(), "Check your log in information.",
                                                                    Toast.LENGTH_SHORT).show();
                        Log.i("Log In Failure", "Reason: Firebase Failure");
                    } else {
                        Toast.makeText(view.getContext(), "Log in successful",
                                                                    Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), MainMenuActivity.class);
                        isLogInSuccessful = true;
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
