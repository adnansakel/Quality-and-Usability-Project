package com.example.adnansakel.bingo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.adnansakel.bingo.View.JoinGameView;

/**
 * Created by Adnan Sakel on 12/30/2016.
 */
public class JoinGameActivity extends AppCompatActivity{
    LinearLayout llGameList;
    BingoServerCalls bingoServerCalls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        new JoinGameView(findViewById(R.id.ll_join_game_view),((MyApplication)getApplication()).getBingoGameModel(),this);
        initialize();
    }

    private void initialize(){
        llGameList = (LinearLayout)findViewById(R.id.llGamesAvailable);
        bingoServerCalls.getAvailableGamelist();
        /*
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(2, 0, 2, 0);
        View item_playerlist = getLayoutInflater().inflate(R.layout.list_view_item_gamelist,null);
        llGameList.addView(item_playerlist,layoutParams);
        item_playerlist = getLayoutInflater().inflate(R.layout.list_view_item_gamelist,null);
        llGameList.addView(item_playerlist,layoutParams);
        item_playerlist = getLayoutInflater().inflate(R.layout.list_view_item_gamelist,null);
        llGameList.addView(item_playerlist,layoutParams);
        */


    }
}
