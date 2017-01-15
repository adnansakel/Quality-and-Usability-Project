package com.example.adnansakel.bingo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.Util.AppConstants;

import org.json.JSONException;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignIn;
    EditText editTextName;
    EditText editTextAge;
    EditText editTextGender;
    EditText editTextEmail;
    BingoServerCalls bingoServerCalls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        //new HomeView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());
        initialize();
        getUserInfo();
    }

    private void initialize(){
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextAge = (EditText)findViewById(R.id.editTextAge);
        editTextGender = (EditText)findViewById(R.id.editTextGender);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
    }

    private void getUserInfo()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.PLAYER_INFO, Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString(AppConstants.PLAYER_ID,"");
        String name = sharedPreferences.getString(AppConstants.NAME,"");
        String gender = sharedPreferences.getString(AppConstants.GENDER,"");
        String age = sharedPreferences.getString(AppConstants.AGE,"");


        if(userID.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setPlayerID(userID);
        }
        if(name.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setName(name);
        }
        if(age.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setAge(age);
        }
        if(gender.length()>0){
            ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setGender(gender);
        }

        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getPlayerID().length()>0){
            startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
            this.finish();
        }

    }
    @Override
    public void onClick(View view) {
        //startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
        if(view == btnSignIn){
            if(editTextName.getText().length()>0){
                //making server call to register user
                Player player = new Player();
                player.setName(editTextName.getText().toString());
                if(editTextAge.getText().toString()!=null){player.setAge(editTextAge.getText().toString());}
                else{player.setAge("");}
                if(editTextGender.getText().toString()!=null){player.setGender(editTextGender.getText().toString());}
                else{player.setGender("");}
                if(editTextEmail.getText().toString()!=null){player.setEmail(editTextEmail.getText().toString());}
                else{player.setEmail("");}
                ((MyApplication)getApplication()).getBingoGameModel().setMyPlayer(player);
                try {
                    bingoServerCalls.registerUser(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show();
            }

        }

    }
}
