package cs.gtstudent.zwaste;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                String logInInfo = newUser.getId() + ".." + newUser.getPassword();
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

        //userInfoName = For all User data
        //userLogInInfoName = Only for id and password
        String userInfoName = "userInfo.dat";
        String userLogInInfoName = "userLogInInfo.dat";
        File storedUsers = new File(this.view.getContext().getFilesDir(), userInfoName);
        File storedLogInUsers = new File(this.view.getContext().getFilesDir(), userLogInInfoName);

        FileInputStream inputStream;
        FileOutputStream outputStream;

        if (!storedUsers.exists()) {
            Set<User> userData = new HashSet<>();
            Set<String> userLogInData = new HashSet<>();

            try {
                storedUsers.createNewFile();
                storedLogInUsers.createNewFile();

                //For All User data, including Name and User Type
                outputStream = this.view.getContext().openFileOutput(userInfoName, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                oos.writeObject(userData);
                oos.close();

                //For Log In info only, id and password
                outputStream = this.view.getContext().openFileOutput(userLogInInfoName, Context.MODE_PRIVATE);
                oos = new ObjectOutputStream(outputStream);
                oos.writeObject(userLogInData);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            inputStream = this.view.getContext().openFileInput(userInfoName);
            outputStream = this.view.getContext().openFileOutput(userInfoName, Context.MODE_PRIVATE);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            Set<User> userData = (HashSet) ois.readObject();
            userData.add(newUser);

            oos.writeObject(userData);
            oos.close();

            //------------------------

            inputStream = this.view.getContext().openFileInput(userLogInInfoName);
            outputStream = this.view.getContext().openFileOutput(userLogInInfoName, Context.MODE_PRIVATE);
            ois = new ObjectInputStream(inputStream);
            oos = new ObjectOutputStream(outputStream);

            Set<String> userLogInData = (HashSet) ois.readObject();
            userLogInData.add(logInInfo);

            oos.writeObject(userLogInData);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Toast.makeText(this.getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
    }
}

//inputStream = this.view.getContext().openFileInput(userListName);
//                ObjectInputStream ois = new ObjectInputStream(inputStream);
//                userData = (HashSet)ois.readObject();