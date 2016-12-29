package com.example.adnansakel.bingo.Model;

import com.example.adnansakel.bingo.Util.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class BingoGameModel extends Observable{
    private List<Integer> shuffledNumberSequence = new ArrayList<Integer>();
    private List<Integer> shuffledCalledNumberSequence = new ArrayList<Integer>();
    private boolean ifBingoIsFound = false;
    private String winner;
    private int calledNumber;
    private String calledNumberColor;
    private Player myPlayer;
    private Game myGame = new Game();
    private int[][] bingo_pattern_search_grid;

    private List<Game> gamelist = new ArrayList<Game>();
    private List<Player> playerlist = new ArrayList<Player>();

    private String longestMatchPlayerInfo = "";

    //making the class singleton
    private static BingoGameModel bingoGameModel = new BingoGameModel();
    private BingoGameModel(){}
    public static BingoGameModel getInstance(){return bingoGameModel;}

    public void initializeBingoPatternSsearchGrid(){
        bingo_pattern_search_grid = new int[AppConstants.BINGO_GRID_SIZE][AppConstants.BINGO_GRID_SIZE];
        for(int i = 0; i < AppConstants.BINGO_GRID_SIZE; i++){
            for(int j = 0; j < AppConstants.BINGO_GRID_SIZE; j++){
                bingo_pattern_search_grid[i][j]=0;
                if(i == 2 && j == 2){
                    bingo_pattern_search_grid[i][j]=-1;
                }
            }
        }
    }

    public int[][] getBingoPatternSearchGrid(){
        return bingo_pattern_search_grid;
    }

    public void updateBingoPatternSearcgGrid(int i, int val){
        int row = (i-1) % AppConstants.BINGO_GRID_SIZE;
        int col = (i-1) / AppConstants.BINGO_GRID_SIZE;
        bingo_pattern_search_grid[row][col] = val;
        this.setChanged();
        this.notifyObservers(AppConstants.GRID_UPDATE);
    }
    public boolean getIfBingoIsFound() {
        return ifBingoIsFound;
    }

    public void setIfBingoIsFound(boolean ifBingoIsFound) {
        this.ifBingoIsFound = ifBingoIsFound;
        this.setChanged();
        this.notifyObservers(AppConstants.BINGO_FOUND);
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
        //System.out.println("Came here!");
        this.shuffledNumberSequence = shuffledNumberSequence;
        this.setChanged();
        this.notifyObservers(AppConstants.NUMBER_SEQUENCE_FOR_GRID);
    }

    public List<Integer> getShuffledCalledNumberSequence() {
        return shuffledCalledNumberSequence;
    }

    public void setShuffledCalledNumberSequence(List<Integer> shuffledCalledNumberSequence) {
        this.shuffledCalledNumberSequence = shuffledCalledNumberSequence;
        this.setChanged();
        this.notifyObservers(AppConstants.CALLING_NUMBER_SEQUENCE);
    }

    public int getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(int calledNumber) {
        this.calledNumber = calledNumber;
        this.setChanged();
        this.notifyObservers(AppConstants.A_NUMBER_IS_CALLED);
    }

    public String getCalledNumberColor() {
        return calledNumberColor;
    }

    public void setCalledNumberColor(String calledNumberColor) {
        this.calledNumberColor = calledNumberColor;
        this.setChanged();
        this.notifyObservers(AppConstants.CHANGE_CALLED_NUMBER_COLOR);
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public List<Game> getGamelist() {
        return gamelist;
    }

    public void setGamelist(List<Game> gamelist) {
        this.gamelist = gamelist;
    }

    public Game getMyGame() {
        return myGame;
    }

    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }

    public int getmyLongestMatch(){
        int [][] bingo_grid = getBingoPatternSearchGrid();

        //check for rows
        int row_sum;
        int col_sum;
        int row_sum_max = 0;
        int col_sum_max = 0;
        for(int i = 0; i < AppConstants.BINGO_GRID_SIZE; i++){
            row_sum = 0;
            col_sum = 0;
            for(int j = 0; j < AppConstants.BINGO_GRID_SIZE; j++){
                row_sum += bingo_grid[i][j];
                col_sum +=bingo_grid[j][i];
            }
            if(row_sum > row_sum_max){ row_sum_max = row_sum;}
            if(col_sum > col_sum_max){col_sum_max = col_sum;}

        }
        if(row_sum_max > col_sum_max) {
            return row_sum_max;
        }
        return col_sum_max;
    }

    public List<Player> getPlayerlist() {
        return playerlist;
    }

    public void setPlayerlist(List<Player> playerlist) {
        this.playerlist = playerlist;
    }

    public String getLongestMatchPlayerInfo() {
        return longestMatchPlayerInfo;
    }

    public void setLongestMatchPlayerInfo(String longestMatchPlayerInfo) {
        this.longestMatchPlayerInfo = longestMatchPlayerInfo;
    }
}

