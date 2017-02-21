package com.example.adnansakel.bingo.Model;

/**
 * Created by Adnan Sakel on 2/19/2017.
 */

public class Chat {
    private String playerID = "";
    private String playerName = "";
    private String time = "";
    private String message = "";

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
