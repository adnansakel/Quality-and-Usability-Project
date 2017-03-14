package com.example.adnansakel.bingo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.example.adnansakel.bingo.HttpHelper.BingoServerClient;
import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.Model.Chat;
import com.example.adnansakel.bingo.Model.Game;
import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.Util.AppConstants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.*;
import com.loopj.android.http.*;

import java.io.ByteArrayOutputStream;
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

    ProgressDialog progress;

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
        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);
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
        System.out.println("ProfPhoto test");
        if(player.getBmpProfilePhoto()!=null){
            System.out.println("Converted to byte array");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            player.getBmpProfilePhoto().compress(Bitmap.CompressFormat.JPEG, 10, bytes);
            jsonObject.put(AppConstants.PROFILE_PHOTO,Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));

        }

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

                progress.dismiss();

                try {

                    SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.PLAYER_INFO,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editUserinfo = sharedPreferences.edit();

                    if(response.get(AppConstants.NAME).toString()!=null && !response.get(AppConstants.NAME).toString().equals("null")){
                        bingoGameModel.getMyPlayer().setName((String)response.get(AppConstants.NAME));
                        editUserinfo.putString(AppConstants.NAME,(String)response.get(AppConstants.NAME));
                    }

                    if(response.get(AppConstants.PLAYER_ID).toString()!=null && !response.get(AppConstants.PLAYER_ID).toString().equals("null")){
                        bingoGameModel.getMyPlayer().setPlayerID((String)response.get(AppConstants.PLAYER_ID));
                        editUserinfo.putString(AppConstants.PLAYER_ID,(String)response.get(AppConstants.PLAYER_ID));
                    }

                    if(response.get(AppConstants.AGE).toString()!=null && !response.get(AppConstants.AGE).toString().equals("null")){
                        bingoGameModel.getMyPlayer().setAge((String)response.get(AppConstants.AGE));
                        editUserinfo.putString(AppConstants.AGE,(String)response.get(AppConstants.AGE));
                    }

                    if(response.get(AppConstants.GENDER).toString()!=null && !response.get(AppConstants.GENDER).toString().equals("null")){
                        bingoGameModel.getMyPlayer().setGender((String)response.get(AppConstants.GENDER));
                        editUserinfo.putString(AppConstants.GENDER,(String)response.get(AppConstants.GENDER));
                    }

                    if(response.get(AppConstants.EMAIL).toString()!=null && !response.get(AppConstants.EMAIL).toString().equals("null")){
                        bingoGameModel.getMyPlayer().setEmail((String)response.get(AppConstants.EMAIL));
                        editUserinfo.putString(AppConstants.EMAIL,(String)response.get(AppConstants.EMAIL));

                    }
                    //bingoGameModel.getMyPlayer().setAge((String)response.get(AppConstants.AGE));
                    //bingoGameModel.getMyPlayer().setPlayerID((String)response.get(AppConstants.PLAYER_ID));
                    //bingoGameModel.getMyPlayer().setGender((String)response.get(AppConstants.GENDER));
                    //editUserinfo.putString(AppConstants.PLAYER_ID,(String)response.get(AppConstants.PLAYER_ID));

                    //editUserinfo.putString(AppConstants.AGE,(String)response.get(AppConstants.AGE));
                    //editUserinfo.putString(AppConstants.GENDER,(String)response.get(AppConstants.GENDER));

                    editUserinfo.commit();
                    context.startActivity(new Intent((RegistrationActivity)context, RulesActivity.class));
                    ((RegistrationActivity)context).finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(response);



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){

                progress.dismiss();

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

    public void createGame(Game myGame, final Player myPlayer) throws JSONException{

        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.CREATOR_ID,myPlayer.getPlayerID());
        jsonObject.put(AppConstants.CREATOR_NAME,myPlayer.getName());
        jsonObject.put(AppConstants.IF_BINGO,AppConstants.FALSE);
        jsonObject.put(AppConstants.STATUS,AppConstants.INACTIVE);
        jsonObject.put(AppConstants.GAME_NAME,myGame.getGameName());
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

                progress.dismiss();

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

                progress.dismiss();

                System.out.println(""+statusCode);
            }
        });

    }

    private void selectGameRequestbyCreator(Player myPlayer, final Game myGame) throws JSONException{
        /*
        * This method is called when user creates a game and application receives the successful response from server
        * This method then adds the creator into the game without user intervention
        * */
        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);

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
                progress.dismiss();
                //Toast.makeText(context,response.toString(),Toast.LENGTH_LONG);
                bingoGameModel.setMyGame(myGame);
                context.startActivity(new Intent(context,LobbyActivity.class));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){

                progress.dismiss();

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

        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);

        BingoServerClient.get(AppConstants.GAME_LIST_URL,null,new JsonHttpResponseHandler(){

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                    if(progress!=null){progress.dismiss();}

                    System.out.println(response.toString());
                    bingoGameModel.removeAllPlayers();
                    for(int i = 0; i < response.length(); i++){
                        Game mgame = new Game();
                        try {
                            JSONObject gameObjwith_player_number = response.getJSONObject(i);
                            JSONObject gameObj = gameObjwith_player_number.getJSONObject(AppConstants.GAME);
                            int numofPlayers = (int) gameObjwith_player_number.get(AppConstants.NUMBER_OF_PLAYERS);
                            mgame.setNumberOfPlayers(""+numofPlayers);

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

                            if(gameObj.get(AppConstants.GAME_NAME).toString()!="null"){
                                mgame.setGameName((String)gameObj.get(AppConstants.GAME_NAME));
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

                    if(progress!=null){progress.dismiss();}
                    System.out.println(""+statusCode);
                }
            });
    }

    public void selectGame(Player myPlayer, final Game myGame)throws JSONException{

        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);

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
                if(progress!=null){progress.dismiss();}
                //Toast.makeText(context,response.toString(),Toast.LENGTH_LONG);
                try {
                    if(response.get(AppConstants.GAME_ID).toString().equals(null)||response.get(AppConstants.GAME_ID).toString().equals("null")){
                        Toast.makeText(context,"This game is already active or completed. You cannot join.",Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("response : "+response.toString());
                bingoGameModel.setMyGame(myGame);
                context.startActivity(new Intent(context,LobbyActivity.class));
                ((JoinGameActivity)context).finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){

                if(progress != null){progress.dismiss();}

                System.out.println(""+statusCode);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String string, Throwable throwable){
                if(progress != null){progress.dismiss();}
                //System.out.println("response on failure: "+statusCode+","+headers.toString()+","+toString());

            }
        });

    }

    public void getPlayersInLobby(Game myGame) throws JSONException{


        BingoServerClient.get(AppConstants.LOBBY_URL+myGame.getGameID(),null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                //System.out.println(""+response.toString());
                System.out.print(response.toString());
                try {
                JSONArray mjsonArray = response.getJSONArray(AppConstants.GAME_PLAYER);
                bingoGameModel.getPlayerlist().clear();
                for(int i = 0; i < mjsonArray.length(); i++){

                        JSONObject jplayer = mjsonArray.getJSONObject(i);
                        Player player = new Player();
                        if(jplayer.get(AppConstants.NAME).toString()!=null){
                            player.setName(jplayer.get(AppConstants.NAME).toString());
                        }
                        if(jplayer.get(AppConstants.PLAYER_ID).toString()!=null){
                            player.setPlayerID(jplayer.get(AppConstants.PLAYER_ID).toString());
                        }

                        bingoGameModel.addPlayer(player);



                }

                mjsonArray = response.getJSONArray(AppConstants.LOBBY_CHAT);

                    for(int i = 0; i < mjsonArray.length(); i++){
                        JSONObject jChat = mjsonArray.getJSONObject(i);

                        Chat chat = new Chat();
                        if(jChat.get(AppConstants.PLAYER_ID).toString()!=null&&jChat.get(AppConstants.TIME).toString()!=null){
                            chat.setPlayerID(jChat.get(AppConstants.PLAYER_ID).toString());
                            chat.setPlayerName(jChat.get(AppConstants.NAME).toString());
                            chat.setTime(jChat.get(AppConstants.TIME).toString());
                            chat.setMessage(jChat.get(AppConstants.MESSAGE).toString());
                            if(bingoGameModel.isItNewLobbyChat(chat)){
                                bingoGameModel.addLobbyChat(chat);
                            }
                        }
                    }

                if(response.get(AppConstants.GAME_STATUS).toString()!=null){
                    bingoGameModel.getMyGame().setStatus(response.get(AppConstants.GAME_STATUS).toString());
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

    private String prev_longest_match="";
    public void postLongestMatch(final Player myPlayer, int longestMatch)throws JSONException{
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.NAME,myPlayer.getName());
        jsonObject.put(AppConstants.LONGEST_MATCH,myPlayer.getName()+"_"+myPlayer.getPlayerID()+","+longestMatch);
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
                    System.out.print("If bingo response:"+response.toString());
                    JSONObject gameObj = (JSONObject) response.get(AppConstants.GAME);
                    if(gameObj.get(AppConstants.IF_BINGO).toString().length()>0&&gameObj.get(AppConstants.IF_BINGO).toString()!=null){
                        if(gameObj.get(AppConstants.IF_BINGO).toString().equals(AppConstants.TRUE)){
                            AppConstants.IF_BINGO_FOUND = 1;
                            System.out.println("Bingo found from other" + gameObj.get(AppConstants.WINNER).toString());
                            if(gameObj.get(AppConstants.WINNER).toString()!=null){
                                System.out.println("winner found from other");
                                bingoGameModel.getMyGame().setWinner(gameObj.get(AppConstants.WINNER).toString());
                                bingoGameModel.setWinner(gameObj.get(AppConstants.WINNER).toString());
                            }

                            return;
                        }
                    }
                    System.out.println("!!!!!!!!!!!!!!!!!!+++++++++++++++++++++++++!!!!!!!!!!!!!!!!!!!!!");
                    JSONArray mjsonArray = response.getJSONArray(AppConstants.MAINGAME_CHAT);
                    System.out.println("Num of chats: "+mjsonArray.length());
                    for(int i = 0; i < mjsonArray.length(); i++){
                        JSONObject jChat = mjsonArray.getJSONObject(i);
                        Chat chat = new Chat();
                        System.out.println("Name:"+jChat.get(AppConstants.NAME)+", Message: "+jChat.get(AppConstants.MESSAGE));
                        if(jChat.get(AppConstants.PLAYER_ID).toString()!=null&&jChat.get(AppConstants.TIME).toString()!=null){
                            chat.setPlayerID(jChat.get(AppConstants.PLAYER_ID).toString());
                            chat.setPlayerName(jChat.get(AppConstants.NAME).toString());
                            chat.setTime(jChat.get(AppConstants.TIME).toString());
                            chat.setMessage(jChat.get(AppConstants.MESSAGE).toString());
                            System.out.println("ChatMessage: "+chat.getMessage());
                            if(bingoGameModel.isItNewMainGameChat(chat)){
                                System.out.println("ChatMessage new: "+chat.getMessage());
                                bingoGameModel.addMainGameChat(chat);
                            }
                        }
                    }
                    if(gameObj.get(AppConstants.LONGEST_MATCH).toString()!=null && !gameObj.get(AppConstants.LONGEST_MATCH).toString().equalsIgnoreCase("null")){
                        if(!gameObj.get(AppConstants.LONGEST_MATCH).toString().equals(AppConstants.LONGEST_MATCH_STR)){
                            String [] str = gameObj.get(AppConstants.LONGEST_MATCH).toString().split(",");
                            System.out.println("Longest match:" + gameObj.get(AppConstants.LONGEST_MATCH).toString());
                            String[] name_playerID = str[0].split("_");
                            String name = name_playerID[0];
                            String playerID = name_playerID[1];
                            String score = str[1];//.replaceAll("\\s+","");
                            System.out.println(name+" "+"needs"+Integer.valueOf(str[1])+"more match only!");


                            //Toast toast = Toast.makeText(context,name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!",Toast.LENGTH_SHORT);
                            //toast.setGravity(Gravity.BOTTOM,0,0);
                            //toast.show();
                            //bingoGameModel.setNotificationText(name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!");
                            Chat chat = new Chat();
                            chat.setPlayerName("Bingo System");
                            chat.setPlayerID("");
                            chat.setTime("");
                            chat.setMessage(name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!");
                            if(!chat.getMessage().equals(prev_longest_match)&&Integer.valueOf(score)>0
                                    && !chat.getPlayerID().equals(myPlayer.getPlayerID())){
                                bingoGameModel.addMainGameChat(chat);
                            }
                            prev_longest_match = name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!";
                        }

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

    public void sayBingo(final Player myPlayer) throws JSONException{

        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.WINNER,myPlayer.getPlayerID());
        System.out.println("Winner should be:"+myPlayer.getPlayerID());
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

                if(progress != null){progress.dismiss();}

                System.out.println("Say Bingo:"+response.toString());



                try {
                    postLongestMatchafterSayBingo(myPlayer,bingoGameModel.getmyLongestMatch());//should br removed after server issue fixed
                    if(response.get(AppConstants.WINNER).toString()!=null && !response.get(AppConstants.WINNER).toString().equals("null")){

                    //    bingoGameModel.setWinner(response.get(AppConstants.WINNER).toString());//commented for the time being. Untill server issue fixed
                        //Toast.makeText(context,"Congrasulations !!!"+response.get(AppConstants.WINNER).toString(),Toast.LENGTH_LONG).show();
                    }
                    else{
                        //Toast.makeText(context,"Congrasulations !!!",Toast.LENGTH_LONG).show();
                     //   bingoGameModel.setWinner("");//commented for the time being. Untill server issue fixed
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                if(progress != null){progress.dismiss();}
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

    public void removeFromGame(Player myPlayer, Game myGame, final Handler handler, final Runnable runnable) throws JSONException{

        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());
        jsonObject.put(AppConstants.GAME_ID,myGame.getGameID());
        //jsonObject.put(AppConstants.GAME_ID,bingoGameModel.getMyGame().getGameID());

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BingoServerClient.post(context,AppConstants.REMOVE_PLAYER_FROM_GAME_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                if(progress != null){progress.dismiss();}



                try {
                    //System.out.println(response.get(AppConstants.MESSAGE).toString());
                    if(response.get(AppConstants.MESSAGE).toString().equals(AppConstants.SUCCESS)){
                        if(handler!=null && runnable!=null){
                            handler.removeCallbacks(runnable);
                        }

                        ((LobbyActivity)context).finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                if(progress != null){progress.dismiss();}
                Toast.makeText(context,"Sorry. Could remove you from this game. Please try again",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void notifyGameStatus(Game myGame, final Handler handler, final Runnable runnable) throws JSONException{

        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AppConstants.STATUS,myGame.getStatus());
        jsonObject.put(AppConstants.GAME_ID,myGame.getGameID());
        //jsonObject.put(AppConstants.GAME_ID,bingoGameModel.getMyGame().getGameID());

        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BingoServerClient.post(context,AppConstants.GAME_STATUS_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                if(progress != null){progress.dismiss();}

                try {
                    if(response.get(AppConstants.MESSAGE).toString().equals(AppConstants.SUCCESS)){
                        if(handler!=null){
                            if(runnable!=null)handler.removeCallbacks(runnable);
                        }
                        bingoGameModel.getMyGame().setStatus(AppConstants.INACTIVE);
                        context.startActivity(new Intent(context,PresGameStartActivity.class).putExtra(AppConstants.LOBBY_WAS_FULL,false));
                        ((LobbyActivity)context).finish();
                    }
                    else{
                        Toast.makeText(context,"Could not start the game. Please try again",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){
                if(progress != null){progress.dismiss();}
                Toast.makeText(context,"Could not start the game. Please try again",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendChatFromLobby(Chat chat){

        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());
        try {
            jsonObject.put(AppConstants.NAME,""+chat.getPlayerName());
            jsonObject.put(AppConstants.PLAYER_ID,""+chat.getPlayerID());
            jsonObject.put(AppConstants.GAME_ID,""+bingoGameModel.getMyGame().getGameID());
            //jsonObject.put(AppConstants.GAME_ID,myGame.getGameID());
            jsonObject.put(AppConstants.MESSAGE,""+chat.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        BingoServerClient.post(context,AppConstants.LOBBY_CHAT_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){

                System.out.println(""+statusCode);
            }
        });
    }

    public void sendChatFromMainGame(Chat chat){

        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());
        try {
            jsonObject.put(AppConstants.NAME,""+chat.getPlayerName());
            jsonObject.put(AppConstants.PLAYER_ID,""+chat.getPlayerID());
            jsonObject.put(AppConstants.GAME_ID,bingoGameModel.getMyGame().getGameID());
            jsonObject.put(AppConstants.MESSAGE,""+chat.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        BingoServerClient.post(context,AppConstants.MAINGAME_CHAT_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){



                System.out.println(""+statusCode);
            }
        });
    }


    public void uploadProfilePhoto(Player player, final Bitmap bmpProfilePhoto){
        progress = ProgressDialog.show(context, null,
                null, true);
        progress.setContentView(R.layout.progressdialogview);
        progress.setCancelable(true);
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.PLAYER_ID,myPlayer.getPlayerID());
        try {

            jsonObject.put(AppConstants.PLAYER_ID,""+player.getPlayerID());
            //jsonObject.put(AppConstants.GAME_ID,myGame.getGameID());
            if(bmpProfilePhoto!=null){
                System.out.println("Converted to byte array");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bmpProfilePhoto.compress(Bitmap.CompressFormat.JPEG, 10, bytes);
                jsonObject.put(AppConstants.PROFILE_PHOTO,Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT));

            }
            else{
                Toast.makeText(context,"Sorry, could not update new profile photo",Toast.LENGTH_SHORT).show();
                progress.dismiss();
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        BingoServerClient.post(context,AppConstants.PLAYER_PHOTO_UPDATE_URL,entity,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                if(progress!=null){progress.dismiss();}
                //Update new photo
                bingoGameModel.updateProfilePhoto(bmpProfilePhoto);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response){

                if(progress!=null){progress.dismiss();}
                Toast.makeText(context,"Sorry, could not update new profile photo",Toast.LENGTH_SHORT).show();

                System.out.println(""+statusCode);
            }
        });
    }

    public void postLongestMatchafterSayBingo(final Player myPlayer, int longestMatch)throws JSONException{
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put(AppConstants.NAME,myPlayer.getName());
        jsonObject.put(AppConstants.LONGEST_MATCH,myPlayer.getName()+"_"+myPlayer.getPlayerID()+","+longestMatch);
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
                    System.out.print("If bingo response:"+response.toString());
                    JSONObject gameObj = (JSONObject) response.get(AppConstants.GAME);
                    if(gameObj.get(AppConstants.IF_BINGO).toString().length()>0&&gameObj.get(AppConstants.IF_BINGO).toString()!=null){
                        if(gameObj.get(AppConstants.IF_BINGO).toString().equals(AppConstants.TRUE)){
                            AppConstants.IF_BINGO_FOUND = 1;
                            System.out.println("Bingo found from other" + gameObj.get(AppConstants.WINNER).toString());
                            if(gameObj.get(AppConstants.WINNER).toString()!=null){
                                System.out.println("winner found from other");
                                bingoGameModel.getMyGame().setWinner(gameObj.get(AppConstants.WINNER).toString());
                                bingoGameModel.setWinner(gameObj.get(AppConstants.WINNER).toString());
                            }

                            return;
                        }
                    }
                    System.out.println("!!!!!!!!!!!!!!!!!!+++++++++++++++++++++++++!!!!!!!!!!!!!!!!!!!!!");
                    JSONArray mjsonArray = response.getJSONArray(AppConstants.MAINGAME_CHAT);
                    System.out.println("Num of chats: "+mjsonArray.length());
                    for(int i = 0; i < mjsonArray.length(); i++){
                        JSONObject jChat = mjsonArray.getJSONObject(i);
                        Chat chat = new Chat();
                        System.out.println("Name:"+jChat.get(AppConstants.NAME)+", Message: "+jChat.get(AppConstants.MESSAGE));
                        if(jChat.get(AppConstants.PLAYER_ID).toString()!=null&&jChat.get(AppConstants.TIME).toString()!=null){
                            chat.setPlayerID(jChat.get(AppConstants.PLAYER_ID).toString());
                            chat.setPlayerName(jChat.get(AppConstants.NAME).toString());
                            chat.setTime(jChat.get(AppConstants.TIME).toString());
                            chat.setMessage(jChat.get(AppConstants.MESSAGE).toString());
                            System.out.println("ChatMessage: "+chat.getMessage());
                            if(bingoGameModel.isItNewMainGameChat(chat)){
                                System.out.println("ChatMessage new: "+chat.getMessage());
                                bingoGameModel.addMainGameChat(chat);
                            }
                        }
                    }
                    if(gameObj.get(AppConstants.LONGEST_MATCH).toString()!=null && !gameObj.get(AppConstants.LONGEST_MATCH).toString().equalsIgnoreCase("null")){
                        if(!gameObj.get(AppConstants.LONGEST_MATCH).toString().equals(AppConstants.LONGEST_MATCH_STR)){
                            String [] str = gameObj.get(AppConstants.LONGEST_MATCH).toString().split(",");
                            System.out.println("Longest match:" + gameObj.get(AppConstants.LONGEST_MATCH).toString());
                            String[] name_playerID = str[0].split("_");
                            String name = name_playerID[0];
                            String playerID = name_playerID[1];
                            String score = str[1];//.replaceAll("\\s+","");
                            System.out.println(name+" "+"needs"+Integer.valueOf(str[1])+"more match only!");


                            //Toast toast = Toast.makeText(context,name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!",Toast.LENGTH_SHORT);
                            //toast.setGravity(Gravity.BOTTOM,0,0);
                            //toast.show();
                            //bingoGameModel.setNotificationText(name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!");
                            Chat chat = new Chat();
                            chat.setPlayerName("Bingo System");
                            chat.setPlayerID("");
                            chat.setTime("");
                            chat.setMessage(name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!");
                            if(!chat.getMessage().equals(prev_longest_match)&&Integer.valueOf(score)>0
                                    && !chat.getPlayerID().equals(myPlayer.getPlayerID())){
                                bingoGameModel.addMainGameChat(chat);
                            }
                            prev_longest_match = name+" "+"needs "+(5-Integer.valueOf(score))+" more match only!";
                        }

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


}
