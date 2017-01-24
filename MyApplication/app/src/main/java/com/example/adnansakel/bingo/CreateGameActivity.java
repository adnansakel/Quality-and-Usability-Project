package com.example.adnansakel.bingo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class CreateGameActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        getSupportActionBar().setTitle("Create Game");
        Toast.makeText(this,"Came to on create",Toast.LENGTH_LONG).show();
        //new MainGameView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());


    }

    /*
    @Override
    public void onBackPressed(){
        Toast.makeText(this,"This activity will exit",Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }*/


}
