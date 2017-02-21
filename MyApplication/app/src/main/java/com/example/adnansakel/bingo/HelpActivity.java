package com.example.adnansakel.bingo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Adnan Sakel on 2/11/2017.
 */

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_help);
        getSupportActionBar().setTitle("Help");
        //getSupportActionBar().hide();

        ((Button)findViewById(R.id.btnLeaveHelp)).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        HelpActivity.this.finish();
    }
}
