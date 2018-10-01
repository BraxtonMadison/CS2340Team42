package cs.gtstudent.zwaste;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

public class LogInFragment extends Fragment {
    private View view;

    private Button logInButton, registerUserButton;
    private EditText emailInput, pwInput;  //ID and PW input

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_log_in, container, false);

        logInButton = (Button)view.findViewById(R.id.logInButton);
        registerUserButton = (Button)view.findViewById(R.id.registerUserButton);

        emailInput = (EditText)view.findViewById(R.id.emailInput);
        pwInput = (EditText)view.findViewById(R.id.pwInput);

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
    private void checkLogInInfo(View view) {
        String email, password;
        String logInInfo;

        email = emailInput.getText().toString().trim();
        password = pwInput.getText().toString().trim();
        logInInfo = new UserLogIn(email, password).toString();

        String userLogInData = getUserData();
        if (userLogInData == null) {
            Toast.makeText(this.getActivity(), "Check your log in information.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (false/*!emailContainsAt(email)*/) {
            Toast.makeText(this.getActivity(), "Please check your email.", Toast.LENGTH_SHORT).show();
        } else {
            if (userLogInData.contains(logInInfo)) {
                Toast.makeText(this.getActivity(), "Log in successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.getActivity(), MainScreenActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this.getActivity(), "Check your log in information.", Toast.LENGTH_SHORT).show();
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

    private String getUserData() {
        String userLogInInfoName = "userLogInInfo.dat";
        File directory = this.getContext().getFilesDir();
        File storedUsers = new File(directory, userLogInInfoName);
        String userData = "";
        if (!storedUsers.exists()) {
            return null;
        }
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(storedUsers));
            userData = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(userData);
        Log.i(userData, "Shit");
        return userData;
    }

}
