package com.example.adnansakel.bingo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.adnansakel.bingo.Util.AppConstants;
import com.example.adnansakel.bingo.View.HomeView;
import com.example.adnansakel.bingo.View.MainGameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Adnan Sakel on 11/26/2016.
 */
public class MainGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //new MainGameView(findViewById(R.id.rl_home_view),((MyApplication)getApplication()).getBingoGameModel());

        setShuffledNumberSequenceonCard();

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

        ((MyApplication)getApplication()).getBingoGameModel().setShuffledNumberSequence(shuffledNumbersonCard);

    }
}
