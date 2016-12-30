package com.example.adnansakel.bingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignIn;
    BingoServerCalls bingoServerCalls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        //new HomeView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());
        initialize();
    }

    private void initialize(){
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
        /*
        //making server call to register user
        try {
            bingoServerCalls.registerUser(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
