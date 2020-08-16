package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    //The one take the score value
    private int mScore1;
    private int mScore2;

    // The member show the score value
    private TextView mScoreText1;
    private TextView mScoreText2;

    // the Pref file key
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    //declare a pref variable
    private SharedPreferences ScorePreferences;
    private String sharedPrefFile = "com.example.scorekeeper";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScorePreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //find the TextViewID
        mScoreText1=(TextView)findViewById(R.id.score_1);
        mScoreText2=(TextView)findViewById(R.id.score_2);

            mScore1 = ScorePreferences.getInt(STATE_SCORE_1,0);
            mScore2 = ScorePreferences.getInt(STATE_SCORE_2,0);

            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));




    }


    protected void onPause()
    {
        super.onPause();
        //activate the editor tools
        SharedPreferences.Editor preferencesEditor = ScorePreferences.edit();
        //Save the score
        preferencesEditor.putInt(STATE_SCORE_1, mScore1);
        preferencesEditor.putInt(STATE_SCORE_2, mScore2);
        preferencesEditor.apply();

    }


    public void decreaseScore(View view) {
        int ViewID=view.getId();
        switch(ViewID)
        {
            case R.id.decreaseTeam1:
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case  R.id.decreaseTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }

    }
    public void increaseScore(View view) {
        int ViewID=view.getId();
        switch (ViewID)
        {
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    public void resetScore(View view){
        int ViewID=view.getId();
        SharedPreferences.Editor preferencesEditor = ScorePreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
        switch(ViewID)
        {
            case R.id.reset_button1:
                mScore1=0;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case  R.id.reset_button2:
                mScore2=0;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Check if the correct item was clicked
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
// Recreate the activity for the theme change to take effect.
            recreate();
            return true;
        }
        return false;
    }


}