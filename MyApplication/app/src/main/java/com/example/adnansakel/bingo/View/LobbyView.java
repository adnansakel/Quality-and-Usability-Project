package com.example.adnansakel.bingo.View;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.adnansakel.bingo.HttpHelper.MySingleton;
import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.Model.Chat;
import com.example.adnansakel.bingo.Model.Game;
import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.MyApplication;
import com.example.adnansakel.bingo.R;
import com.example.adnansakel.bingo.Util.AppConstants;

import java.util.Observable;
import java.util.Observer;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class LobbyView implements Observer {


    View view;
    LinearLayout llPlayerList;
    LinearLayout llChatList;
    Context context;
    BingoGameModel bingoGameModel;
    TextView txtWaiting;
    public LobbyView(View view, BingoGameModel bingoGameModel, Context context){
        this.view = view;
        bingoGameModel.addObserver(this);
        this.bingoGameModel = bingoGameModel;
        this.context = context;
        initialize();
    }

    private void initialize(){
        llPlayerList = (LinearLayout)view.findViewById(R.id.llPlayersinLobby);
        llChatList = (LinearLayout)view.findViewById(R.id.ll_chat_msg_lobby);
        txtWaiting = (TextView)view.findViewById(R.id.txtWaiting);
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
int i = 0;
    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof BingoGameModel){
            if(o.toString().equals(AppConstants.PLAYER_LIST_UPDATED)){
                System.out.println("Came here ! too !!");
                txtWaiting.setText("Waiting for "+(5-bingoGameModel.getPlayerlist().size())+" more players ...");
                llPlayerList.removeAllViews();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(2, 0, 2, 0);
                //int i = 0;
                for(Player mplayer : bingoGameModel.getPlayerlist()){
                    View item_playerlist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_item_lobby,null);
                    item_playerlist.setTag(mplayer);
                    //i++;
                    ((TextView)item_playerlist.findViewById(R.id.txtName)).setText(mplayer.getName());
                    CircleImageView profPhotoImageView = (CircleImageView) item_playerlist.findViewById(R.id.imageViewPlayerImage);
                   // mNetworkImageView.setImageUrl(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+mplayer.getPlayerID(), MySingleton.getInstance(context).getImageLoader());
                    //item_playerlist.setOnClickListener(this);
                    MySingleton.getInstance(context).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+mplayer.getPlayerID(),
                            ImageLoader.getImageListener(profPhotoImageView,
                            R.drawable.user, R.drawable.user));
                    if(mplayer.getPlayerID().equals(bingoGameModel.getMyGame().getCreatorID())){
                        profPhotoImageView.setBorderWidth(15);
                        profPhotoImageView.setBorderColor(Color.parseColor("#f1c405"));
                    }
                    llPlayerList.addView(item_playerlist,layoutParams);


                }
                //game = new Game();
                //game.setGameID("103");

            }

            if(o.toString().equals(AppConstants.UPDATE_LOBBY_MESSAGE_LIST)){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                View item_chatlist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_lobby_message,null);
                item_chatlist.setLayoutParams(layoutParams);
                LinearLayout llChat = (LinearLayout)item_chatlist.findViewById(R.id.llChat);
                Chat chat  = new Chat();
                chat = bingoGameModel.getLobbyChatList().get(bingoGameModel.getLobbyChatList().size()-1);
                if(!chat.getPlayerID().equals(bingoGameModel.getMyPlayer().getPlayerID())){
                    llChat.setGravity(Gravity.RIGHT);
                    ((LinearLayout)item_chatlist.findViewById(R.id.llMsgBackground)).setBackgroundResource(R.drawable.rect_backgroud_orange);
                }
                else{
                    llChat.setGravity(Gravity.LEFT);
                    ((LinearLayout)item_chatlist.findViewById(R.id.llMsgBackground)).setBackgroundResource(R.drawable.rect_background);
                }
                //llChat.setLayoutParams(layoutParams);
                //llChat.setG
                ((TextView)item_chatlist.findViewById(R.id.txtName)).setText(""+chat.getPlayerName());
                ((TextView)item_chatlist.findViewById(R.id.txtMessage)).setText(""+chat.getMessage());
                CircleImageView profPhotoImageView = (CircleImageView) item_chatlist.findViewById(R.id.imageViewPlayerImage);
                // mNetworkImageView.setImageUrl(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+mplayer.getPlayerID(), MySingleton.getInstance(context).getImageLoader());
                //item_playerlist.setOnClickListener(this);
                MySingleton.getInstance(context).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+chat.getPlayerID(),
                        ImageLoader.getImageListener(profPhotoImageView,
                                R.drawable.user, R.drawable.user));
                llChatList.addView(item_chatlist);
                ScrollView ScrlChatList = ((ScrollView)this.view.findViewById(R.id.scrlChatList));


                ScrlChatList.fullScroll(View.FOCUS_DOWN);
            }
        }
    }


}
