package cs.gtstudent.zwaste;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Controller Class responsible for password recover functionality.
 * Receives email from the user, and contacts the
 */
public class PasswordRecoveryFragment extends Fragment {
    private EditText emailForPasswordInput;
    private Button passwordRecoveryButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_recovery, container, false);


        emailForPasswordInput = view.findViewById(R.id.emailForPasswordInput);
        passwordRecoveryButton = view.findViewById(R.id.passwordRecoveryButton);

        passwordRecoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = emailForPasswordInput.getText().toString().trim();
                if (!DataValidation.isEmailValid(email)) {
                    Toast.makeText(view.getContext(), "Please provide a proper email.", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(view.getContext(), "Email Sent!", Toast.LENGTH_SHORT).show();
                                }
                            });
                    ((LogInActivity)getActivity()).setFragment(3);
                }
            }
        });

        return view;
    }
}
