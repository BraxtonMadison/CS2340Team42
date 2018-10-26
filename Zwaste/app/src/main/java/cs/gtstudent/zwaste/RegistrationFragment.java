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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationFragment extends Fragment {

    private View view;
    private EditText name, emailID, password;
    private RadioGroup userTypeRadio;
    private RadioButton userFinalTypeRadio;

    private Button cancelButton, submitButton;
    private UserType userType;

    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_registration, container, false);

        name = (EditText)view.findViewById(R.id.nameEdit);
        emailID = (EditText)view.findViewById(R.id.idEdit);
        password = (EditText)view.findViewById(R.id.pwEdit);
        userTypeRadio = (RadioGroup)view.findViewById(R.id.userType);
        userFinalTypeRadio = (RadioButton)view.findViewById(R.id.user_regUser);

        cancelButton = (Button)view.findViewById(R.id.cancelButton);
        submitButton = (Button)view.findViewById(R.id.submitButton);

        auth = FirebaseAuth.getInstance();

        userTypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int index) {
                userFinalTypeRadio = (RadioButton)view.findViewById(index);
                switch (index) {
                    case R.id.user_regUser:
                        userType = UserType.REG_USER;
                    case R.id.user_locEmp:
                        userType = UserType.LOC_EMPL;
                    case R.id.user_admin:
                        userType = UserType.ADMIN;
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
                User newUser = checkRegisterInfo();
                registerNewUser(newUser);
                ((LogInActivity)getActivity()).setFragment(0);
            }
        });

        return view;
    }

    private void removeAllText() {
        name.setText("");
        emailID.setText("");
        password.setText("");
        userFinalTypeRadio = (RadioButton)view.findViewById(R.id.user_regUser);
    }
    private User checkRegisterInfo() {
        String name = this.name.getText().toString().trim();
        String id = this.emailID.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        //Criteria for appropriate user information would go here but I was too lazy to write them

        return new User(name, id, password, userType);
    }
    private void registerNewUser(final User newUser) {
        final String emailID = newUser.getEmailID();
        final String id = newUser.getId();
        final String pw = newUser.getPassword();
        auth.createUserWithEmailAndPassword(emailID, pw).addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Toast.makeText(view.getContext(), "Registration Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference()
                            .child("users")
                            .child(auth.getUid())
                            .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(view.getContext(), "Registration Complete", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
