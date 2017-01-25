package com.example.adnansakel.bingo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adnansakel.bingo.Util.AppConstants;
import com.example.adnansakel.bingo.View.HomeView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreateGame;
    Button btnJoinGame;
    BingoServerCalls bingoServerCalls;
    TextToSpeech textToSpeechtest;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Bingo Home");
        new HomeView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());
        //System.out.println("Player at home: "+((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getPlayerID());
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        initialize();
        //handler = new Handler();
        //handlerTest();
        /*
        textToSpeechtest = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeechtest.setLanguage(Locale.UK);
                }
            }
        });*/
    }

    /*
    int counter = 0;
    Runnable runnable;
    private void handlerTest(){
       runnable = new Runnable() {

            @Override
            public void run() {
                // do your stuff - don't create a new runnable here!

                //System.out.println(counter);

                if(counter > 5){
                    handler.removeCallbacks(this);
                    Toast.makeText(HomeActivity.this,"After callback removed "+counter,Toast.LENGTH_LONG).show();
                    HomeActivity.this.finish();
                    return;
                }

                System.out.println("Handler running "+ counter);

                handler.postDelayed(this, 1000);
                counter++;


            }
        };

// start it with:
        handler.post(runnable);
    }
    */

    private void initialize(){
        btnCreateGame = (Button)findViewById(R.id.btn_create_game);
        btnJoinGame = (Button)findViewById(R.id.btn_join_game);

        btnCreateGame.setOnClickListener(this);
        btnJoinGame.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == btnCreateGame){
            //handler.removeCallbacks(runnable);
            //startActivity(new Intent(HomeActivity.this,CreateGameActivity.class));
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeechtest.speak("22",TextToSpeech.QUEUE_FLUSH,null,"22");
            }
            else{
                textToSpeechtest.speak("22",TextToSpeech.QUEUE_FLUSH,null);
            }*/

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View mview = View.inflate(this,R.layout.creategame_dialog_view,null);
            builder.setView(mview);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(((EditText)mview.findViewById(R.id.editTextGameName)).getText().toString().length()>0){
                        ((MyApplication)getApplication()).getBingoGameModel().getMyGame().setGameName(
                                ((EditText)mview.findViewById(R.id.editTextGameName)).getText().toString());
                        try {
                            bingoServerCalls.createGame(((MyApplication)getApplication()).getBingoGameModel().getMyGame(),
                                    ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(HomeActivity.this,"You need to give a game name",Toast.LENGTH_LONG).show();
                    }
                }
            });
           // builder.setNegativeButton("No", null);
            builder.setCancelable(true);
            builder.show();



        }
        else if(view == btnJoinGame){
            startActivity(new Intent(HomeActivity.this,JoinGameActivity.class));
        }
    }

}
