package com.example.adnansakel.bingo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.adnansakel.bingo.Util.AppConstants;
import com.example.adnansakel.bingo.View.HomeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new HomeView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());

    }



}
