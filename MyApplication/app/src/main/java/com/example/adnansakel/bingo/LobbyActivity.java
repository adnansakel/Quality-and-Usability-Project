package com.example.adnansakel.bingo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adnansakel.bingo.Util.AppConstants;
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
        setContentView(R.layout.activity_lobby_guest);
        getSupportActionBar().setTitle("Lobby");
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        new LobbyView(findViewById(R.id.ll_lobby_view),((MyApplication)getApplication()).getBingoGameModel(),this);
        System.out.println("From Lobby"+((MyApplication) getApplication()).getBingoGameModel().getMyGame().getCallingNumberlist().toString());
        initialize();
        checkforPlayers(2000);//checks for players after each two seconds;

    }

    private void initialize(){
        llPlayerList = (LinearLayout)findViewById(R.id.llPlayersinLobby);
        txtWaiting = (TextView)findViewById(R.id.txtWaiting);
        btnStartGame = (Button)findViewById(R.id.btnStartGame);

        //txtWaiting.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);
        if(!((MyApplication) getApplication()).getBingoGameModel().getMyPlayer().getPlayerID().equals(
                ((MyApplication) getApplication()).getBingoGameModel().getMyGame().getCreatorID())){
            btnStartGame.setVisibility(View.GONE);
        }
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

                    if(((MyApplication)getApplication()).getBingoGameModel().getPlayerlist().size()>4){

                        /*handler.removeCallbacks(this);
                        startActivity(new Intent(LobbyActivity.this,MainGameActivity.class));
                        LobbyActivity.this.finish();*/
                        try {

                            bingoServerCalls.notifyGameStatus(((MyApplication)getApplication()).getBingoGameModel().getMyGame(),handler,runnable);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                    if(((MyApplication)getApplication()).getBingoGameModel().getMyGame().getStatus().equals(AppConstants.ACTIVE)){
                        handler.removeCallbacks(this);
                        startActivity(new Intent(LobbyActivity.this,MainGameActivity.class));
                        LobbyActivity.this.finish();
                        return;
                    }

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
        if(((MyApplication)getApplication()).getBingoGameModel().getPlayerlist().size()<2){
            Toast.makeText(this,"You need at least two players to start the game.",Toast.LENGTH_LONG).show();
            return;
        }


        try {

            bingoServerCalls.notifyGameStatus(((MyApplication)getApplication()).getBingoGameModel().getMyGame(),handler,runnable);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed(){
        //Toast.makeText(this,"This activity will exit",Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure want to exit Lobby of this game?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    bingoServerCalls.removeFromGame(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer(),
                            ((MyApplication)getApplication()).getBingoGameModel().getMyGame(),handler,runnable);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("No", null);
        builder.setCancelable(true);
        builder.show();
    }


}
