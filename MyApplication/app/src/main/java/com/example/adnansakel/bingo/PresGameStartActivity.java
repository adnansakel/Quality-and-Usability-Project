package com.example.adnansakel.bingo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.adnansakel.bingo.Util.AppConstants;

/**
 * Created by Adnan Sakel on 2/22/2017.
 */

public class PresGameStartActivity extends AppCompatActivity{

    Handler mhandler;
    int count = 3;
    TextView txtNumberCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game_start);
        getSupportActionBar().setTitle("Game Bingo");
        getSupportActionBar().hide();
        txtNumberCounter= ((TextView)findViewById(R.id.txt_NumberCounter));
        mhandler = new Handler();
        if(getIntent().getBooleanExtra(AppConstants.LOBBY_WAS_FULL,false)){
            count = 3;
        }
        else if(((MyApplication) getApplication()).getBingoGameModel().getMyPlayer().getPlayerID().equals(
                    ((MyApplication) getApplication()).getBingoGameModel().getMyGame().getCreatorID())){
                count = 4;
        }
        else{
            count = 3;
        }


        ready_set_go();
    }
    Runnable runnable;

    private void ready_set_go(){
        runnable = new Runnable() {

            @Override
            public void run() {
                // do your stuff - don't create a new runnable here!

                //System.out.println("Sending message.");

                if(count<0){
                    mhandler.removeCallbacks(runnable);
                    startActivity(new Intent(PresGameStartActivity.this,MainGameActivity.class));
                    PresGameStartActivity.this.finish();
                    return;
                }
                txtNumberCounter.setText(""+count);
                if(count==0){txtNumberCounter.setText("GO");}
                txtNumberCounter.startAnimation(AnimationUtils.loadAnimation(PresGameStartActivity.this,
                        R.anim.fade_in));

                count--;
                mhandler.postDelayed(this,1250);
            }


        };

// start it with:
        mhandler.post(runnable);
    }
}
