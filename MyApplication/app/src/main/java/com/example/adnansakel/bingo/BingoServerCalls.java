package com.example.adnansakel.bingo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class BingoServerCalls {

    public static void getShuffledCalledNumberSequence(){
     //a get request is sent to the serever to get the numbers
        String shuffledNumberSequence = "";
        List<Integer> numberSequence = new ArrayList<Integer>();
        shuffledNumberSequence = "1, ";
        for(int i = 2; i <= 64; i++){
            shuffledNumberSequence +=" "+i+",";
        }
        shuffledNumberSequence +=" 65";
        //this string should be got from server

        //return shuffledNumberSequence;
        //return "";
    }

    public static void setShuffledNumberSequence(String shuffledNumberSequence){
        //a post request is sent to server
    }

    public static void notifyServerWhenBingoFound(){
        //a post request is sent to server with a parameter ifBingo=yes or no;
    }

    public static String checkServerIfBingoWasFound(){
        //a get request to server with parameter ifBingo;
        return "";
    }


}
