package com.homework.buttonmenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class homeFrag extends Fragment {
private FloatingActionButton btnLogout;
    public homeFrag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnLogout=view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log_Out();
            }
        });
        return view;
    }

    private void Log_Out() {
        FirebaseAuth.getInstance().signOut();
        MainActivity.isLogedin=false;
        MainActivity.homeFrame.setVisibility(View.INVISIBLE);
        MainActivity.dashFrame.setVisibility(View.INVISIBLE);
        MainActivity.loginFrame.setVisibility(View.VISIBLE);
    }
}