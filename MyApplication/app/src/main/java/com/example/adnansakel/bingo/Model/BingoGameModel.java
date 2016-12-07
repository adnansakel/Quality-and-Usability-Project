package com.example.adnansakel.bingo.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class BingoGameModel extends Observable{
    private List<Integer> shuffledNumberSequence = new ArrayList<Integer>();
    private List<Integer> shuffledCalledNumberSequence = new ArrayList<Integer>();
    private String ifBingoIsFound;
    private String winner;


    public String getIfBingoIsFound() {
        return ifBingoIsFound;
    }

    public void setIfBingoIsFound(String ifBingoIsFound) {
        this.ifBingoIsFound = ifBingoIsFound;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }


    public List<Integer> getShuffledNumberSequence() {
        return shuffledNumberSequence;
    }

    public void setShuffledNumberSequence(List<Integer> shuffledNumberSequence) {
        this.shuffledNumberSequence = shuffledNumberSequence;
    }

    public List<Integer> getShuffledCalledNumberSequence() {
        return shuffledCalledNumberSequence;
    }

    public void setShuffledCalledNumberSequence(List<Integer> shuffledCalledNumberSequence) {
        this.shuffledCalledNumberSequence = shuffledCalledNumberSequence;
    }
}

