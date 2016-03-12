package com.example.admin.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;



public class MainActivity extends AppCompatActivity {
    //Order queue stores the order the buttons need to be pressed
    //Will only be added values to
    //Will be reset at the end of every game
    java.util.Queue<Integer> Order;
    //NewButt randomizes a new number every match to store into the Order Queue
    //NewButt represents the four game buttons
    Random NewButt;
    //Temp represents Order and during game it checks if the player gets the order of the buttons correct
    //resets to match "Order" at the start of every new level
    java.util.Queue<Integer> Temp;
    //Represents Game buttons
    //0 = Green
    //1 = Blue
    //2 = Yellow
    //3 = Red
    Button[] Btn;
    MediaPlayer[] ButSounds;
    //Activity mActivity;
    //Represents the play button
    ImageView Playbut;
    ImageView Stopbut;
    //Represents level
    TextView Level;

    LinearLayout gameOverScreen;
    EditText Name;//Represents user's name after game over
    Intent MoveToHighScore;

    Handler H;//Handler thread to create a delay


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewButt = new Random();
        Btn = new Button[4];
        Btn[0] =(Button)findViewById(R.id.GreenBtn);
        Btn[0].setClickable(false);
        Btn[1] =(Button)findViewById(R.id.BlueBtn);
        Btn[1].setClickable(false);
        Btn[2] =(Button)findViewById(R.id.YellowBtn);
        Btn[2].setClickable(false);
        Btn[3] =(Button)findViewById(R.id.RednBtn);
        Btn[3].setClickable(false);
        ButSounds = new MediaPlayer[4];
        ButSounds[0] = MediaPlayer.create(this,R.raw.a);
        ButSounds[1] = MediaPlayer.create(this,R.raw.b);
        ButSounds[2] = MediaPlayer.create(this,R.raw.c);
        ButSounds[3] = MediaPlayer.create(this,R.raw.d);
       //mActivity = this;
        Playbut =(ImageView)findViewById(R.id.play);
        Stopbut =(ImageView)findViewById(R.id.Stop);
        Stopbut.setClickable(false);
        Level = (TextView)findViewById(R.id.Level);
        gameOverScreen = (LinearLayout)findViewById(R.id.ScoreWindow);
        Name = (EditText)findViewById(R.id.Name);
        MoveToHighScore = new Intent(this,HighScoreShow.class);
        H = new Handler();

    }

    public void Green(View v) {
        PlayerTurn(0);
        PlaySound(0);
    }

    public void Blue(View v) {
        PlayerTurn(1);
        PlaySound(1);
    }

    public void Yellow(View v) {
        PlayerTurn(2);
        PlaySound(2);
    }

    public void Red(View v) {
        PlayerTurn(3);
        PlaySound(3);
    }
    //Plays pressed button's sound
    void PlaySound(final int ButtonId){
        H.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButSounds[ButtonId].start();
            }
        }, 200);
    }

    //Cycles through a button's click animation
   public void PressAnim(final Integer ButtonId,final Queue temp){
       Btn[ButtonId].setPressed(true);
       PlaySound(ButtonId);
       H.postDelayed(new Runnable() {
           @Override
           public void run() {
               Btn[ButtonId].setPressed(false);
               if(!temp.isEmpty()) {
                   PressAnim((int)temp.remove(), temp);
               }
           }
       }, 2000);
       /*if(!temp.isEmpty()) {
           PressAnim((int) temp.remove(), temp);
       }*/
   }

    //Function to show the player which buttons to press
    public void Animate(){
        Integer Rando = NewButt.nextInt(4); //Takes out random number between 0 and 4
        Order.add(Rando);//Pushes random number into the Order Queue
        Temp = new LinkedList(Order);  // saves Order queue into Temp Queue
        //Goes to show the player which buttons to press
        String lev = "" + Order.size();
        Level.setText(lev);//Shows player Level (Player level is the same as "Order" queue's size).
        PressAnim(Order.peek(),new LinkedList(Order));
    }

    public void PlayerTurn(int ButtonNumber){
        //If button pressed by the player is the correct button
           if (Temp.peek() == ButtonNumber) {
               Temp.remove();
           } else {
               gameOverScreen.setVisibility(View.VISIBLE);

           }
        //If player was able to get the series correct
        if (Temp.isEmpty()){
                    Animate();
    }
    }

    //Whenever play button is pressed
    public void Play(View v) {
        Order = new LinkedList<>();
        Animate();

        v.setClickable(false);
        Stopbut.setClickable(true);
        //Set all play buttons as clickable
        for (int i=0;i<4;i++)
            Btn[i].setClickable(true);
        Stopbut.setVisibility(View.VISIBLE);
        Playbut.setVisibility(View.INVISIBLE);
        }

    //Whenever game is stopped
    public void Stop(View v){
        v.setClickable(false);
        this.Playbut.setClickable(true);
        gameOverScreen.setVisibility(View.VISIBLE);
    }

    //Both Next and Back are only visible in the game over screen

    //Move to Score window
    public void Next(View v){
        MoveToHighScore.putExtra("Name", this.Name.getText().toString());
        MoveToHighScore.putExtra("Score",Order.size());
        startActivity(MoveToHighScore);
        this.finish();
    }
    //Go back to game
    public void Back(View v){
            super.recreate();
    }
    }
