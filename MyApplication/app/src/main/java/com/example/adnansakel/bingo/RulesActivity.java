package com.example.adnansakel.bingo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Adnan Sakel on 2/20/2017.
 */

public class RulesActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        getSupportActionBar().setTitle("Bingo Rules");
        txtNext = (TextView)findViewById(R.id.txtNext);
        txtNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == txtNext){
            startActivity(new Intent(this,HomeActivity.class));
            this.finish();
        }
    }
}
