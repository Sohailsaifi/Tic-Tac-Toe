package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean GameActive = true;
    boolean flag = false;

    //Player State :
    // 0 - x
    // 1 - 0
    int activePlayer = 0;
    int GameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int WinPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

//       State Meaning :
//        0 - x
//        1 - 0
//        2 - Null

    public void PlayerTap(View view) {

        ImageView img = (ImageView) view;
        int Tappedimage = Integer.parseInt(img.getTag().toString());

        if (!GameActive) {
            GameReset(view);
        }

        if (GameState[Tappedimage] == 2) {

            GameState[Tappedimage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText(" O's Turn - Tap to play");

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(" X's Turn - Tap to play");

            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        // check if any player won:
        for (int[] winposition : WinPositions) {

            if (GameState[winposition[0]] == GameState[winposition[1]] &&
                    GameState[winposition[1]] == GameState[winposition[2]] &&
                    GameState[winposition[0]] != 2) {

                String winnerstr;
                GameActive = false;
                if (GameState[winposition[0]] == 0) {
                    winnerstr = "X has won.";
                } else {
                    winnerstr = "O has won.";
                }

                TextView status = findViewById(R.id.status);
                status.setText(winnerstr);
            }
        }
    }


    private void GameReset(View view) {
        GameActive = true;
        activePlayer = 0;

        for(int i= 0; i< GameState.length; i++) {
            GameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText(" X's Turn - Tap to play");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
