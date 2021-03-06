package com.example.adnansakel.bingo.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.adnansakel.bingo.BingoServerCalls;
import com.example.adnansakel.bingo.HttpHelper.MySingleton;
import com.example.adnansakel.bingo.JoinGameActivity;
import com.example.adnansakel.bingo.LobbyActivity;
import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.Model.Game;
import com.example.adnansakel.bingo.R;
import com.example.adnansakel.bingo.Util.AppConstants;

import org.json.JSONException;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Adnan Sakel on 12/30/2016.
 */
public class JoinGameView implements Observer, View.OnClickListener {
    View view;
    BingoGameModel bingoGameModel;
    LinearLayout llGameList;
    Context context;
    BingoServerCalls bingoServerCalls;
    TextView txtGamesAvailable;
    public JoinGameView(View view, BingoGameModel bingoGameModel, Context context){
        this.view = view;
        bingoGameModel.addObserver(this);
        this.bingoGameModel = bingoGameModel;
        this.context = context;
        bingoServerCalls = new BingoServerCalls(bingoGameModel,context);
        initialize();
    }

    private void initialize(){
        llGameList = (LinearLayout)view.findViewById(R.id.llGamesAvailable);
        txtGamesAvailable = (TextView)view.findViewById(R.id.txtGamesAvailable);
        if(bingoGameModel.getGamelist().size()==0){
            txtGamesAvailable.setText("Sorry, no games available");
        }
        /*
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(2, 0, 2, 0);
        View item_gamelist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_gamelist,null);
        Game game = new Game();
        game.setGameID("101");
        item_gamelist.setTag(game);
        item_gamelist.setOnClickListener(this);
        llGameList.addView(item_gamelist,layoutParams);
        item_gamelist = ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_gamelist,null);
        game = new Game();
        game.setGameID("102");
        item_gamelist.setTag(game);
        item_gamelist.setOnClickListener(this);
        llGameList.addView(item_gamelist,layoutParams);
        item_gamelist = ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_gamelist,null);
        game = new Game();
        game.setGameID("103");
        item_gamelist.setTag(game);
        item_gamelist.setOnClickListener(this);
        llGameList.addView(item_gamelist,layoutParams);
        */

    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof BingoGameModel){
            //System.out.println("Came here !");
            if(bingoGameModel.getGamelist().size()==0){
                txtGamesAvailable.setText("Sorry, no games available");
            }
            else{
                txtGamesAvailable.setText("Available Games");
            }
            if(o.toString().equals(AppConstants.GAME_LIST_UPDATED)){
                System.out.println("Came here ! too !!");
                llGameList.removeAllViews();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(2, 0, 2, 0);
                //int i = 0;
                for(Game mgame : bingoGameModel.getGamelist()){
                    //if(mgame.getCreatorID().equals(bingoGameModel.getMyPlayer().getPlayerID())){continue;}
                    View item_gamelist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_gamelist,null);
                    item_gamelist.setTag(mgame);
                    //i++;
                    ((TextView)item_gamelist.findViewById(R.id.txtGameID)).setText(mgame.getGameName());//instead of GameID showing game name
                    ((TextView)item_gamelist.findViewById(R.id.txtCreatorName)).setText("Created by "+mgame.getCreatorName());
                    int numofplayersjoined = Integer.valueOf(mgame.getNumberOfPlayers());
                    ((TextView)item_gamelist.findViewById(R.id.txtPlayerNeeded)).setText((5-numofplayersjoined)+" more players needed");
                    item_gamelist.setOnClickListener(this);
                    ImageView profPhotoImageView = (ImageView)item_gamelist.findViewById(R.id.imageViewPlayerImage);
                    MySingleton.getInstance(context).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+mgame.getCreatorID(),
                            ImageLoader.getImageListener(profPhotoImageView,
                                    R.drawable.user, R.drawable.user));

                    llGameList.addView(item_gamelist,layoutParams);
                }
                //game = new Game();
                //game.setGameID("103");

            }
        }
    }

    @Override
    public void onClick(View view) {
        System.out.println("Clicked:"+((Game)view.getTag()).getGameID());
        try {
            bingoServerCalls.selectGame(bingoGameModel.getMyPlayer(),(Game)view.getTag());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
