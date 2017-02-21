package com.example.adnansakel.bingo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.example.adnansakel.bingo.Util.AppConstants;

/**
 * Created by Adnan Sakel on 2/21/2017.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
        CheckBox chkPlayWithoutChat;
        CheckBox chkLimitedCallingNumbers;
        CheckBox chkWithoutNextNumberHint;
    @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Bingo Settings");

        chkLimitedCallingNumbers = (CheckBox)findViewById(R.id.chkLimitedCallingNumber);
        chkPlayWithoutChat = (CheckBox)findViewById(R.id.chkWithoutChat);
        chkWithoutNextNumberHint = (CheckBox)findViewById(R.id.chkWithoutNextNumberHint);

        chkWithoutNextNumberHint.setOnClickListener(this);
        chkPlayWithoutChat.setOnClickListener(this);
        chkLimitedCallingNumbers.setOnClickListener(this);

        if(AppConstants.PLAY_WITHOUT_CHAT){
            chkPlayWithoutChat.setChecked(true);
        }
        else{
            chkPlayWithoutChat.setChecked(false);
        }

        if(AppConstants.PLAY_WITHOUT_NEXT_NUMBER_HINT){
            chkWithoutNextNumberHint.setChecked(true);
        }
        else{
            chkWithoutNextNumberHint.setChecked(false);
        }

        }

        @Override
        public void onClick(View view){
            if(view==chkLimitedCallingNumbers){
                if(chkLimitedCallingNumbers.isChecked()){
                    AppConstants.PLAY_WITH_LIMITED_CALLING_NUMBERS = true;
                }
                else{
                    AppConstants.PLAY_WITH_LIMITED_CALLING_NUMBERS = false;
                }
            }
            if(view==chkPlayWithoutChat){
                if(chkPlayWithoutChat.isChecked()){
                    AppConstants.PLAY_WITHOUT_CHAT = true;
                }
                else{
                    AppConstants.PLAY_WITHOUT_CHAT = false;
                }
            }
            if(view==chkWithoutNextNumberHint){
                if(chkWithoutNextNumberHint.isChecked()){
                    AppConstants.PLAY_WITHOUT_NEXT_NUMBER_HINT = true;
                }
                else{
                    AppConstants.PLAY_WITHOUT_NEXT_NUMBER_HINT = false;
                }
            }
        }

}
