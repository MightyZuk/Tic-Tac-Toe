package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class gamePage extends AppCompatActivity {

    TextView turns;

    int player = 0;
    boolean gameActive = true;
    int[] blocks = {2, 2, 2,
                    2, 2, 2,
                    2, 2, 2};
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        turns = findViewById(R.id.turns);
    }

    @SuppressLint("SetTextI18n")
    public void click(View view) {
        ImageView img = (ImageView) view;
        int tap = Integer.parseInt(img.getTag().toString());
        //change image on tap
        if (blocks[tap] == 2 && gameActive) {
            gameActive = false;
            blocks[tap] = player;
            img.setTranslationY(-1000f);
            if (player == 0) {
                img.setImageResource(R.drawable.x);
                player = 1;
                turns.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.o);
                player = 0;
                turns.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Winning logic
        for (int[] win : winPos) {
            if (blocks[win[0]] == blocks[win[1]] && blocks[win[1]] == blocks[win[2]] && blocks[win[0]] != 2) {
                gameActive = false;
                if (blocks[win[0]] == 0)
                    Toast.makeText(this, "! Player 1 has won !", Toast.LENGTH_SHORT).show();
                if (blocks[win[0]] == 1)
                    Toast.makeText(this, "! Player 2 has won !", Toast.LENGTH_SHORT).show();
                turns.setText("");
                new Handler().postDelayed(() -> reset(view), 2000);
            }
        }

        // Logic for tie
        for (int i : blocks) {
            if (i == 2) {
                gameActive = true;
                break;
            }
        }
            if (!gameActive){
                if (    (blocks[0] != blocks[1] || blocks[1] != blocks[2]) && /*horizontal*/
                        (blocks[3] != blocks[4] || blocks[4] != blocks[5]) && /*horizontal*/
                        (blocks[6] != blocks[7] || blocks[7] != blocks[8]) && /*horizontal*/
                        (blocks[0] != blocks[3] || blocks[3] != blocks[6]) && /*vertical*/
                        (blocks[1] != blocks[4] || blocks[4] != blocks[7]) && /*vertical*/
                        (blocks[2] != blocks[5] || blocks[5] != blocks[8]) && /*vertical*/
                        (blocks[0] != blocks[4] || blocks[4] != blocks[8]) && /*diagonal*/
                        (blocks[2] != blocks[4] || blocks[4] != blocks[6])    /*diagonal*/              ){
                      Toast.makeText(this,"! IT's a TIE !",Toast.LENGTH_SHORT).show();
                    turns.setText("");
                    new Handler().postDelayed(() -> reset(view), 2000);
                }
            }
    }
    @SuppressLint("SetTextI18n")
    //reset method
    public void reset(View view) {
        gameActive = true;
        player = 0;
        turns.setText("X's turn");
        Arrays.fill(blocks, 2);
        ((ImageView) findViewById(R.id.first)).setImageResource(0);
        ((ImageView) findViewById(R.id.second)).setImageResource(0);
        ((ImageView) findViewById(R.id.third)).setImageResource(0);
        ((ImageView) findViewById(R.id.four)).setImageResource(0);
        ((ImageView) findViewById(R.id.five)).setImageResource(0);
        ((ImageView) findViewById(R.id.six)).setImageResource(0);
        ((ImageView) findViewById(R.id.seven)).setImageResource(0);
        ((ImageView) findViewById(R.id.eight)).setImageResource(0);
        ((ImageView) findViewById(R.id.nine)).setImageResource(0);
    }
}