package com.veersingh.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView xWins;
    private TextView oWins;
    private TextView draws;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private TextView currPlayer;
    private Button restartGame;
    public static int wins_x_num = 0;
    public static int wins_o_num = 0;
    public static int draws_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        establishViews();
        gameButtonListener(b1);
        gameButtonListener(b2);
        gameButtonListener(b3);
        gameButtonListener(b4);
        gameButtonListener(b5);
        gameButtonListener(b6);
        gameButtonListener(b7);
        gameButtonListener(b8);
        gameButtonListener(b9);
        restartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtons();
                currPlayer.setText("PLAYER TURN: X");
                xWins.setText("WINS (X): 0");
                oWins.setText("WINS (O): 0");
                draws.setText("DRAWS: 0");
                MainActivity.wins_x_num = 0;
                MainActivity.wins_o_num = 0;
                MainActivity.draws_num = 0;
            }
        });
    }

    private void establishViews(){
        xWins = findViewById(R.id.text_view_xwins);
        oWins = findViewById(R.id.text_view_owins);
        draws = findViewById(R.id.text_view_draws);
        b1 = findViewById(R.id.button_1);
        b2 = findViewById(R.id.button_2);
        b3 = findViewById(R.id.button_3);
        b4 = findViewById(R.id.button_4);
        b5 = findViewById(R.id.button_5);
        b6 = findViewById(R.id.button_6);
        b7 = findViewById(R.id.button_7);
        b8 = findViewById(R.id.button_8);
        b9 = findViewById(R.id.button_9);
        currPlayer = findViewById(R.id.text_view_currplayer);
        restartGame = findViewById(R.id.button_restart_game);
    }
    private void gameButtonListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currPlayer.getText().toString().equals("PLAYER TURN: X")) {
                    if(button.getText().toString().equals(" ")){
                        button.setText("X");
                        currPlayer.setText("PLAYER TURN: O");
                    } else {
                        Toast.makeText(MainActivity.this, "Cannot use this block", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(button.getText().toString().equals(" ")){
                        button.setText("O");
                        currPlayer.setText("PLAYER TURN: X");
                    } else {
                        Toast.makeText(MainActivity.this, "Cannot use this block", Toast.LENGTH_SHORT).show();
                    }
                }
                if(checkWin() == true){
                    if(currPlayer.getText().toString().equals("PLAYER TURN: X")){
                        Toast.makeText(MainActivity.this, "PLAYER O HAS WON!", Toast.LENGTH_LONG).show();
                        try {
                            Thread.sleep(2 * 1000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                        oWins.setText("WINS (O): " + (MainActivity.wins_o_num + 1));
                        resetButtons();
                    } else {
                        Toast.makeText(MainActivity.this, "PLAYER X HAS WON!", Toast.LENGTH_LONG).show();
                        try {
                            Thread.sleep(2 * 1000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                        xWins.setText("WINS (O): " + (MainActivity.wins_x_num + 1));
                        resetButtons();
                        currPlayer.setText("PLAYER TURN: X");
                    }
                }
                Button[] blist = {b1,b2,b3,b4,b5,b6,b7,b8,b9};
                if(checkDraw(blist) == true){
                    Toast.makeText(MainActivity.this, "ITS A DRAW!", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    draws.setText("DRAWS: " + (MainActivity.draws_num)+1);
                    resetButtons();
                    currPlayer.setText("PLAYER TURN: X");
                }
            }
        });
    }

    private boolean checkWin(){
        return isSimilar(b1,b2,b3) || isSimilar(b4,b5,b6) || isSimilar(b7,b8,b9) || isSimilar(b1,b4,b7) || isSimilar(b2,b5,b8) || isSimilar(b3,b6,b9) || isSimilar(b1,b5,b9) || isSimilar(b3,b5,b7);
    }

    private boolean checkDraw(Button[] blist){
        boolean isDraw = true;
        for(int i = 0; i < 9; i++){
            if(blist[i].getText().toString().equals(" ")){
                isDraw = false;
                break;
            }
        }
        return isDraw;
    }
    private void resetButtons(){
        b1.setText(" ");
        b2.setText(" ");
        b3.setText(" ");
        b4.setText(" ");
        b5.setText(" ");
        b6.setText(" ");
        b7.setText(" ");
        b8.setText(" ");
        b9.setText(" ");
    }

    private boolean isSimilar(Button x, Button y, Button z){
        return (x.getText().toString().equals(y.getText().toString()) && y.getText().toString().equals(z.getText().toString())) && !(x.getText().toString().equals(" "));
    }

}