package com.example.adnansakel.bingo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adnansakel.bingo.View.LobbyView;

import org.json.JSONException;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class LobbyActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llPlayerList;
    TextView txtWaiting;
    Button btnStartGame;
    BingoServerCalls bingoServerCalls;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        new LobbyView(findViewById(R.id.ll_lobby_view),((MyApplication)getApplication()).getBingoGameModel(),this);
        System.out.println("From Lobby"+((MyApplication) getApplication()).getBingoGameModel().getMyGame().getCallingNumberlist().toString());
        initialize();
        checkforPlayers(2000);//checks for players after each two seconds;

    }

    private void initialize(){
        llPlayerList = (LinearLayout)findViewById(R.id.llPlayersinLobby);
        txtWaiting = (TextView)findViewById(R.id.txtWaiting);

        txtWaiting.setOnClickListener(this);
        handler = new Handler();
        //btnStartGame.setOnClickListener(this);
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

    private void checkforPlayers(final int milliseconds){
        runnable = new Runnable() {

            @Override
            public void run() {
                // do your stuff - don't create a new runnable here!

                    //System.out.println(counter);
                try {

                    bingoServerCalls.getPlayersInLobby(((MyApplication)getApplication()).getBingoGameModel().getMyGame());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                handler.postDelayed(this, milliseconds);


            }
        };

// start it with:
        handler.post(runnable);
    }

    @Override
    public void onClick(View view) {
        if(handler!=null){
            if(runnable!=null)handler.removeCallbacks(runnable);
        }
        startActivity(new Intent(LobbyActivity.this,MainGameActivity.class));
        this.finish();
    }


}
