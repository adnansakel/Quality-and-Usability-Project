package com.example.adnansakel.bingo;

import android.content.Context;

import com.example.adnansakel.bingo.HttpHelper.BingoServerClient;
import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.Model.Game;
import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.Util.AppConstants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.*;
import com.loopj.android.http.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


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

    /*public void getShuffledCalledNumberSequence(){
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
    }*/

    public void setShuffledNumberSequence(String shuffledNumberSequence){
        //a post request is sent to server
    }


    public String checkServerIfBingoWasFound(){
        //a get request to server with parameter ifBingo;
        return "";
    }

    public void registerUser(Player player) throws JSONException{
        /*RequestParams params = new RequestParams();
        params.put("FirstName","Sayem");
        params.put("LastName","Siam");
        params.put("Email","siam@example.com");
        params.put("Password","0587");*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.NAME,player.getName());
        jsonObject.put(AppConstants.AGE,player.getAge());
        jsonObject.put(AppConstants.EMAIL,player.getEmail());
        jsonObject.put(AppConstants.GENDER,player.getGender());
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        BingoServerClient.post(context,AppConstants.REGISTRATION_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });

        /*BingoServerClient.post("customers/",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Success: "+statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });*/
    }

    public void createGame(Player myPlayer) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        BingoServerClient.post(context,AppConstants.GAME_CREATION_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });

    }

    public void getAvailableGamelist(){
            BingoServerClient.get(AppConstants.GAME_LIST_URL,null,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                    System.out.println(""+statusCode);
                }
            });
    }

    public void selectGame(Player myPlayer, Game myGame)throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());
        jsonObject.put(AppConstants.GAME_ID,myGame.getGameID());

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        BingoServerClient.post(context,AppConstants.GAME_PLAYER_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });
    }

    public void getPlayersInLobby(){
        BingoServerClient.get(AppConstants.LOBBY_URL,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });
    }

    public void postLongestMatch(Player myPlayer, int longestMatch)throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.NAME,myPlayer.getName());
        jsonObject.put(AppConstants.LONGEST_MATCH,longestMatch);

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        BingoServerClient.post(context,AppConstants.IFBINGO_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });

    }

    public void sayBingo(Player myPlayer) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.WINNER,myPlayer.getName());
        jsonObject.put(AppConstants.IF_BINGO,true);

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BingoServerClient.post(context,AppConstants.SAY_BINGO_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });
    }

    public void getPlayerPhoto(Player player){
        BingoServerClient.get(AppConstants.PLAYER_PHOTO_URL+"/"+player.getPlayerID(),null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });
    }


}
