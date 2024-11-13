package com.homework.buttonmenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFrag extends Fragment {
TextInputEditText txtEmail,txtPassword;
Button btnLogin;
FirebaseAuth mAuth;
    public LoginFrag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        txtEmail=view.findViewById(R.id.et_email);
        txtPassword=view.findViewById(R.id.et_Pass);
        btnLogin=view.findViewById(R.id.btn_login);
        mAuth=FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailPass();
               
            }
        });
        return view;
    }

    private void checkEmailPass() {
        String email,password;
        email=txtEmail.getText().toString();
        password=txtPassword.getText().toString();
        if(!(email.equals("")||password.equals(""))){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Toast.makeText(getActivity(),"login successful",Toast.LENGTH_SHORT).show();
                updateUI();
            }
            else
                Toast.makeText(getActivity(),"login failed",Toast.LENGTH_SHORT).show();
            }
        });
        }
        else
            Toast.makeText(getActivity(),"please fill in fields",Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
        MainActivity.homeFrame.setVisibility(View.VISIBLE);
        MainActivity.dashFrame.setVisibility(View.INVISIBLE);
        MainActivity.loginFrame.setVisibility(View.INVISIBLE);
    }
}