package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.admin.myapplication.Nodes.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;



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
    MediaPlayer[] Sounds;
    Activity mActivity;
    //Represents the play button
    ImageView Playbut;
    ImageView Stopbut;
    //Represents level
    TextView Level;
    LinearLayout Score;
    EditText Name;
    Intent I;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewButt = new Random();
        Btn = new Button[4];
        Btn[0] =(Button)findViewById(R.id.GreenBtn);
        Btn[1] =(Button)findViewById(R.id.BlueBtn);
        Btn[2] =(Button)findViewById(R.id.YellowBtn);
        Btn[3] =(Button)findViewById(R.id.RednBtn);
        Sounds = new MediaPlayer[4];
        Sounds[0] = MediaPlayer.create(this,R.raw.a);
        Sounds[1] = MediaPlayer.create(this,R.raw.b);
        Sounds[2] = MediaPlayer.create(this,R.raw.c);
        Sounds[3] = MediaPlayer.create(this,R.raw.d);
        mActivity = this;
        Playbut =(ImageView)findViewById(R.id.play);
        Stopbut =(ImageView)findViewById(R.id.Stop);
        Stopbut.setClickable(false);
        Level = (TextView)findViewById(R.id.Level);
        Score = (LinearLayout)findViewById(R.id.ScoreWindow);
        Name = (EditText)findViewById(R.id.Name);
        I = new Intent(this,HighScore.class);
    }

    public void Green(View v) {
        PressAnim(0);
        PlayerTurn(0);
    }

    public void Blue(View v) {
        PressAnim(1);
        PlayerTurn(1);
    }

    public void Yellow(View v) {
        PressAnim(2);
        PlayerTurn(2);
    }

    public void Red(View v) {
        PressAnim(3);
        PlayerTurn(3);
    }

    //Cycles through a button's click animation
   public void PressAnim( final int ButtonId){
               Btn[ButtonId].setPressed(true);
               Sounds[ButtonId].start();
               try {
                   //Try pausing button for .1 seconds
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   //If problem occurs than print stack trace
                   e.printStackTrace();
               }
               Btn[ButtonId].invalidate();
           }

    //Function to show the player which buttons to press
    public void Animate(){
        Integer Rando = NewButt.nextInt(4); //Takes out random number between 0 and 4
        Order.add(Rando);//Pushes random number into the Order Queue
        Temp = new LinkedList(Order);  // saves Order queue into Temp Queue
        //Goes to show the player which buttons to press
        String lev = "" + Order.size();
        Level.setText(lev);
        while (!Temp.isEmpty())
            PressAnim(Temp.remove());
        if(Order.isEmpty())
        {
            Score.setVisibility(View.VISIBLE);
        }
        Temp = new LinkedList(Order);
    }
    public void PlayerTurn(int ButtonNumber){
           if (Temp.peek() == ButtonNumber) {
               Temp.remove();
            } else {
                Score.setVisibility(View.VISIBLE);

            }
        if (Temp.isEmpty()){
            Animate();
        }
    }

    public void Play(View v) {
        Order = new LinkedList<>();
        Animate();
        v.setClickable(false);
        Stopbut.setClickable(true);
        for (int i=0;i<4;i++)
            Btn[i].setClickable(true);
        Stopbut.setVisibility(View.VISIBLE);
        Playbut.setVisibility(View.INVISIBLE);
        }
    public void Stop(View v){
        Level.setText("Start");
        v.setClickable(false);
        this.Playbut.setClickable(true);
        Score.setVisibility(View.VISIBLE);
    }
    public void Next(View v){
        //Intent I = new Intent(this,HighScore.class);
        startActivity(I);
        this.finish();
    }
    public void Back(View v){
        if (Build.VERSION.SDK_INT >= 11)
            super.recreate();
        else{
            startActivity(getIntent());
            finish();
        }
    }
    }
