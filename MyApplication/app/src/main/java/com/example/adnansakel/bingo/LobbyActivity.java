package com.example.adnansakel.bingo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.adnansakel.bingo.HttpHelper.MySingleton;
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

    Spinner spinnerMsg;
    Button btnSendMsg;

    //ImageView imgGender;
    //TextView txtNameAge;
    String nameage = "";
    //ImageView imgUserPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        getSupportActionBar().setTitle("Lobby");
        getSupportActionBar().hide();
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
        spinnerMsg = (Spinner)findViewById(R.id.spinner_Msg);
        btnSendMsg = (Button)findViewById(R.id.btnMsgSend);
        btnSendMsg.setOnClickListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lobby_messages, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerMsg.setAdapter(adapter);
        String msg = spinnerMsg.getSelectedItem().toString();
        //String[] msg = {"Hi", "Thank u","Good day!"};
        //SpinnerAdapter spadp = new Sp
        //imgGender = (ImageView)findViewById(R.id.imgGender);
        //imgUserPhoto = (ImageView)findViewById(R.id.imageUser);
        //txtNameAge = (TextView)findViewById(R.id.txtNameAge);

        //imgUserPhoto.setVisibility(View.GONE);
        //imgGender.setVisibility(View.GONE);
        //txtNameAge.setVisibility(View.GONE);


        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getName()!=null){
            nameage = ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getName();
        }

        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getAge()!=null){
            nameage = nameage+","+((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getAge();
        }

        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getGender()!=null){
            if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getGender().equals(AppConstants.MALE)){
               // imgGender.setImageResource(R.mipmap.male_mark);
            }
            if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getGender().equals(AppConstants.FEMALE)){
               // imgGender.setImageResource(R.mipmap.female_mark);
            }
            else{
               // imgGender.setVisibility(View.GONE);
            }
        }
        else{
            //imgGender.setVisibility(View.GONE);
        }


        btnStartGame.setOnClickListener(this);
        if(!((MyApplication) getApplication()).getBingoGameModel().getMyPlayer().getPlayerID().equals(
                ((MyApplication) getApplication()).getBingoGameModel().getMyGame().getCreatorID())){
            btnStartGame.setVisibility(View.GONE);
        }
        handler = new Handler();

        /*
        MySingleton.getInstance(this).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+
                        ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getPlayerID(),
                ImageLoader.getImageListener((ImageView)findViewById(R.id.imageUser),
                        R.drawable.user, R.drawable.user));
                        */

        //txtWaiting.setOnClickListener(this);

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
        if(view == btnStartGame){
            if(((MyApplication)getApplication()).getBingoGameModel().getPlayerlist().size()<2){
                Toast.makeText(this,"You need at least two players to start the game.",Toast.LENGTH_LONG).show();
            }
            else{
                try {

                    bingoServerCalls.notifyGameStatus(((MyApplication)getApplication()).getBingoGameModel().getMyGame(),handler,runnable);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }

        if(view == btnSendMsg){
            //Toast.makeText(this,spinnerMsg.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            ((MyApplication)getApplication()).getBingoGameModel().addLobbyMessage(spinnerMsg.getSelectedItem().toString());
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
