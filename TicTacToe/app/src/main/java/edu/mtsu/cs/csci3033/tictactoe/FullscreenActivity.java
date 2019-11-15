package edu.mtsu.cs.csci3033.tictactoe;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener {

    //set integers to store our player scores
    int player_one = 0;
    int player_two = 0;
    //declare all of our box buttons
    Button box00;
    Button box01;
    Button box02;
    Button box10;
    Button box11;
    Button box12;
    Button box20;
    Button box21;
    Button box22;
    Button reset_score;
    Button reset_board;

    //declare our different textViews
    TextView player_one_name;
    TextView player_one_score;
    TextView player_two_name;
    TextView player_two_score;

    //create an integer array that will track each marked box
    int[][] gameTable = new int[3][3];

    //we will track who's turn it is using player1 as a boolean value
    //if it's player1's turn, bool is true, board is marked as X
    //if it's player 2's turn, bool is false, board is marked as y
    boolean player_one_turn = true;

    //integer value will track turns, if we've played 9 turns and no winner
    //then game is a draw and we will give no points to either player
    int turns;


    //set a MediaPlayer object variable our Game has been won notification
    private MediaPlayer winner_found;

    //animation to shake a button if pushed when it wasn't needed
    private Animation button_shake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gamescreen);

        //create our sound instance
        winner_found = MediaPlayer.create(this, R.raw.cheering);

        //create our button_shake instance
        button_shake = AnimationUtils.loadAnimation(this, R.anim.shakey_button);

        //set all of our buttons to their respective view id's
        box00 = findViewById(R.id.box00);
        box01 = findViewById(R.id.box01);
        box02 = findViewById(R.id.box02);
        box10 = findViewById(R.id.box10);
        box11 = findViewById(R.id.box11);
        box12 = findViewById(R.id.box12);
        box20 = findViewById(R.id.box20);
        box21 = findViewById(R.id.box21);
        box22 = findViewById(R.id.box22);
        reset_score = findViewById(R.id.reset_score);
        reset_board = findViewById(R.id.reset_board);

        //set our text views to respective id's
        player_one_name = findViewById(R.id.player_one_name);
        player_one_score = findViewById(R.id.player_one_score);
        player_two_name = findViewById(R.id.player_two_name);
        player_two_score = findViewById(R.id.player_two_score);

        //get our game table set up as an empty table
        setGameTable();

        //set click listeners on all of our buttons
        box00.setOnClickListener(this);
        box01.setOnClickListener(this);
        box02.setOnClickListener(this);
        box10.setOnClickListener(this);
        box11.setOnClickListener(this);
        box12.setOnClickListener(this);
        box20.setOnClickListener(this);
        box21.setOnClickListener(this);
        box22.setOnClickListener(this);
        reset_score.setOnClickListener(this);
        reset_board.setOnClickListener(this);


        //set our player scores to 0
        player_one_score.setText(String.valueOf(player_one));
        player_two_score.setText(String.valueOf(player_two));

        player_one_name.setTextColor(this.getResources().getColor(R.color.my_turn));
    }

        @Override
        public void onClick(View view){

            //first we will highlight the player's name who's turn it is
            //the player's who turn it is gets highlighted yellow
            //not your turn, text goes to white
            if (!player_one_turn) {
                player_one_name.setTextColor(this.getResources().getColor(R.color.my_turn));
                player_two_name.setTextColor(this.getResources().getColor(R.color.not_my_turn));

            }

            else {
                player_one_name.setTextColor(this.getResources().getColor(R.color.not_my_turn));
                player_two_name.setTextColor(this.getResources().getColor(R.color.my_turn));
            }

            //switch will be used to check which button was pushed
            //evaluate if it was player1 or player2 that pushed button and update box text
            //if reset score button is pushed, we will call our reset board function
            switch(view.getId())
            {
                case R.id.box00:
                {
                    if (player_one_turn)
                    {
                        box00.setText("X");
                        gameTable[0][0] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box00.setText("O");
                        gameTable[0][0] = -1;
                    }
                    //disable the box using setEnabled
                    box00.setEnabled(false);
                    turns +=1;
                    break;
                }
                case R.id.box01:
                {
                    if (player_one_turn)
                    {
                        box01.setText("X");
                        gameTable[0][1] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box01.setText("O");
                        gameTable[0][1] = -1;
                    }
                    //disable the box using setEnabled
                    box01.setEnabled(false);
                    turns +=1;
                    break;

                }
                case R.id.box02:
                {
                    if (player_one_turn)
                    {
                        box02.setText("X");
                        gameTable[0][2] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box02.setText("O");
                        gameTable[0][2] = -1;
                    }
                    //disable the box using setEnabled
                    box02.setEnabled(false);
                    turns +=1;
                    break;

                }
                case R.id.box10:
                {
                    if (player_one_turn)
                    {
                        box10.setText("X");
                        gameTable[1][0] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box10.setText("O");
                        gameTable[1][0] = -1;
                    }
                    //disable the box using setEnabled
                    box10.setEnabled(false);
                    turns +=1;
                    break;

                }
                case R.id.box11:
                {
                    if (player_one_turn)
                    {
                        box11.setText("X");
                        gameTable[1][1] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box11.setText("O");
                        gameTable[1][1] = -1;
                    }
                    //disable the box using setEnabled
                    box11.setEnabled(false);
                    turns +=1;
                    break;

                }
                case R.id.box12:
                {
                    if (player_one_turn)
                    {
                        box12.setText("X");
                        gameTable[1][2] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box12.setText("O");
                        gameTable[1][2] = -1;
                    }
                    //disable the box using setEnabled
                    box12.setEnabled(false);
                    turns +=1;
                    break;
                }
                case R.id.box20:
                {
                    if (player_one_turn)
                    {
                        box20.setText("X");
                        gameTable[2][0] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box20.setText("O");
                        gameTable[2][0] = -1;
                    }
                    //disable the box using setEnabled
                    box20.setEnabled(false);
                    turns +=1;
                    break;

                }
                case R.id.box21:
                {
                    if (player_one_turn)
                    {
                        box21.setText("X");
                        gameTable[2][1] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box21.setText("O");
                        gameTable[2][1] = -1;
                    }
                    //disable the box using setEnabled
                    box21.setEnabled(false);
                    turns +=1;
                    break;
                }
                case R.id.box22:
                {
                    if (player_one_turn)
                    {
                        box22.setText("X");
                        gameTable[2][2] = 1;
                    }
                    else if (!player_one_turn)
                    {
                        box22.setText("O");
                        gameTable[2][2] = -1;
                    }
                    //disable the box using setEnabled
                    box22.setEnabled(false);
                    turns +=1;
                    break;
                }
                case R.id.reset_board:
                {
                    boolean emptyBoard = true;
                    for (int i = 0; i < 3; i ++) {
                        for (int j = 0; j < 3; j++)
                        {
                            if (gameTable[i][j] != 0)
                                emptyBoard = false;
                        }
                    }
                    if (emptyBoard)
                    {
                        reset_board.startAnimation(button_shake);
                        Toast no_board = Toast.makeText(getApplicationContext(),
                                "Board Already Empty!!",
                                Toast.LENGTH_SHORT);

                        no_board.show();
                        break;
                    }
                    else {
                        setGameTable();
                        turns = 0;
                        break;
                    }
                }
                case R.id.reset_score: {
                    if (player_one == 0 || player_two == 0)
                    {
                        reset_score.startAnimation(button_shake);
                        Toast no_score = Toast.makeText(getApplicationContext(),
                                "Score Already at 0",
                                Toast.LENGTH_SHORT);

                        no_score.show();
                        break;
                    }
                    else {
                        setGameTable();
                        turns = 0;
                        player_one = 0;
                        player_two = 0;
                        player_one_score.setText(String.valueOf(player_one));
                        player_two_score.setText(String.valueOf(player_two));
                        break;
                    }
                }
            }

            //check out table to see if any three columns or diagonals are connected
            doWeHaveAWinner();

            //if turns value has reached 9, no winner is found, call gameIsADraw() method
            if (turns == 9)
            {
                gameIsADraw();
            }
            //if we still have turns to play, change our player_one_turn value
            player_one_turn = !player_one_turn;





    }

    private void doWeHaveAWinner() {
        if ((gameTable[0][0] == -1 && gameTable[0][1] == -1 && gameTable[0][2] == -1)
                || (gameTable[1][0] == -1 && gameTable[1][1] == -1 && gameTable[1][2] == -1) ||
                (gameTable[2][0] == -1 && gameTable[2][1] == -1 && gameTable[2][2] == -1) ||
                (gameTable[0][0] == -1 && gameTable[1][0] == -1 && gameTable[2][0] == -1) ||
                (gameTable[0][1] == -1 && gameTable[1][1] == -1 && gameTable[2][1] == -1) ||
                (gameTable[0][2] == -1 && gameTable[1][2] == -1 && gameTable[2][2] == -1) ||
                (gameTable[0][0] == -1 && gameTable[1][1] == -1 && gameTable[2][2] == -1) ||
                (gameTable[0][2] == -1 && gameTable[1][1] == -1 && gameTable[2][0] == -1))
        {
            winner_found.start();
                //winner is player 2
            Toast winnerToast = Toast.makeText(getApplicationContext(),
                    "Player 2 Wins!!!!!",
                    Toast.LENGTH_SHORT);

            winnerToast.show();
            player_two += 1;
            player_two_score.setText(String.valueOf(player_two));
            player_one_turn = true;
            setGameTable();
        }
        else if ((gameTable[0][0] == 1 && gameTable[0][1] == 1 && gameTable[0][2] == 1)
                || (gameTable[1][0] == 1 && gameTable[1][1] == 1 && gameTable[1][2] == 1) ||
                (gameTable[2][0] == 1 && gameTable[2][1] == 1 && gameTable[2][2] == 1) ||
                (gameTable[0][0] == 1 && gameTable[1][0] == 1 && gameTable[2][0] == 1) ||
                (gameTable[0][1] == 1 && gameTable[1][1] == 1 && gameTable[2][1] == 1) ||
                (gameTable[0][2] == 1 && gameTable[1][2] == 1 && gameTable[2][2] == 1) ||
                (gameTable[0][0] == 1 && gameTable[1][1] == 1 && gameTable[2][2] == 1) ||
                (gameTable[0][2] == 1 && gameTable[1][1] == 1 && gameTable[2][0] == 1))
        {
            winner_found.start();
            Toast winnerToast = Toast.makeText(getApplicationContext(),
                    "Player 1 Wins!!!!!!",
                    Toast.LENGTH_SHORT);

            winnerToast.show();
            player_one += 1;
            player_one_score.setText(String.valueOf(player_one));
            player_one_turn = true;
            setGameTable();
        }

    }

    public void gameIsADraw(){
        Toast noWinner =Toast.makeText(getApplicationContext(),"Game is a Draw!",Toast.LENGTH_SHORT);

        noWinner.show();
        setGameTable();
    }

    public void setGameTable(){
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++)
            {
                gameTable[i][k] = 0;

            }
        }
        box00.setEnabled(true);
        box01.setEnabled(true);
        box02.setEnabled(true);
        box10.setEnabled(true);
        box11.setEnabled(true);
        box12.setEnabled(true);
        box20.setEnabled(true);
        box21.setEnabled(true);
        box22.setEnabled(true);
        box00.setText("");
        box01.setText("");
        box02.setText("");
        box10.setText("");
        box11.setText("");
        box12.setText("");
        box20.setText("");
        box21.setText("");
        box22.setText("");

        turns = 0;
        player_one_turn = true;
    }

}