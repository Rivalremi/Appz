package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutPage extends AppCompatActivity {

    Intent toMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        toMenu = new Intent(this, MainMenu.class);
    }
    public void backToMenu(View v){
        startActivity(toMenu);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out_right);
        finish();
    }
}
