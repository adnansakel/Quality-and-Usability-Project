package com.example.adnansakel.bingo.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan Sakel on 12/28/2016.
 */
public class Game {
    private String GameID="";
    private List<Integer> callingNumberlist = new ArrayList<Integer>();
    private String IfBingo = "";
    private String Winner = "";
    private String LongestMatch = "";
    private String CreatorID = "";

    public String getGameID() {
        return GameID;
    }

    public void setGameID(String gameID) {
        GameID = gameID;
    }

    public List<Integer> getCallingNumberlist() {
        return callingNumberlist;
    }

    public void setCallingNumberlist(List<Integer> callingNumberlist) {
        this.callingNumberlist = callingNumberlist;
    }

    public String getIfBingo() {
        return IfBingo;
    }

    public void setIfBingo(String ifBingo) {
        IfBingo = ifBingo;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public String getLongestMatch() {
        return LongestMatch;
    }

    public void setLongestMatch(String longestMatch) {
        LongestMatch = longestMatch;
    }

    public String getCreatorID() {
        return CreatorID;
    }

    public void setCreatorID(String creatorID) {
        CreatorID = creatorID;
    }
}
