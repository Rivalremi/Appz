package com.example.admin.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    Intent toAbout;
    Intent toGame;
    Intent toHighScore;
    TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toAbout = new Intent(this , AboutPage.class);
        toGame = new Intent(this , MainActivity.class);
        toHighScore = new Intent(this, HighScoreShow.class);
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
    public void HighScore (View v){
        startActivity(toHighScore);
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
        finish();
    }


}
