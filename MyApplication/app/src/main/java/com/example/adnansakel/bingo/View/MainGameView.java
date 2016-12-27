package com.example.adnansakel.bingo.View;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adnansakel.bingo.Model.BingoGameModel;
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

    List sequencenumberList;

    public MainGameView(View view, BingoGameModel bingoGameModel){
        this.view = view;
        bingoGameModel.addObserver(this);
        this.bingoGameModel = bingoGameModel;
        initialize();
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
        //txt_one = (TextView)findViewById(R.id.txt_one);



        //sequencenumberList = ((MyApplication)getApplication()).getBingoGameModel().getShuffledNumberSequence();

        btnSayBingo.setVisibility(View.GONE);


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
            }

            if(data.toString()==AppConstants.CHANGE_CALLED_NUMBER_COLOR){
                txtCalledNumber.setTextColor(Color.parseColor(bingoGameModel.getCalledNumberColor()));
            }

            if(data.toString() == AppConstants.BINGO_FOUND){
                btnSayBingo.setVisibility(View.VISIBLE);
            }
        }
    }
}
