package com.example.adnansakel.bingo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.Toast;

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
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;


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
            //System.out.println(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        BingoServerClient.post(context,AppConstants.REGISTRATION_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    bingoGameModel.getMyPlayer().setName((String)response.get(AppConstants.NAME));
                    bingoGameModel.getMyPlayer().setAge((String)response.get(AppConstants.AGE));
                    bingoGameModel.getMyPlayer().setPlayerID((String)response.get(AppConstants.PLAYER_ID));
                    bingoGameModel.getMyPlayer().setGender((String)response.get(AppConstants.GENDER));

                    SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.PLAYER_INFO,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editUserinfo = sharedPreferences.edit();
                    editUserinfo.commit();

                    editUserinfo.putString(AppConstants.PLAYER_ID,(String)response.get(AppConstants.PLAYER_ID));
                    editUserinfo.putString(AppConstants.NAME,(String)response.get(AppConstants.NAME));
                    editUserinfo.putString(AppConstants.AGE,(String)response.get(AppConstants.AGE));
                    editUserinfo.putString(AppConstants.GENDER,(String)response.get(AppConstants.GENDER));

                    context.startActivity(new Intent(context, HomeActivity.class));
                    ((RegistrationActivity)context).finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(response);



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

    public void createGame(final Player myPlayer) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.CREATOR_ID,myPlayer.getPlayerID());
        jsonObject.put(AppConstants.CREATOR_NAME,myPlayer.getName());
        jsonObject.put(AppConstants.IF_BINGO,AppConstants.FALSE);
        //System.out.println("CreatorID:"+myPlayer.getPlayerID());
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
            System.out.println(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        BingoServerClient.post(context,AppConstants.GAME_CREATION_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response);
                try {
                    bingoGameModel.getMyGame().setGameID((String)response.get(AppConstants.GAME_ID));

                    bingoGameModel.getMyGame().setCallingNumberlist(getCallingNumberList(
                            (String)response.get(AppConstants.CALLING_NUMBERS)));
                    System.out.println(response.get(AppConstants.IF_BINGO).toString());
                    if(response.get(AppConstants.CREATOR_NAME).toString()!="null"){
                        bingoGameModel.getMyGame().setCreatorName((String)response.get(AppConstants.CREATOR_NAME));
                    }
                    if(response.get(AppConstants.IF_BINGO).toString()!="null"){
                        bingoGameModel.getMyGame().setIfBingo((String)response.get(AppConstants.IF_BINGO));
                    }

                    if(response.get(AppConstants.WINNER).toString()!="null"){
                        bingoGameModel.getMyGame().setWinner((String)response.get(AppConstants.WINNER));
                    }

                    if(response.get(AppConstants.LONGEST_MATCH).toString()!="null"){
                        bingoGameModel.getMyGame().setLongestMatch((String)response.get(AppConstants.LONGEST_MATCH));
                    }
                    if(response.get(AppConstants.CREATOR_ID).toString()!="null"){
                        bingoGameModel.getMyGame().setCreatorID((String)response.get(AppConstants.CREATOR_ID));
                    }
                    if(response.get(AppConstants.CREATION_TIME).toString()!="null"){
                        bingoGameModel.getMyGame().setCreationTime((String)response.get(AppConstants.CREATION_TIME));
                    }
                    if(response.get(AppConstants.STATUS).toString()!="null"){
                        bingoGameModel.getMyGame().setStatus((String)response.get(AppConstants.STATUS));
                    }

                    if(((String)response.get(AppConstants.GAME_ID)).length()>0){
                        selectGameRequestbyCreator(myPlayer,bingoGameModel.getMyGame());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });

    }

    private void selectGameRequestbyCreator(Player myPlayer, final Game myGame) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());
        jsonObject.put(AppConstants.NAME,""+myPlayer.getName());
        jsonObject.put(AppConstants.PLAYER_ID,""+myPlayer.getPlayerID());
        //jsonObject.put(AppConstants.GAME_ID,myGame.getGameID());
        jsonObject.put(AppConstants.GAME_ID,""+myGame.getGameID());

        StringEntity entity = null;
        try {

            entity = new StringEntity(jsonObject.toString());
            //entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            //entity.setContentEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /*
        BingoServerClient.post(AppConstants.GAME_PLAYER_ADD_URL+"1092/555",null,new JsonHttpResponseHandler(){
            @Override
         public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

        Toast.makeText(context,response.toString(),Toast.LENGTH_LONG);
        System.out.println("Response:"+response.toString());
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
        System.out.println(""+statusCode);
    }
    });*/


        BingoServerClient.post(context,AppConstants.GAME_PLAYER_ADD_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                //Toast.makeText(context,response.toString(),Toast.LENGTH_LONG);
                bingoGameModel.setMyGame(myGame);
                context.startActivity(new Intent(context,LobbyActivity.class));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });
    }

    private List<Integer> getCallingNumberList(String sequence){
        List<Integer> callingnumList = new ArrayList<Integer>();
        String [] numbers = sequence.split(",");
        for(String num : numbers){
            callingnumList.add(Integer.valueOf(num));
        }
        return  callingnumList;
    }

    public void getAvailableGamelist(){
            BingoServerClient.get(AppConstants.GAME_LIST_URL,null,new JsonHttpResponseHandler(){

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                    //System.out.println(response.toString());
                    bingoGameModel.getGamelist().clear();
                    for(int i = 0; i < response.length(); i++){
                        Game mgame = new Game();
                        try {
                            JSONObject gameObj = response.getJSONObject(i);

                            if(gameObj.get(AppConstants.GAME_ID).toString()!="null"){
                                mgame.setGameID((String)gameObj.get(AppConstants.GAME_ID));
                            }
                            if(gameObj.get(AppConstants.CREATOR_NAME).toString()!="null"){
                                mgame.setCreatorName((String)gameObj.get(AppConstants.CREATOR_NAME));
                            }
                            if(gameObj.get(AppConstants.IF_BINGO).toString()!="null"){
                                mgame.setIfBingo((String)gameObj.get(AppConstants.IF_BINGO));
                            }

                            if(gameObj.get(AppConstants.WINNER).toString()!="null"){
                                mgame.setWinner((String)gameObj.get(AppConstants.WINNER));
                            }

                            if(gameObj.get(AppConstants.LONGEST_MATCH).toString()!="null"){
                                mgame.setLongestMatch((String)gameObj.get(AppConstants.LONGEST_MATCH));
                            }
                            if(gameObj.get(AppConstants.CREATOR_ID).toString()!="null"){
                                mgame.setCreatorID((String)gameObj.get(AppConstants.CREATOR_ID));
                            }
                            if(gameObj.get(AppConstants.CREATION_TIME).toString()!="null"){
                                mgame.setCreationTime((String)gameObj.get(AppConstants.CREATION_TIME));
                            }
                            if(gameObj.get(AppConstants.STATUS).toString()!="null"){
                                mgame.setStatus((String)gameObj.get(AppConstants.STATUS));
                            }

                            if(gameObj.get(AppConstants.CALLING_NUMBERS).toString()!=null){
                                mgame.setCallingNumberlist(getCallingNumberList(
                                        (String)gameObj.get(AppConstants.CALLING_NUMBERS)));
                            }

                            bingoGameModel.addGame(mgame);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                    System.out.println(""+statusCode);
                }
            });
    }

    public void selectGame(Player myPlayer, final Game myGame)throws JSONException{
        System.out.println("Game selection:"+"Game ID:"+myGame.getGameID()+"sequence:"+myGame.getCallingNumberlist().toString());
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());
        jsonObject.put(AppConstants.NAME,""+myPlayer.getName());
        jsonObject.put(AppConstants.PLAYER_ID,""+myPlayer.getPlayerID());
        //jsonObject.put(AppConstants.GAME_ID,myGame.getGameID());
        jsonObject.put(AppConstants.GAME_ID,""+myGame.getGameID());

        StringEntity entity = null;
        try {

            entity = new StringEntity(jsonObject.toString());
            //entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            //entity.setContentEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /*
        BingoServerClient.post(AppConstants.GAME_PLAYER_ADD_URL+"1092/555",null,new JsonHttpResponseHandler(){
            @Override
         public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

        Toast.makeText(context,response.toString(),Toast.LENGTH_LONG);
        System.out.println("Response:"+response.toString());
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
        System.out.println(""+statusCode);
    }
    });*/


        BingoServerClient.post(context,AppConstants.GAME_PLAYER_ADD_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                //Toast.makeText(context,response.toString(),Toast.LENGTH_LONG);
                bingoGameModel.setMyGame(myGame);
                context.startActivity(new Intent(context,LobbyActivity.class));
                ((JoinGameActivity)context).finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });

    }

    public void getPlayersInLobby(Game myGame) throws JSONException{
        BingoServerClient.get(AppConstants.LOBBY_URL+myGame.getGameID(),null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                bingoGameModel.getPlayerlist().clear();
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jplayer = response.getJSONObject(i);
                        Player player = new Player();
                        if(jplayer.get(AppConstants.NAME).toString()!=null){
                            player.setName(jplayer.get(AppConstants.NAME).toString());
                        }
                        if(jplayer.get(AppConstants.PLAYER_ID).toString()!=null){
                            player.setPlayerID(jplayer.get(AppConstants.PLAYER_ID).toString());
                        }

                        /*if(jplayer.get(AppConstants.NAME).toString()!=null){
                            player.setName(jplayer.get(AppConstants.NAME).toString());
                        }*/

                        bingoGameModel.addPlayer(player);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                System.out.println(""+statusCode);
            }
        });
    }

    public void postLongestMatch(Player myPlayer, int longestMatch)throws JSONException{
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.NAME,myPlayer.getName());
        jsonObject.put(AppConstants.LONGEST_MATCH,myPlayer.getName()+","+longestMatch);
        jsonObject.put(AppConstants.GAME_ID,bingoGameModel.getMyGame().getGameID());


        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        BingoServerClient.post(context,AppConstants.IFBINGO_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.get(AppConstants.IF_BINGO).toString()!=null){
                        if(response.get(AppConstants.IF_BINGO).toString().equals(AppConstants.TRUE)){
                            AppConstants.IF_BINGO_FOUND = 1;
                        }
                    }
                    if(response.get(AppConstants.LONGEST_MATCH).toString()!=null){
                        String [] str = response.get(AppConstants.LONGEST_MATCH).toString().split(",");
                        String name = str[0];
                        String score = str[1];//.replaceAll("\\s+","");
                        System.out.println(name+" "+"needs"+Integer.valueOf(str[1])+"more match only!");
                        Toast toast = Toast.makeText(context,name+" "+"needs "+(5-Integer.valueOf(str[1]))+" more match only!",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM,0,0);
                        toast.show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        jsonObject.put(AppConstants.IF_BINGO,AppConstants.TRUE);
        jsonObject.put(AppConstants.GAME_ID,bingoGameModel.getMyGame().getGameID());

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BingoServerClient.post(context,AppConstants.SAY_BINGO_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    if(response.get(AppConstants.WINNER).toString()!=null){
                        Toast.makeText(context,"Congrasulations !!!"+response.get(AppConstants.WINNER).toString(),Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(context,"Congrasulations !!!",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                Toast.makeText(context,"Could not say bingo. Please try again",Toast.LENGTH_SHORT).show();
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
