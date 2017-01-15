package com.example.adnansakel.bingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adnansakel.bingo.View.LobbyView;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class LobbyActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llPlayerList;
    TextView txtWaiting;
    Button btnStartGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        new LobbyView(findViewById(R.id.ll_lobby_view),((MyApplication)getApplication()).getBingoGameModel(),this);
        System.out.println("From Lobby"+((MyApplication) getApplication()).getBingoGameModel().getMyGame().getCallingNumberlist().toString());
        initialize();
    }

    private void initialize(){
        llPlayerList = (LinearLayout)findViewById(R.id.llPlayersinLobby);
        txtWaiting = (TextView)findViewById(R.id.txtWaiting);
        txtWaiting.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);
        /*
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(2, 0, 2, 0);
        View item_playerlist = getLayoutInflater().inflate(R.layout.list_view_item_lobby,null);
        llPlayerList.addView(item_playerlist,layoutParams);
        item_playerlist = getLayoutInflater().inflate(R.layout.list_view_item_lobby,null);
        llPlayerList.addView(item_playerlist,layoutParams);
        item_playerlist = getLayoutInflater().inflate(R.layout.list_view_item_lobby,null);
        llPlayerList.addView(item_playerlist,layoutParams);
*/

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(LobbyActivity.this,MainGameActivity.class));
        this.finish();
    }
}
