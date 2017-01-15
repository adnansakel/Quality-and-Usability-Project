package com.example.adnansakel.bingo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.adnansakel.bingo.Util.AppConstants;
import com.example.adnansakel.bingo.View.HomeView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreateGame;
    Button btnJoinGame;
    BingoServerCalls bingoServerCalls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new HomeView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());
        //System.out.println("Player at home: "+((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getPlayerID());
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        initialize();
    }

    private void initialize(){
        btnCreateGame = (Button)findViewById(R.id.btn_create_game);
        btnJoinGame = (Button)findViewById(R.id.btn_join_game);

        btnCreateGame.setOnClickListener(this);
        btnJoinGame.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == btnCreateGame){
            try {
                bingoServerCalls.createGame(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else if(view == btnJoinGame){
            startActivity(new Intent(HomeActivity.this,JoinGameActivity.class));
        }
    }
}
