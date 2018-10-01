package cs.gtstudent.zwaste;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class RegistrationFragment extends Fragment {

    private View view;
    private EditText name, id, password;
    private RadioGroup userTypeRadio;
    private RadioButton userFinalTypeRadio;

    private Button cancelButton, submitButton;
    private UserType userType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_registration, container, false);

        name = (EditText)view.findViewById(R.id.nameEdit);
        id = (EditText)view.findViewById(R.id.idEdit);
        password = (EditText)view.findViewById(R.id.pwEdit);
        userTypeRadio = (RadioGroup)view.findViewById(R.id.userType);
        userFinalTypeRadio = (RadioButton)view.findViewById(R.id.user_regUser);

        cancelButton = (Button)view.findViewById(R.id.cancelButton);
        submitButton = (Button)view.findViewById(R.id.submitButton);

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
                String logInInfo = newUser.getUserLogIn().toString() + ";";
                registerNewUser(newUser, logInInfo);
                ((LogInActivity)getActivity()).setFragment(0);
            }
        });

        return view;
    }

    private void removeAllText() {
        name.setText("");
        id.setText("");
        password.setText("");
        userFinalTypeRadio = (RadioButton)view.findViewById(R.id.user_regUser);
    }
    private User checkRegisterInfo() {
        String name = this.name.getText().toString().trim();
        String id = this.id.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        //Criteria for appropriate user information would go here but I was too lazy to write them

        return new User(name, id, password, userType);
    }
    private void registerNewUser(User newUser, String logInInfo) {

        //userInfoName : For all User data
        //userLogInInfoName :  Only for id and password
        //String userInfoName = "userInfo.dat";
        String userLogInInfoName = "userLogInInfo.dat";
        File directory = this.getContext().getFilesDir();
        File userLogInInfoFile = new File(directory, userLogInInfoName);

        if (!userLogInInfoFile.exists()) {
            try {
                userLogInInfoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter pw = new PrintWriter(userLogInInfoFile);

            System.out.println(logInInfo);

            pw.append(logInInfo);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
