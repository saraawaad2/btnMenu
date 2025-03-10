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
    private fragre dashFrag;
    private LoginFrag loginFrag;
    private SignUp signUpFrag;
    public static FrameLayout homeFrame,dashFrame,loginFrame,signUpFrame;
    private BottomNavigationView bottomNavigationView;
    public static boolean isLogedin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFrame=findViewById(R.id.home_frame);
        dashFrame=findViewById(R.id.dash_frame);
        loginFrame=findViewById(R.id.login_frame);
        signUpFrame=findViewById(R.id.Signup_frame);
        bottomNavigationView=findViewById(R.id.buttom_navigation);
        begin();
    }

    private void begin() {
        homeFrag=new homeFrag();
        dashFrag=new fragre();
        loginFrag=new LoginFrag();
        signUpFrag=new SignUp();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,homeFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.dash_frame,dashFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame,loginFrag).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.Signup_frame,signUpFrag).commit();
        dashFrame.setVisibility(View.INVISIBLE);
        homeFrame.setVisibility(View.INVISIBLE);
        signUpFrame.setVisibility(View.INVISIBLE);
        loginFrame.setVisibility(View.VISIBLE);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_home&&isLogedin){
                    homeFrame.setVisibility(View.VISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);
                    signUpFrame.setVisibility(View.INVISIBLE);
                }
                if(item.getItemId()==R.id.menu_dashboard&&isLogedin){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.VISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);
                    signUpFrame.setVisibility(View.INVISIBLE);
                }
                if(item.getItemId()==R.id.menu_login&&!isLogedin){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.VISIBLE);
                    signUpFrame.setVisibility(View.INVISIBLE);
                }
                if(item.getItemId()==R.id.menu_Signup&&!isLogedin){
                    homeFrame.setVisibility(View.INVISIBLE);
                    dashFrame.setVisibility(View.INVISIBLE);
                    loginFrame.setVisibility(View.INVISIBLE);
                    signUpFrame.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }
}
