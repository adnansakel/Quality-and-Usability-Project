package com.example.adnansakel.bingo.View;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.adnansakel.bingo.HttpHelper.MySingleton;
import com.example.adnansakel.bingo.MainGameActivity;
import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.MyApplication;
import com.example.adnansakel.bingo.R;
import com.example.adnansakel.bingo.Util.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class MainGameView implements Observer {
    private View view;
    private BingoGameModel bingoGameModel;
    TextView txt_one;
    TextView txt_two;
    TextView txt_three;
    TextView txt_four;
    TextView txt_five;
    TextView txt_six;
    TextView txt_seven;
    TextView txt_eight;
    TextView txt_nine;
    TextView txt_ten;
    TextView txt_eleven;
    TextView txt_twelve;
    TextView txt_thirteen;
    TextView txt_fourteen;
    TextView txt_fifteen;
    TextView txt_sixteen;
    TextView txt_seventeen;
    TextView txt_eighteen;
    TextView txt_nineteen;
    TextView txt_twenty;
    TextView txt_twentione;
    TextView txt_twentitwo;
    TextView txt_twentithree;
    TextView txt_twentifour;
    TextView txt_twentifive;
    TextView txtCalledNumber;
    Button btnSayBingo;
    LinearLayout llPlayerList;
    LinearLayout llChatList;
    ImageView imgCallingNUmberCircle;

    View llendgame;

    ProgressBar progressBarTimer;
    //TextView txtCalledNumber;

    List sequencenumberList;

    Context context;

    public MainGameView(View view, BingoGameModel bingoGameModel, Context context){
        this.view = view;
        this.context = context;
        bingoGameModel.addObserver(this);
        this.bingoGameModel = bingoGameModel;
        initialize();
        populateGamePlayers();
    }



    private void initialize(){
        txt_one = (TextView)view.findViewById(R.id.txt_1);
        txt_two = (TextView)view.findViewById(R.id.txt_2);
        txt_three = (TextView)view.findViewById(R.id.txt_3);
        txt_four = (TextView)view.findViewById(R.id.txt_4);
        txt_five = (TextView)view.findViewById(R.id.txt_5);
        txt_six = (TextView)view.findViewById(R.id.txt_6);
        txt_seven = (TextView)view.findViewById(R.id.txt_7);
        txt_eight = (TextView)view.findViewById(R.id.txt_8);
        txt_nine = (TextView)view.findViewById(R.id.txt_9);
        txt_ten = (TextView)view.findViewById(R.id.txt_10);
        txt_eleven = (TextView)view.findViewById(R.id.txt_11);
        txt_twelve = (TextView)view.findViewById(R.id.txt_12);
        txt_thirteen = (TextView)view.findViewById(R.id.txt_13);
        txt_fourteen = (TextView)view.findViewById(R.id.txt_14);
        txt_fifteen = (TextView)view.findViewById(R.id.txt_15);
        txt_sixteen = (TextView)view.findViewById(R.id.txt_16);
        txt_seventeen = (TextView)view.findViewById(R.id.txt_17);
        txt_eighteen = (TextView)view.findViewById(R.id.txt_18);
        txt_nineteen = (TextView)view.findViewById(R.id.txt_19);
        txt_twenty = (TextView)view.findViewById(R.id.txt_20);
        txt_twentione = (TextView)view.findViewById(R.id.txt_21);
        txt_twentitwo = (TextView)view.findViewById(R.id.txt_22);
        txt_twentithree = (TextView)view.findViewById(R.id.txt_23);
        txt_twentifour = (TextView)view.findViewById(R.id.txt_24);
        txt_twentifive = (TextView)view.findViewById(R.id.txt_25);
        txtCalledNumber = (TextView)view.findViewById(R.id.txtCalledNumber);
        btnSayBingo = (Button)view.findViewById(R.id.btn_say_bingo);

        llPlayerList = (LinearLayout) view.findViewById(R.id.llPlayers);
        llChatList = (LinearLayout) view.findViewById(R.id.ll_chat_msg_maingame);

        llendgame = view.findViewById(R.id.ll_endgame);
        llendgame.setVisibility(View.GONE);

        imgCallingNUmberCircle = (ImageView)view.findViewById(R.id.imgCallingNUmberCircle);
        progressBarTimer = (ProgressBar)view.findViewById(R.id.progressBarTimer);
        progressBarTimer.setMax(100);
        progressBarTimer.setProgress(0);
        progressBarTimer.getProgressDrawable().setColorFilter(
                Color.parseColor("#ffd600"), android.graphics.PorterDuff.Mode.SRC_IN);
        //txt_one = (TextView)findViewById(R.id.txt_one);



        //sequencenumberList = ((MyApplication)getApplication()).getBingoGameModel().getShuffledNumberSequence();

        btnSayBingo.setClickable(false);


    }

    private void populateGamePlayers(){
        llPlayerList.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(2, 0, 2, 0);
        //int i = 0;
        for(Player mplayer : bingoGameModel.getPlayerlist()){
            View item_playerlist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_imageview_playerlist,null);
            item_playerlist.setTag(mplayer);
            //i++;

            ImageView profPhotoImageView = (ImageView)item_playerlist.findViewById(R.id.imageViewPlayerImage);
            // mNetworkImageView.setImageUrl(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+mplayer.getPlayerID(), MySingleton.getInstance(context).getImageLoader());
            //item_playerlist.setOnClickListener(this);
            MySingleton.getInstance(context).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+mplayer.getPlayerID(),
                    ImageLoader.getImageListener(profPhotoImageView,
                            R.drawable.user, R.drawable.user));
            llPlayerList.addView(item_playerlist,layoutParams);


        }
    }

    @Override
    public void update(Observable observable, Object data) {
        if(observable instanceof BingoGameModel){
            if(data.toString()== AppConstants.NUMBER_SEQUENCE_FOR_GRID){
                sequencenumberList = new ArrayList<Integer>();
                sequencenumberList = bingoGameModel.getShuffledNumberSequence();
                //System.out.println("Total numbers:"+sequencenumberList.size());
                txt_one.setText(""+sequencenumberList.get(0));
                txt_two.setText(""+sequencenumberList.get(1));
                txt_three.setText(""+sequencenumberList.get(2));
                txt_four.setText(""+sequencenumberList.get(3));
                txt_five.setText(""+sequencenumberList.get(4));
                txt_six.setText(""+sequencenumberList.get(5));
                txt_seven.setText(""+sequencenumberList.get(6));
                txt_eight.setText(""+sequencenumberList.get(7));
                txt_nine.setText(""+sequencenumberList.get(8));
                txt_ten.setText(""+sequencenumberList.get(9));
                txt_eleven.setText(""+sequencenumberList.get(10));
                txt_twelve.setText(""+sequencenumberList.get(11));
                //txt_thirteen.setText(""+sequencenumberList.get(12));
                txt_fourteen.setText(""+sequencenumberList.get(13));
                txt_fifteen.setText(""+sequencenumberList.get(14));
                txt_sixteen.setText(""+sequencenumberList.get(15));
                txt_seventeen.setText(""+sequencenumberList.get(16));
                txt_eighteen.setText(""+sequencenumberList.get(17));
                txt_nineteen.setText(""+sequencenumberList.get(18));
                txt_twenty.setText(""+sequencenumberList.get(19));
                txt_twentione.setText(""+sequencenumberList.get(20));
                txt_twentitwo.setText(""+sequencenumberList.get(21));
                txt_twentithree.setText(""+sequencenumberList.get(22));
                txt_twentifour.setText(""+sequencenumberList.get(23));
                txt_twentifive.setText(""+sequencenumberList.get(24));

            }

            if(data.toString()==AppConstants.A_NUMBER_IS_CALLED){
                txtCalledNumber.setText(""+bingoGameModel.getCalledNumber());
                progressBarTimer.setProgress(100);
                if(Integer.valueOf(""+bingoGameModel.getCalledNumber())<16){
                    imgCallingNUmberCircle.setImageResource(R.mipmap.no_ball_r);
                    progressBarTimer.getProgressDrawable().setColorFilter(
                            Color.parseColor("#e57373"), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else if(Integer.valueOf(""+bingoGameModel.getCalledNumber())<31){
                    imgCallingNUmberCircle.setImageResource(R.mipmap.no_ball_o);
                    progressBarTimer.getProgressDrawable().setColorFilter(
                            Color.parseColor("#ffb74d"), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else if(Integer.valueOf(""+bingoGameModel.getCalledNumber())<46){
                    imgCallingNUmberCircle.setImageResource(R.mipmap.no_ball_y);
                    progressBarTimer.getProgressDrawable().setColorFilter(
                            Color.parseColor("#ffd600"), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else if(Integer.valueOf(""+bingoGameModel.getCalledNumber())<61){
                    imgCallingNUmberCircle.setImageResource(R.mipmap.no_ball_g);
                    progressBarTimer.getProgressDrawable().setColorFilter(
                            Color.parseColor("#66bb6a"), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else{
                    imgCallingNUmberCircle.setImageResource(R.mipmap.no_ball_b);
                    progressBarTimer.getProgressDrawable().setColorFilter(
                            Color.parseColor("#4a90e2"), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            }

            if(data.toString()==AppConstants.CHANGE_CALLED_NUMBER_COLOR){
                //txtCalledNumber.setTextColor(Color.parseColor(bingoGameModel.getCalledNumberColor()));
                if(progressBarTimer.getProgress()-20>0){
                    progressBarTimer.setProgress(progressBarTimer.getProgress()-20);
                }
                else{
                    progressBarTimer.setProgress(0);
                }
            }

            if(data.toString() == AppConstants.BINGO_FOUND){
                btnSayBingo.setClickable(true);
                btnSayBingo.setBackgroundResource(R.mipmap.bingo_button);
            }

            if(data.toString() == AppConstants.SHOW_NOTIFICATION){
                LayoutInflater inflater = ((MainGameActivity)context).getLayoutInflater();
                View layout = inflater.inflate(R.layout.notifcation,
                        (ViewGroup) view.findViewById(R.id.ll_toast_item));

                ImageView image = (ImageView) layout.findViewById(R.id.imageViewPlayerImage);
                //image.setImageResource(R.drawable.android);
                TextView txtName = (TextView) layout.findViewById(R.id.txtName);
                TextView txtMessage = (TextView) layout.findViewById(R.id.txtMessage);
                //text.setText("Hello! This is a custom toast!");
                /*MySingleton.getInstance(context).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+bi.getPlayerID(),
                        ImageLoader.getImageListener(image,
                                R.drawable.user, R.drawable.user));
                                */
                txtMessage.setText(bingoGameModel.getNotificationText().toString());

                Toast toast = new Toast(((MainGameActivity)context).getApplicationContext());
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 300, 20);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }

            if(data.toString() == AppConstants.WINNER_FOUND){
                //end game screen
                AppConstants.IF_WINNER_FOUND = 1;
                System.out.println("From view "+AppConstants.IF_BINGO_FOUND);
                if(bingoGameModel.getWinner().equals(bingoGameModel.getMyPlayer().getPlayerID())){
                    //You win
                    llendgame.setVisibility(View.VISIBLE);
                    ((TextView)llendgame.findViewById(R.id.txt_endgame_winner)).setText("You Win");
                    ((TextView)llendgame.findViewById(R.id.txt_congrats_console)).setText("Well played. Congrasulations !!");
                    MySingleton.getInstance(context).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+bingoGameModel.getWinner(),
                            ImageLoader.getImageListener(((ImageView)llendgame.findViewById(R.id.imageViewPlayerImage)),
                                    R.drawable.user, R.drawable.user));
                    llendgame.startAnimation(AnimationUtils.loadAnimation(context,
                            R.anim.slid_down));


                }
                else{
                    String winnerName = bingoGameModel.getPlayerNamebyID(bingoGameModel.getWinner());
                    //Toast.makeText(context,"Winner is "+winnerName,Toast.LENGTH_LONG).show();
                    llendgame.setVisibility(View.VISIBLE);
                    ((TextView)llendgame.findViewById(R.id.txt_endgame_winner)).setText(winnerName+" Wins");
                    ((TextView)llendgame.findViewById(R.id.txt_congrats_console)).setText("Better luck next time.");
                    if(bingoGameModel.getWinner().equals("--Nobody")){
                        ((TextView)llendgame.findViewById(R.id.txt_endgame_winner)).setText(bingoGameModel.getWinner()+" Wins");
                        ((LinearLayout)llendgame.findViewById(R.id.llUserImage)).setVisibility(View.GONE);

                    }
                    MySingleton.getInstance(context).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+bingoGameModel.getWinner(),
                            ImageLoader.getImageListener(((ImageView)llendgame.findViewById(R.id.imageViewPlayerImage)),
                                    R.drawable.user, R.drawable.user));
                    llendgame.startAnimation(AnimationUtils.loadAnimation(context,
                            R.anim.slid_down));
                }
            }

            if(data.toString().equals(AppConstants.UPDATE_MAINGAME_MESSAGE_LIST)){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                View item_chatlist =  ((LayoutInflater)view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_lobby_message,null);
                item_chatlist.setLayoutParams(layoutParams);
                LinearLayout llChat = (LinearLayout)item_chatlist.findViewById(R.id.llChat);

                if(bingoGameModel.getMaingameMessageList().size()%2==0){
                    llChat.setGravity(Gravity.RIGHT);
                    ((LinearLayout)item_chatlist.findViewById(R.id.llMsgBackground)).setBackgroundResource(R.drawable.rect_backgroud_orange);
                }
                else{
                    llChat.setGravity(Gravity.LEFT);
                    ((LinearLayout)item_chatlist.findViewById(R.id.llMsgBackground)).setBackgroundResource(R.drawable.rect_background);
                }
                //llChat.setLayoutParams(layoutParams);
                //llChat.setG
                ((TextView)item_chatlist.findViewById(R.id.txtName)).setText("MyName");
                ((TextView)item_chatlist.findViewById(R.id.txtMessage)).setText(bingoGameModel.getMaingameMessageList().get(bingoGameModel.getMaingameMessageList().size()-1));
                llChatList.addView(item_chatlist);
                ScrollView ScrlChatList = ((ScrollView)this.view.findViewById(R.id.scrlChatList));


                ScrlChatList.fullScroll(View.FOCUS_DOWN);
            }
        }
    }
}
