package com.example.admin.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.myapplication.Nodes.Node;
import com.example.admin.myapplication.Nodes.Queue;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Queue<Integer> Order;
    Random NewButt;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Order = new Queue<>();
        NewButt = new Random();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void Green(View v) {

    }

    public void Blue(View v) {

    }

    public void Yellow(View v) {

    }

    public void Red(View v) {

    }

    private void play(View v) {
        int Rando = NewButt.nextInt(4); //Takes out random number between 0 and 4
        Order.Push(Rando);  //Pushes random number into the Order Queue
    }

}