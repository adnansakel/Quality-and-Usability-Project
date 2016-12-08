package com.example.adnansakel.bingo;

import android.content.Context;

import com.example.adnansakel.bingo.Model.BingoGameModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class BingoServerCalls {

    private BingoGameModel bingoGameModel;
    private Context context;

    public BingoServerCalls(BingoGameModel bingoGameModel, Context context){
        this.context = context;
        this.bingoGameModel = bingoGameModel;
    }

    public void getShuffledCalledNumberSequence(){
     //a get request is sent to the serever to get the numbers
        String shuffledNumberSequence = "";
        List<Integer> numberSequence = new ArrayList<Integer>();
        //shuffledNumberSequence = "1, ";
        for(int i = 1; i <= 65; i++){
            numberSequence.add(i);
        }
        //shuffledNumberSequence +=" 65";
        Collections.shuffle(numberSequence);
        for(int i =0; i < 65; i++){
            System.out.println("Numbers: "+numberSequence.get(i));
        }
        bingoGameModel.setShuffledCalledNumberSequence(numberSequence);
        //this string should be got from server

        //return shuffledNumberSequence;
        //return "";
    }

    public void setShuffledNumberSequence(String shuffledNumberSequence){
        //a post request is sent to server
    }

    public void notifyServerWhenBingoFound(){
        //a post request is sent to server with a parameter ifBingo=yes or no;
    }

    public String checkServerIfBingoWasFound(){
        //a get request to server with parameter ifBingo;
        return "";
    }


}
