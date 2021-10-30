package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; //yellow = 0 = O, red = 1 = X, not played = 2
    int[] state = {2,2,2,2,2,2,2,2,2};
    String[] players = {"O", "X"};
    String winner = players[1];
    String loser = players[0];
    boolean gameActive = true;
    int[][] winCase = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int score_O = 0;
    int score_X = 0;

    public void coins(View view){
        ImageView coin = (ImageView) view;
        TextView message = findViewById(R.id.display);
        TextView score = findViewById(R.id.scoreboard);
        Button click = findViewById(R.id.resetsc);
        Button change = findViewById(R.id.Button);

        int tapCoin = Integer.parseInt(coin.getTag().toString());

        if (state[tapCoin] == 2 && gameActive) {
            state[tapCoin] = activePlayer;
            if (activePlayer == 0) {
                if (players[0].equals("O")) {
                    coin.setImageResource(R.drawable.yellow);
                }else{
                    coin.setImageResource(R.drawable.red);
                }
                message.setText(players[1]+"'s turn");
                activePlayer = 1;

            } else {
                if (players[1].equals("O")) {
                    coin.setImageResource(R.drawable.yellow);
                }else{
                    coin.setImageResource(R.drawable.red);
                }
                message.setText(players[0]+"'s turn");
                activePlayer = 0;
            }
            coin.animate().alpha(1f).setDuration(500);

            for (int[] win : winCase) {
                if ((state[win[0]] == state[win[1]]) && (state[win[1]] == state[win[2]])
                        && (state[win[0]] != 2)) {
                    gameActive = false;
                    if (state[win[0]] == 0) {
                        message.setText(players[0]+" won");
                        winner = players[0];
                        loser = players[1];
                    } else {
                        message.setText(players[1]+" won");
                        winner = players[1];
                        loser = players[0];
                    }
                    if (winner == "X"){
                        score_X++;
                    }else{
                        score_O++;
                    }
                    change.setText("Play Again");
                    score.setText(score_O + " - " + score_X);
                    if (!score.getText().toString().equals("0 - 0")){
                        click.setEnabled(true);
                    }
                }
                else{
                    boolean gameOver = true;
                    for (int coinState: state){
                        if (coinState == 2) {
                            gameOver = false;
                            break;
                        }
                    }
                    if (gameOver) {
                        message.setText("It's a draw");
                        winner = players[0];
                        loser = players[1];
                        change.setText("Play Again");
                    }
                }
            }
        }
    }

    public void reset(View view){
        TextView message = findViewById(R.id.display);
        TextView score = findViewById(R.id.scoreboard);
        Button click = findViewById(R.id.resetsc);
        Button change = findViewById(R.id.Button);
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(state, 2);
        players[0] = loser;
        players[1] = winner;
        score.setText(score_O + " - " + score_X);
        change.setText("Reset");
        click.setEnabled(!score.getText().toString().equals("0 - 0"));
        GridLayout gridLayout = findViewById(R.id.grid);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        message.setText(loser + " starts");

    }

    public void resetscore(View view){
        TextView message = findViewById(R.id.display);
        TextView score = findViewById(R.id.scoreboard);
        Button click = findViewById(R.id.resetsc);
        Button change = findViewById(R.id.Button);
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(state, 2);
        players[0] = loser;
        players[1] = winner;
        score_O = 0;
        score_X = 0;
        score.setText(score_O + " - " + score_X);
        click.setEnabled(false);
        change.setText("Reset");
        GridLayout gridLayout = findViewById(R.id.grid);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        message.setText(loser + " starts");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView message = findViewById(R.id.display);
        TextView score = findViewById(R.id.scoreboard);
        Button click = findViewById(R.id.resetsc);
        message.setText("O starts");
        score.setText(score_O + " - " + score_X);
        click.setEnabled(false);
    }
}