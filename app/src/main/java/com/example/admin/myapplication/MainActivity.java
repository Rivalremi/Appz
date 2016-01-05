package com.example.admin.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.myapplication.Nodes.Node;
import com.example.admin.myapplication.Nodes.Queue;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

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
    }

    public void Green(View v) {

    }

    public void Blue(View v) {

    }

    public void Yellow(View v) {

    }

    public void Red(View v) {

    }

    //Called by Computer to show what buttons to click
    public void changestate(int ButtonId,int DrawableId ){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Btn[ButtonId].setBackground(getResources().getDrawable(DrawableId));
            Sounds[ButtonId].start();
            

        }
        else {
            Btn[ButtonId].setBackgroundDrawable(getResources().getDrawable(DrawableId));
        }
    }


    public void Play(View v) {
        Order = new Queue<>();
        Integer Rando = NewButt.nextInt(4); //Takes out random number between 0 and 4
        Order.size();
        Order.Push(Rando);//Pushes random number into the Order Queue
        Temp = Order;// saves Order queue into Temp Queue
        switch (Rando){
            case 0:changestate(Rando, R.drawable.greenpressed);break;
            case 1:changestate(Rando, R.drawable.bluepressed);break;
            case 2:changestate(Rando, R.drawable.yellowpressed);break;
            case 3:changestate(Rando, R.drawable.redpressed);break;
        }
        v.setClickable(false);
        }
    public void show(){

    }
    }
