package com.example.adnansakel.bingo;

import android.app.Application;

import com.example.adnansakel.bingo.Model.BingoGameModel;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class MyApplication extends Application {

    private BingoGameModel bingoGameModel = BingoGameModel.getInstance();

    public BingoGameModel getBingoGameModel(){
        return bingoGameModel;
    }

}
