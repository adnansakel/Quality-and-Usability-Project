package com.example.adnansakel.bingo.View;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adnansakel.bingo.Model.BingoGameModel;
import com.example.adnansakel.bingo.R;
import com.example.adnansakel.bingo.Util.AppConstants;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class HomeView implements Observer{

    View view;
    BingoGameModel bingoGameModel;
    TextView txtGameTitle;
    Button btnCreateGame;
    Button btnJoinGame;
    ImageView imgUser;
    Context context;

    public HomeView(View view, BingoGameModel bingoGameModel, Context context){
        this.view = view;
        bingoGameModel.addObserver(this);
        this.bingoGameModel = bingoGameModel;
        this.context = context;
        initialize();
    }

    private void initialize(){
       // txtGameTitle = (TextView)view.findViewById(R.id.txt_game_title);
        btnCreateGame = (Button)view.findViewById(R.id.btn_create_game);
        btnJoinGame = (Button)view.findViewById(R.id.btn_join_game);
        imgUser = (ImageView)view.findViewById(R.id.imageUser);
    }

    @Override
    public void update(Observable observable, Object o) {

        if(observable instanceof BingoGameModel){
            //System.out.println("Photo Updated notified");
            //Toast.makeText(context,"Photo Updated notified",Toast.LENGTH_SHORT).show();
            if(o.toString().equals(AppConstants.UPDATE_PROFILE_PHOTO)){
              //  Toast.makeText(context,"Photo Updated",Toast.LENGTH_SHORT).show();
                imgUser.setImageBitmap(bingoGameModel.getMyPlayer().getBmpProfilePhoto());
            }
        }
    }
}
