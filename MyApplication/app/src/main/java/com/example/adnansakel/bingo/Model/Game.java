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
    private String CreatorName = "";
    private String CreationTime = "";
    private String Status = "";
    private String GameName = "";
    private String numberOfPlayers = "";

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

    public String getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(String creationTime) {
        CreationTime = creationTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
