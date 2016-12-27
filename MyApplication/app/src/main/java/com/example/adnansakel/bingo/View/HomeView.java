package com.example.adnansakel.bingo.View;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.R;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class HomeView implements Observer{

    View view;
    BingoGameModel bingoGameModel;
    TextView txtGameTitle;
    Button btnCreateGame;
    Button btnJoinGame;

    public HomeView(View view, BingoGameModel bingoGameModel){
        this.view = view;
        this.bingoGameModel = bingoGameModel;
        initialize();
    }

    private void initialize(){
       // txtGameTitle = (TextView)view.findViewById(R.id.txt_game_title);
        btnCreateGame = (Button)view.findViewById(R.id.btn_create_game);
        btnJoinGame = (Button)view.findViewById(R.id.btn_join_game);
    }

    @Override
    public void update(Observable observable, Object o) {

        if(observable instanceof BingoGameModel){

        }
    }
}
