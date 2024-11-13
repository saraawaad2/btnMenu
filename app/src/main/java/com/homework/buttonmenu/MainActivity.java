package com.homework.buttonmenu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    private homeFrag homeFrag;
    private DashFrag dashFrag;
    private LoginFrag loginFrag;
    public static FrameLayout homeFrame,dashFrame,loginFrame;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFrame=findViewById(R.id.home_frame);
        dashFrame=findViewById(R.id.dash_frame);
        loginFrame=findViewById(R.id.login_frame);
        bottomNavigationView=findViewById(R.id.buttom_navigation);
        begin();
    }

    private void begin() {
        homeFrag=new homeFrag();
        dashFrag=new DashFrag();
        loginFrag=new LoginFrag();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,homeFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.dash_frame,dashFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame,loginFrag).commit();
        //hiding dash and login Fragments
        dashFrame.setVisibility(View.INVISIBLE);
        loginFrame.setVisibility(View.INVISIBLE);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_home){
                    homeFrame.setVisibility(View.VISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);
                }
                if(item.getItemId()==R.id.menu_dashboard){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.VISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);
                }
                if(item.getItemId()==R.id.menu_login){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }
}
