package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    Intent toMenu;
    Intent toGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_show);

        ScoreDatabase = getSharedPreferences("MyScores", Context.MODE_PRIVATE);
        ScoreEdit = ScoreDatabase.edit();

        ScoreList = (ListView) findViewById(R.id.HighScore);


        if (getIntent().getExtras() != null){
            //Gets bundle from main activity
            Extras = getIntent().getExtras();
            AddScore(Extras.getString("Name"),Extras.getInt("Score"));
        }
        else
        {
            PostScores();
        }

        toGame = new Intent(this, MainActivity.class);
        toMenu = new Intent(this, MainMenu.class);
    }

    public String getScores(){

        return ScoreDatabase.getString("Scores","");
    }
    public void AddScore(String Name, int Score) {
        //Gets list of scores from sharedpreference database
        String Scores = getScores();
        if (Score >0) {
            if (Scores.length() >= 1) {
                //If Scores isn't empty

                List<ScoreOrganizer> scoreStrings = new ArrayList<>();
                //Splits scores
                //Scores are automatically set with a | after every score
                String[] TempScores = Scores.split("\\|");
                //for every score split in the TempScores list
                for (String eSc : TempScores) {
                    //Split the names of the players from the numbers
                    String[] parts = eSc.split(" - ");
                    //Add them into the scoreStrings list
                    scoreStrings.add(new ScoreOrganizer(parts[0], Integer.parseInt(parts[1])));
                    //Make a new object of the new player's score
                    //ScoreOrganizer newScore = new ScoreOrganizer(Name, Score);
                    //add that new score to the scoreStrings list
                   // scoreStrings.add(newScore);
                    //Sort the data in the sortStrings list (Descending)
                    Collections.sort(scoreStrings);
                    //ScoreEdit.putString("Scores", scoreBuild.toString());
                }

                //Make a new object of the new player's score
                ScoreOrganizer newScore = new ScoreOrganizer(Name, Score);
                //add that new score to the scoreStrings list
                scoreStrings.add(newScore);
                //Sort the data in the sortStrings list (Descending)
                Collections.sort(scoreStrings);


                StringBuilder scoreBuild = new StringBuilder("");
                for (int s = 0; s < scoreStrings.size(); s++) {
                    if (s >= 10) break;//only want ten
                    if (s > 0) scoreBuild.append("|");// | separates the score strings
                    scoreBuild.append(scoreStrings.get(s).getScore());
                }

                ScoreEdit.putString("Scores", scoreBuild.toString());

            } else {
                //If Scores is empty
                ScoreEdit.putString("Scores", "" + Name + " - " + Score);
            }
            ScoreEdit.commit();
            PostScores();
        }
    }
    protected void onDestroy(){
        if (getIntent().getExtras() != null) {
            //Makes sure everything is in check and no data is lost
            AddScore(Extras.getString("Name"), Extras.getInt("Score"));
        }
        super.onDestroy();
    }

    public void backToMenu(View v){
        startActivity(toMenu);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out_right);
        finish();
    }
    public void backToGame(View v){
        startActivity(toGame);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void PostScores(){
        //get data from sharedPreference and show it in the list
        String ScoreToList = ScoreDatabase.getString("Scores", "");
        String[] s = ScoreToList.split("\\|");
        this.adapter = new ArrayAdapter<>(this, R.layout.list_item, s);
        ScoreList.setAdapter(adapter);
    }
}
