package com.example.admin.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.admin.myapplication.Nodes.Queue;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {


    //Order queue stores the order the buttons need to be pressed
    //Will only be added values to
    //Will be reset at the end of every game
    Queue<Integer> Order;
    //NewButt randomizes a new number every match to store into the Order Queue
    //NewButt represents the four game buttons
    Random NewButt;
    //Temp represents Order and during game it checks if the player gets the order of the buttons correct
    //resets to match "Order" at the start of every new level
    Queue<Integer> Temp;
    //Represents Game buttons
    //0 = Green
    //1 = Blue
    //2 = Yellow
    //3 = Red
    Button[] Btn;
    MediaPlayer[] Sounds;
    Activity mActivity;


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
    }

    public void Green(View v) {
       PressAnim(0);
    }

    public void Blue(View v) {
        PressAnim(1);
    }

    public void Yellow(View v) {
        PressAnim(2);
    }

    public void Red(View v) {
        PressAnim(3);
    }

    //Cycles through a button's click animation
   public void PressAnim( final int ButtonId){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Btn[ButtonId].setPressed(true);
                    Sounds[ButtonId].start();
                    //If Order of numbers isn't first
                    if (Order.getFirst().getNext() != null){

                    }
                    try{
                        //Try pausing button for .5 seconds
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        //If problem occurs than print stack trace
                        e.printStackTrace();
                    }
                        Btn[ButtonId].invalidate();
                    }
                }

                );
            }


    public void Play(View v) {
        Order = new Queue<>();
        Integer Rando = NewButt.nextInt(4); //Takes out random number between 0 and 4
        Order.Push(Rando);//Pushes random number into the Order Queue
        Temp = Order;// saves Order queue into Temp Queue
        //Cycles through press animation of chosen button
        switch (Rando){
            case 0:PressAnim(Rando);break;
            case 1:PressAnim(Rando);break;
            case 2:PressAnim(Rando);break;
            case 3:PressAnim(Rando);break;
        }

        v.setClickable(false);
        }
    }
