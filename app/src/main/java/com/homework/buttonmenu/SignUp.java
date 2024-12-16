package com.homework.buttonmenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;

public class SignUp extends Fragment {
    TextInputEditText name, userName, phone, email, password, confirm;
    Button signUp;
    char[] character, chName, chPhone;
    FirebaseAuth bAuth;
    FirebaseFirestore nm;


    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        checkPass();
        userName = view.findViewById(R.id.et_uName);
        name = view.findViewById(R.id.et_Name);
        phone = view.findViewById(R.id.et_phone);
        email = view.findViewById(R.id.et_gmail);
        password = view.findViewById(R.id.et_Pass);
        signUp = view.findViewById(R.id.btn_signup);
        confirm = view.findViewById(R.id.et_confirPass);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        return view;
    }

    private boolean checkPass() {
        char[] array = password.getText().toString().toCharArray();
        boolean capital = false;
        boolean small = false;
        boolean number = false;
        if (password.getText().toString().length() < 6) {
            Toast.makeText(getActivity(), "password must be at least 6 digits", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (Character.isUpperCase(array[i]))
                    capital = true;
                if (Character.isLowerCase(array[i]))
                    small = true;
                if (Character.isDigit(array[i]))
                    number = true;
            }
            if (capital && small && number)
                return true;
        }
        if (!capital) {
            Toast.makeText(getActivity(), "password must have atleast one  capital letter", Toast.LENGTH_SHORT).show();
        } else {
            if (!small) {
                Toast.makeText(getActivity(), "password must have atleast one small letter", Toast.LENGTH_SHORT).show();
            } else {
                if (!number) {
                    Toast.makeText(getActivity(), "password must have a number", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }

    private void addUserToFireStore() {
        com.homework.buttonmenu.User user = new User(name.getText().toString(), userName.getText().toString(), phone.getText().toString(), email.getText().toString());
        nm.collection("users").document(user.getEmail()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "user has beenadded", Toast.LENGTH_SHORT).show();
                    updateUI();
                    name.setText(null);
                    phone.setText(null);
                    email.setText(null);
                    password.setText(null);
                    confirm.setText(null);
                } else {
                    Toast.makeText(getActivity(), "adding user failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean check_phone() {
        char[] array = phone.getText().toString().toCharArray();
        boolean x = true;
        if (array.length != 10) {
            return false;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (!Character.isDigit(array[i]))
                    x = false;
            }
        }
        return x;
    }

    private boolean check_email() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            return true;
        }
        return false;
    }

    private void signUp() {
        if (name.getText().toString().length() >= 4) {
            if (check_phone()) {
                if (check_email()) {
                    if (checkPass()) {
                        if ((password.getText().toString()).equals(confirm.getText().toString())) {
                            bAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "sign up succeed", Toast.LENGTH_LONG).show();
                                        addUserToFireStore();
                                        updateUI();
                                    } else {
                                        Toast.makeText(getActivity(), "sign up failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "password is not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "invalid email", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "phone number must be 10 digits", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "name must be at least 4 digits", Toast.LENGTH_SHORT).show();
        }
    }
    private void check(){
        if(userName.getText().toString().isEmpty()){
            Toast.makeText(getActivity(),"please fill the user name",Toast.LENGTH_SHORT).show();
        }
        else{
            if(name.getText().toString().isEmpty()){
                Toast.makeText(getActivity(),"please fill the name",Toast.LENGTH_SHORT).show();
            }
            else{
                if(phone.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"please fill the phone number",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(email.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(),"please fill the email",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(password.getText().toString().isEmpty()){
                            Toast.makeText(getActivity(),"please fill the password",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(confirm.getText().toString().isEmpty()){
                                Toast.makeText(getActivity(),"please confirm the password",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                signUp();
                            }
                        }
                    }
                }
            }
        }
    }
    private void updateUI(){
        MainActivity.loginFrame.setVisibility(View.VISIBLE);
        MainActivity.homeFrame.setVisibility(View.INVISIBLE);
        MainActivity.dashFrame.setVisibility(View.INVISIBLE);
        MainActivity.signUpFrame.setVisibility(View.INVISIBLE);
    }
}
