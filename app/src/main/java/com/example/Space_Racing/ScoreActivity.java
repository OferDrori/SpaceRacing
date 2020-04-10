package com.example.Space_Racing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.Space_Racing.Keys.KEY_SCORES;
import static com.example.Space_Racing.Keys.KEY_TO_SCORE_ACTIVITY;

public class ScoreActivity extends AppCompatActivity {
    private TextView scoreActivity_TXT_score;
    private Button playAgain;
    private Button btnMap;
    private ListView lstView;
    private MySharedPreferences msp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreActivity_TXT_score = findViewById(R.id.score);
        Intent intent = getIntent();
        scoreActivity_TXT_score.setText("your score is " + intent.getExtras().getInt(KEY_TO_SCORE_ACTIVITY));
        msp = new MySharedPreferences(this);
        playAgain = findViewById(R.id.btn_score_startAgain);
        btnMap = findViewById(R.id.btn_score_Map);
        lstView = findViewById(R.id.listView_score_lst);
        showtopTenList();
        playAgain.setOnClickListener(goToMenuActivity);
        btnMap.setOnClickListener(showLocationActivity);

    }

    private void showtopTenList() {
        ArrayList<Player> topPlayers;
        Gson gson = new Gson();
        topPlayers = gson.fromJson(msp.getString(KEY_SCORES, ""), new TypeToken<List<Player>>() {
        }.getType());
        ArrayList<String> topPlayersStr = new ArrayList<>();
        for (int i = 0; i < topPlayers.size(); i++) {
            topPlayersStr.add(topPlayers.get(i).toString());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topPlayersStr);
        lstView.setAdapter(arrayAdapter);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "Hello ", Toast.LENGTH_LONG).show();

            }
        });
    }

    View.OnClickListener goToMenuActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent next = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(next);
            finish();

        }
    };
    View.OnClickListener showLocationActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent next = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(next);
            finish();
        }
    };

}


