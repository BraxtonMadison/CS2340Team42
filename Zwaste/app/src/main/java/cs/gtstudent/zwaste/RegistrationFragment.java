package cs.gtstudent.zwaste;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Controller Class responsible for Registration functionality.
 * Receives information from the user, checks the information for correctness and safety,
 * and registers the user onto the Firebase.
 */

public class RegistrationFragment extends Fragment {

    private View view;
    private EditText name;
    private EditText emailID;
    private EditText password;
    private RadioGroup userTypeRadio;
    private RadioButton userFinalTypeRadio;

    private Button cancelButton;
    private Button submitButton;
    private User.UserType userType;

    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_registration, container, false);

        name = view.findViewById(R.id.nameEdit);
        emailID = view.findViewById(R.id.idEdit);
        password = view.findViewById(R.id.pwEdit);
        userTypeRadio = view.findViewById(R.id.userType);
        userFinalTypeRadio = view.findViewById(R.id.user_regUser);
        userType = User.UserType.REG_USER;

        cancelButton = view.findViewById(R.id.cancelButton);
        submitButton = view.findViewById(R.id.submitButton);

        auth = FirebaseAuth.getInstance();

        userTypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int index) {
                userFinalTypeRadio = view.findViewById(index);
                switch (index) {
                    case R.id.user_regUser:
                        userType = User.UserType.REG_USER;
                        break;
                    case R.id.user_locEmp:
                        userType = User.UserType.LOC_EMPL;
                        break;
                    case R.id.user_manager:
                        userType = User.UserType.MANAGER;
                        break;
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAllText();
                ((LogInActivity)getActivity()).setFragment(0);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = checkRegistrationInfo();
                if (newUser != null) {
                    registerNewUser(newUser);
                }
                removeAllText();
                ((LogInActivity)getActivity()).setFragment(0);
            }
        });

        return view;
    }

    private void removeAllText() {
        name.setText("");
        emailID.setText("");
        password.setText("");
        userFinalTypeRadio = view.findViewById(R.id.user_regUser);
    }
    private User checkRegistrationInfo() {
        String name = this.name.getText().toString().trim();
        String emailId = this.emailID.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        //Criteria for appropriate user information would go here but I was too lazy to write them  --Louis
        //But wait!!! Alex and Louis have now written methods to examine these criteria
        //Please help I'm sad :(
        // --Alex

        String errorMsg = DataValidation.validateRegistrationInfo(emailId, password);
        if (!errorMsg.equals("Valid info.")) {
            Toast.makeText(this.getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
            return null;
        } else {
            return new User(name, emailId, password, userType);
        }
    }
    private void registerNewUser(final User newUser) {
        final String emailID = newUser.getEmailID();
        final String pw = newUser.getPassword();
        auth.createUserWithEmailAndPassword(emailID, pw).addOnCompleteListener(this.getActivity(),
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Toast.makeText(view.getContext(),
                            "Registration Failed: " + e.getMessage(),
                                                        Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference()
                            .child("users")
                            .child(auth.getUid())
                            .setValue(newUser)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(view.getContext(),
                                    "Registration Complete",
                                                        Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
