package com.example.adnansakel.bingo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.adnansakel.bingo.HttpHelper.MySingleton;
import com.example.adnansakel.bingo.Util.AppConstants;
import com.example.adnansakel.bingo.Util.ConnectionCheck;
import com.example.adnansakel.bingo.View.HomeView;

import org.json.JSONException;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreateGame;
    Button btnJoinGame;
    Button btnHelp;
    BingoServerCalls bingoServerCalls;
    TextToSpeech textToSpeechtest;
    Handler handler;
    ProgressBar progressBarTest;
    ConnectionCheck connectionCheck;
    View llAnimationTest;
    ImageView imgGender;
    TextView txtNameAge;
    String nameage = "";
    ImageView imgUserPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Bingo Home");
        new HomeView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());
        //System.out.println("Player at home: "+((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getPlayerID());
        bingoServerCalls = new BingoServerCalls(((MyApplication)getApplication()).getBingoGameModel(),this);
        connectionCheck = new ConnectionCheck(this);
        initialize();
        //handler = new Handler();
        //handlerTest();
        /*
        textToSpeechtest = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeechtest.setLanguage(Locale.UK);
                }
            }
        });*/
    }


    /* // for testing
    int counter = 0;
    Runnable runnable;
    private void handlerTest(){
       runnable = new Runnable() {

            @Override
            public void run() {
                // do your stuff - don't create a new runnable here!

                //System.out.println(counter);

                if(counter > 4){
                    handler.removeCallbacks(this);
                    progressBarTest.setProgress(100-(counter*2*10));
                   // Toast.makeText(HomeActivity.this,"After callback removed "+counter,Toast.LENGTH_LONG).show();
                   // HomeActivity.this.finish();
                    return;
                }
                progressBarTest.setProgress(100-(counter*2*10));
                //System.out.println("Handler running "+ counter);


                handler.postDelayed(this, 1000);
                counter++;


            }
        };

// start it with:
        handler.post(runnable);
    }

    */


    private void initialize(){
        btnCreateGame = (Button)findViewById(R.id.btn_create_game);
        btnJoinGame = (Button)findViewById(R.id.btn_join_game);
        imgGender = (ImageView)findViewById(R.id.imgGender);
        imgUserPhoto = (ImageView)findViewById(R.id.imageUser);
        txtNameAge = (TextView)findViewById(R.id.txtNameAge);

        btnHelp = (Button)findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(this);

        btnCreateGame.setOnClickListener(this);
        btnJoinGame.setOnClickListener(this);

        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getName()!=null){
            nameage = ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getName();
            txtNameAge.setText(nameage);
        }

        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getAge()!=null){
            nameage = nameage+","+((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getAge();
            txtNameAge.setText(nameage);
        }

        if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getGender().toString()!=null){
            System.out.println("Male or Female: "+((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getGender().toString());
            if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getGender().toString().equals(AppConstants.MALE)){
                imgGender.setImageResource(R.mipmap.male_mark);
                System.out.println("Male");
            }
            if(((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getGender().toString().equals(AppConstants.FEMALE)){
                imgGender.setImageResource(R.mipmap.female_mark);
            }
            else{
                imgGender.setVisibility(View.GONE);
            }
        }
        else{
            imgGender.setVisibility(View.GONE);
        }

        MySingleton.getInstance(this).getImageLoader().get(AppConstants.BASE_URL+AppConstants.PLAYER_PHOTO_URL+"/"+
                        ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer().getPlayerID(),
                ImageLoader.getImageListener((ImageView)findViewById(R.id.imageUser),
                        R.drawable.user, R.drawable.user));

        /* //testing
        llAnimationTest = findViewById(R.id.llAnimationTest);
        llAnimationTest.setVisibility(View.INVISIBLE);
        llAnimationTest.setTranslationX(llAnimationTest.getWidth());
        llAnimationTest.setVisibility(View.VISIBLE);
        llAnimationTest.setTranslationX(llAnimationTest.getWidth());
        llAnimationTest.setVisibility(View.INVISIBLE);


        //an.MakeInVisibleWithSlideRight();
        //an.MakeVisibleWithSlideLeft();
        progressBarTest = (ProgressBar)findViewById(R.id.progressBarTest);
        progressBarTest.setMax(100);
        progressBarTest.setProgress(100);
        progressBarTest.getProgressDrawable().setColorFilter(
                Color.parseColor("#ff0000"), android.graphics.PorterDuff.Mode.SRC_IN);


        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.notifcation,
                (ViewGroup) findViewById(R.id.ll_toast_item));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        //image.setImageResource(R.drawable.android);
        //TextView text = (TextView) layout.findViewById(R.id.text);
        //text.setText("Hello! This is a custom toast!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP|Gravity.RIGHT, 20, 150);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
*/



    }


    @Override
    public void onClick(View view) {
        if(view == btnCreateGame){
            //handler.removeCallbacks(runnable);
            //startActivity(new Intent(HomeActivity.this,CreateGameActivity.class));
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeechtest.speak("22",TextToSpeech.QUEUE_FLUSH,null,"22");
            }
            else{
                textToSpeechtest.speak("22",TextToSpeech.QUEUE_FLUSH,null);
            }*/
            if(!connectionCheck.isConnected()){return;}

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View mview = View.inflate(this,R.layout.creategame_dialog_view,null);
            builder.setView(mview);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(((EditText)mview.findViewById(R.id.editTextGameName)).getText().toString().length()>0){
                        ((MyApplication)getApplication()).getBingoGameModel().getMyGame().setGameName(
                                ((EditText)mview.findViewById(R.id.editTextGameName)).getText().toString());
                        try {
                            bingoServerCalls.createGame(((MyApplication)getApplication()).getBingoGameModel().getMyGame(),
                                    ((MyApplication)getApplication()).getBingoGameModel().getMyPlayer());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(HomeActivity.this,"You need to give a game name",Toast.LENGTH_LONG).show();
                    }
                }
            });
           // builder.setNegativeButton("No", null);
            builder.setCancelable(true);
            builder.show();



        }
        else if(view == btnJoinGame){
            startActivity(new Intent(HomeActivity.this,JoinGameActivity.class));
        }
        else if(view == btnHelp){
            startActivity(new Intent(HomeActivity.this,HelpActivity.class));
        }
    }

}
