package com.example.adnansakel.bingo.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.Model.Game;
import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.R;
import com.example.adnansakel.bingo.Util.AppConstants;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class LobbyView implements Observer {


    View view;
    LinearLayout llPlayerList;
    Context context;
    BingoGameModel bingoGameModel;
    public LobbyView(View view, BingoGameModel bingoGameModel, Context context){
        this.view = view;
        bingoGameModel.addObserver(this);
        this.bingoGameModel = bingoGameModel;
        this.context = context;
        initialize();
    }

    private void initialize(){
        llPlayerList = (LinearLayout)view.findViewById(R.id.llPlayersinLobby);
        /*
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(2, 0, 2, 0);
        View item_playerlist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_lobby,null);
        llPlayerList.addView(item_playerlist,layoutParams);
        item_playerlist = ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_lobby,null);
        llPlayerList.addView(item_playerlist,layoutParams);
        item_playerlist = ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_lobby,null);
        llPlayerList.addView(item_playerlist,layoutParams);
        */
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof BingoGameModel){
            if(o.toString().equals(AppConstants.PLAYER_LIST_UPDATED)){
                System.out.println("Came here ! too !!");
                llPlayerList.removeAllViews();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(2, 0, 2, 0);
                //int i = 0;
                for(Player mplayer : bingoGameModel.getPlayerlist()){
                    View item_playerlist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_lobby,null);
                    item_playerlist.setTag(mplayer);
                    //i++;
                    ((TextView)item_playerlist.findViewById(R.id.txtName)).setText(mplayer.getName());
                    //item_playerlist.setOnClickListener(this);
                    llPlayerList.addView(item_playerlist,layoutParams);
                }
                //game = new Game();
                //game.setGameID("103");

            }
        }
    }


}
