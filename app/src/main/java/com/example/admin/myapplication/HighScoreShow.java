package com.example.admin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreShow extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView ScoreList;
    SharedPreferences ScoreDatabase;
    SharedPreferences.Editor ScoreEdit;
    Bundle Extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_show);
        ScoreDatabase = getSharedPreferences("MyScores", Context.MODE_PRIVATE);
        ScoreEdit = ScoreDatabase.edit();
        ScoreList = (ListView) findViewById(R.id.HighScore);
        Extras = getIntent().getExtras();
        if (Extras != null){
            String x = Extras.getString("Name");
            int y = Extras.getInt("Score");
            AddScore(Extras.getString("Name"),Extras.getInt("Score"));
        }
    }

    public String getScores(){

        return ScoreDatabase.getString("Scores","");
    }
    public void AddScore(String Name, int Score) {
        String Scores = getScores();
        if (Score >0) {
            if (Scores.length() >= 1) {
                //If Scores isn't empty
                List<ScoreOrganizer> scoreStrings = new ArrayList<>();
                String[] TempScores = Scores.split("\\|");
                for (String eSc : TempScores) {
                    String[] parts = eSc.split(" - ");
                    scoreStrings.add(new ScoreOrganizer(parts[0], Integer.parseInt(parts[1])));
                    ScoreOrganizer newScore = new ScoreOrganizer(Name, Score);
                    scoreStrings.add(newScore);
                    Collections.sort(scoreStrings);
                    StringBuilder scoreBuild = new StringBuilder("");
                    for (int s = 0; s < scoreStrings.size(); s++) {
                        if (s >= 10) break;//only want ten
                        if (s > 0) scoreBuild.append("|");//pipe separate the score strings
                        scoreBuild.append(scoreStrings.get(s).getScore());
                    }
                    //write to prefs
                    ScoreEdit.putString("Scores", scoreBuild.toString());
                }
            } else {
                //If Scores is empty
                ScoreEdit.putString("Scores", "" + Name + " - " + Score);
            }
            ScoreEdit.commit();
            String ScoreToList = ScoreDatabase.getString("Scores", "");
            String[] s = ScoreToList.split("\\|");
            this.adapter = new ArrayAdapter<>(this, R.layout.list_item, s);
            ScoreList.setAdapter(adapter);
        }
    }
    protected void onDestroy(){
        AddScore(Extras.getString("Name") , Extras.getInt("Score"));
        super.onDestroy();
    }
}
