package com.example.admin.myapplication;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    Intent toAbout;
    Intent toGame;
    TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toAbout = new Intent(this , AboutPage.class);
        toGame = new Intent(this , MainActivity.class);
        Title = (TextView)findViewById(R.id.Title);
    }
    public void About(View v){
        startActivity(toAbout);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
    public void PlayGame(View v){
        startActivity(toGame);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out_right);
        finish();
    }


}
