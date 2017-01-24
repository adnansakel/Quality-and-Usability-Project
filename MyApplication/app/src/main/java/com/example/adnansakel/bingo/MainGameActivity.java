package com.example.adnansakel.bingo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adnansakel.bingo.HttpHelper.BingoServerClient;
import com.example.adnansakel.bingo.Model.Game;
import com.example.adnansakel.bingo.Model.Player;
import com.example.adnansakel.bingo.Util.AppConstants;
import com.example.adnansakel.bingo.View.HomeView;
import com.example.adnansakel.bingo.View.MainGameView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class MainGameActivity extends AppCompatActivity implements View.OnClickListener{

    Handler handler;
    boolean mStopHandler = false;
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

    TextToSpeech textToSpeechCallNumber;

    List<Integer> sequencenumberList;

    BingoServerCalls bingoServerCalls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_main_game);
        getSupportActionBar().setTitle("Game Bingo");
        sequencenumberList = new ArrayList<Integer>();
        new MainGameView(findViewById(R.id.rl_main_game_view),((MyApplication)getApplication()).getBingoGameModel());
        handler = new Handler();

        //((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setPlayerID("101");
        setShuffledNumberSequenceonCard();
        setShuffledCalledNumberSequence();
        //new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this).getShuffledCalledNumberSequence();
        //setShuffledNumberSequenceonCard();
        ((MyApplication)getApplication()).getBingoGameModel().initializeBingoPatternSsearchGrid();

        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);

        textToSpeechCallNumber = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeechCallNumber.setLanguage(Locale.UK);
                }
            }
        });

        initialize();
        counter = 0;
        callNumbersinInterval(1500);
        //((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().setPlayerID("2017");
        //((MyApplication)getApplication()).getBingoGameModel().getMyGame().setGameID("555");

        /*
        try {
            bingoServerCalls.selectGame(new Player(),new Game());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        /*
        BingoServerCalls bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        try {
            bingoServerCalls.registerUser(new Player());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

    }



    private void initialize(){
        txt_one = (TextView)findViewById(R.id.txt_1);
        txt_two = (TextView)findViewById(R.id.txt_2);
        txt_three = (TextView)findViewById(R.id.txt_3);
        txt_four = (TextView)findViewById(R.id.txt_4);
        txt_five = (TextView)findViewById(R.id.txt_5);
        txt_six = (TextView)findViewById(R.id.txt_6);
        txt_seven = (TextView)findViewById(R.id.txt_7);
        txt_eight = (TextView)findViewById(R.id.txt_8);
        txt_nine = (TextView)findViewById(R.id.txt_9);
        txt_ten = (TextView)findViewById(R.id.txt_10);
        txt_eleven = (TextView)findViewById(R.id.txt_11);
        txt_twelve = (TextView)findViewById(R.id.txt_12);
        txt_thirteen = (TextView)findViewById(R.id.txt_13);
        txt_fourteen = (TextView)findViewById(R.id.txt_14);
        txt_fifteen = (TextView)findViewById(R.id.txt_15);
        txt_sixteen = (TextView)findViewById(R.id.txt_16);
        txt_seventeen = (TextView)findViewById(R.id.txt_17);
        txt_eighteen = (TextView)findViewById(R.id.txt_18);
        txt_nineteen = (TextView)findViewById(R.id.txt_19);
        txt_twenty = (TextView)findViewById(R.id.txt_20);
        txt_twentione = (TextView)findViewById(R.id.txt_21);
        txt_twentitwo = (TextView)findViewById(R.id.txt_22);
        txt_twentithree = (TextView)findViewById(R.id.txt_23);
        txt_twentifour = (TextView)findViewById(R.id.txt_24);
        txt_twentifive = (TextView)findViewById(R.id.txt_25);
        txtCalledNumber = (TextView)findViewById(R.id.txtCalledNumber);
        btnSayBingo = (Button)findViewById(R.id.btn_say_bingo);
        //txt_one = (TextView)findViewById(R.id.txt_one);

        txt_one.setOnClickListener(this);
        txt_two.setOnClickListener(this);
        txt_three.setOnClickListener(this);
        txt_four.setOnClickListener(this);
        txt_five.setOnClickListener(this);
        txt_six.setOnClickListener(this);
        txt_seven.setOnClickListener(this);
        txt_eight.setOnClickListener(this);
        txt_nine.setOnClickListener(this);
        txt_ten.setOnClickListener(this);
        txt_eleven.setOnClickListener(this);
        txt_twelve.setOnClickListener(this);
        //txt_thirteen.setOnClickListener(this);
        txt_fourteen.setOnClickListener(this);
        txt_fifteen.setOnClickListener(this);
        txt_sixteen.setOnClickListener(this);
        txt_seventeen.setOnClickListener(this);
        txt_eighteen.setOnClickListener(this);
        txt_nineteen.setOnClickListener(this);
        txt_twenty.setOnClickListener(this);
        txt_twentione.setOnClickListener(this);
        txt_twentitwo.setOnClickListener(this);
        txt_twentithree.setOnClickListener(this);
        txt_twentifour.setOnClickListener(this);
        txt_twentifive.setOnClickListener(this);
        btnSayBingo.setOnClickListener(this);

        /*
        sequencenumberList = ((MyApplication)getApplication()).getBingoGameModel().getShuffledNumberSequence();
        System.out.println("Total numbers:"+sequencenumberList.size());
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

        btnSayBingo.setVisibility(View.GONE);
*/

    }

    public void setShuffledCalledNumberSequence(){
        /*
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
        //((MyApplication)getApplication()).getBingoGameModel().setShuffledCalledNumberSequence(numberSequence);

        ((MyApplication)getApplication()).getBingoGameModel().getMyGame().setCallingNumberlist(numberSequence);//this is here for testing
        //the above block of code should be removed as the number sequence will be got from server
        */
        ((MyApplication)getApplication()).getBingoGameModel().setShuffledCalledNumberSequence(
                ((MyApplication)getApplication()).getBingoGameModel().getMyGame().getCallingNumberlist());
        //MyGame should contain the calling number sequence got from server while in join game or create game;
        //this string should be got from server

        //return shuffledNumberSequence;
        //return "";
    }

    private void setShuffledNumberSequenceonCard(){

        List<Integer> shuffledNumbersonCard = new ArrayList<Integer>();
        List<Integer> shuffledNumbers = new ArrayList<Integer>();

        for(int j = 0; j < AppConstants.BINGO_GRID_SIZE; j++) {

            int i = j*AppConstants.RANGE_OF_NUMBERS_IN_A_COLLUM+1;
            shuffledNumbers.clear();
            for( ; i <= (j+1)*AppConstants.RANGE_OF_NUMBERS_IN_A_COLLUM ; i++){
                //System.out.println("He He:"+i);
                shuffledNumbers.add(i);
            }

            Collections.shuffle(shuffledNumbers);//shuffling numbers from 1 to 15

            for(int k = 0; k <5; k++){
                //System.out.println(shuffledNumbers.get(k));
                shuffledNumbersonCard.add(shuffledNumbers.get(k));//getting first five items of shuffled list of numbers from 1 to 15;
            }

        }
            System.out.println("Here ??");
        ((MyApplication)getApplication()).getBingoGameModel().setShuffledNumberSequence(shuffledNumbersonCard);

    }

    private boolean IsBingo(){
        int [][] bingo_grid = ((MyApplication)getApplication()).getBingoGameModel().getBingoPatternSearchGrid();

        //check for rows
        int row_sum;
        int col_sum;

        for(int i = 0; i < AppConstants.BINGO_GRID_SIZE; i++){
            row_sum = 0;
            col_sum = 0;
            for(int j = 0; j < AppConstants.BINGO_GRID_SIZE; j++){
                row_sum += bingo_grid[i][j];
                col_sum +=bingo_grid[j][i];
            }

            if (row_sum == AppConstants.BINGO_GRID_SIZE || col_sum == AppConstants.BINGO_GRID_SIZE){
                return true;
            }
        }



        return false;
    }

    int counter = 0;
    Runnable runnable;
    private void callNumbersinInterval(final int milliseconds){

        runnable = new Runnable() {

            @Override
            public void run() {
                // do your stuff - don't create a new runnable here!
                if(AppConstants.IF_BINGO_FOUND == 1 || counter > 5*65){
                    handler.removeCallbacks(this);
                    //llBlur.setVisibility(View.GONE);
                    //progress.dismiss();
                    mStopHandler = true;

                    if(counter>5*65){
                        Toast.makeText(MainGameActivity.this,"Game over without a Bingo !!",Toast.LENGTH_LONG).show();
                    }

                    return;
                }
                if(counter % 5 == 0){
                    if(counter/5 < 65){
                        System.out.println("Indexes: " + (counter/5));
                        int index = counter/5;
                        int m = ((MyApplication)getApplication()).getBingoGameModel().getShuffledCalledNumberSequence().get(index);
                        //txtCalledNumber.setText(""+m);
                        ((MyApplication)getApplication()).getBingoGameModel().setCalledNumber(m);
                        txtCalledNumber.setTextColor(Color.parseColor("#008000"));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            textToSpeechCallNumber.speak(""+m,TextToSpeech.QUEUE_FLUSH,null,""+m);
                        }
                        else{
                            textToSpeechCallNumber.speak("22",TextToSpeech.QUEUE_FLUSH,null);
                        }
                        try {
                            bingoServerCalls.postLongestMatch(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer(),
                                    ((MyApplication)getApplication()).getBingoGameModel().getmyLongestMatch());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(counter % 5 == 1){
                    ((MyApplication)getApplication()).getBingoGameModel().setCalledNumberColor("#00FF00");
                    //txtCalledNumber.setTextColor(Color.parseColor("#00FF00"));
                }
                if(counter % 5 == 2){
                    ((MyApplication)getApplication()).getBingoGameModel().setCalledNumberColor("#FFFF00");
                    //txtCalledNumber.setTextColor(Color.parseColor("#FFFF00"));
                }
                if(counter % 5 == 3){
                    ((MyApplication)getApplication()).getBingoGameModel().setCalledNumberColor("#FFA500");
                    //txtCalledNumber.setTextColor(Color.parseColor("#FFA500"));
                }
                if(counter % 5 == 4){
                    ((MyApplication)getApplication()).getBingoGameModel().setCalledNumberColor("#FF0000");
                    //txtCalledNumber.setTextColor(Color.parseColor("#FF0000"));
                }
                if (!mStopHandler) {
                    counter++;
                    //System.out.println(counter);
                    handler.postDelayed(this, milliseconds);

                }
            }
        };

// start it with:
        handler.post(runnable);

    }


    @Override
    public void onClick(View view) {
        if(view == btnSayBingo){
            try {
                bingoServerCalls.sayBingo(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer());
                System.out.println("Say Bingo !!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            if(((TextView)view).getText()!="*"
                    //&& ((TextView)view).getText()==txtCalledNumber.getText().toString()
                    ){
                int id = view.getId();
                String id_name = getResources().getResourceName(id);
                System.out.println("Clicked:" + id_name.substring(36));
                ((MyApplication)getApplication()).getBingoGameModel()
                        .updateBingoPatternSearcgGrid(Integer.valueOf(id_name.substring(36).toString()),1);
                ((TextView)view).setText("*");
                /*
                try {
                    bingoServerCalls.postLongestMatch(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer(),
                            ((MyApplication)getApplication()).getBingoGameModel().getmyLongestMatch());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                if(IsBingo()){
                    //btnSayBingo.setVisibility(View.VISIBLE);
                    ((MyApplication)getApplication()).getBingoGameModel().setIfBingoIsFound(true);
                    AppConstants.IF_BINGO_FOUND = 1;
                }
            }
        }
    }

    @Override
    public void onBackPressed(){
        //Toast.makeText(this,"This activity will exit",Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure want to exit this game?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(handler!=null && runnable!=null){
                    handler.removeCallbacks(runnable);
                }
                MainGameActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", null);
        builder.setCancelable(true);
        builder.show();
    }
}
