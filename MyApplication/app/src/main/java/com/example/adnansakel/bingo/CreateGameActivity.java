package com.example.adnansakel.bingo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class CreateGameActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //new MainGameView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());


    }


}
