package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.gridlayout.widget.GridLayout;


import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0 is rr, 1 is yy
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningP = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6},};
    int activePlayer = 0;
    boolean gameActive = true;


    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.getTag();
        Log.i("Values", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState [tappedCounter] ==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.rr);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yy);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(360).setDuration(500);
            for (int[] winningPosition : winningP) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //someone won
                    gameActive= false;
                    String winner;
                    if (activePlayer == 1) {
                        winner = "PEKORA!";
                        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.ratt);
                        mPlayer.start();
                    } else {
                        winner = "KORONE!";
                    }
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void PlayAgain(View view) {
        ImageView image = (ImageView)  findViewById(R.id.imgLogo);
        image.setImageResource(R.drawable.logo);
        image.animate().rotation(360).setDuration(500);

        Toast.makeText(this, "New Game!", Toast.LENGTH_LONG).show();
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gameGrid);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

            Arrays.fill(gameState, 2);
            //for (int i=0; i<gameState.length; i++){ gameState[i]=2;  }
            activePlayer = 0;
            gameActive = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}