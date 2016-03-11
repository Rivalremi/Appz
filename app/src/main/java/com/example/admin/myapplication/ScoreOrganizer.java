package com.example.admin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.ScriptIntrinsic;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by I'm always dancing on 11/03/2016.
 */
public class ScoreOrganizer implements Comparable<ScoreOrganizer>{

    public String User;
    public int Score;

    public ScoreOrganizer(String User, int Score){
        this.User = User;
        this.Score = Score;
    }


    @Override
    public int compareTo(ScoreOrganizer sc) {
        //return 0 if equal
        //1 if passed greater than this
        //-1 if this greater than passed
        return sc.Score>Score? 1 : sc.Score<Score? -1 : 0;
    }
    public String getScore(){
        return User + " - " + Score;
    }
}

