package com.example.Space_Racing;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import static com.example.Space_Racing.Keys.KEY_SPEED;
import static com.example.Space_Racing.Keys.KEY_TOUCH_CONTROLLER;


public class MenuActivity extends AppCompatActivity {
    private Button btnStart;
    private final int FAST_SPEED = 500;
    private final int SLOW_SPEED = 700;
    private int speed=SLOW_SPEED;
    private RadioGroup menu_RDG_speed, menu_RDG_control;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private MySharedPreferences msp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnStart = findViewById(R.id.menu_BTN_start);
        menu_RDG_speed = findViewById(R.id.menu_RDG_speed);
        menu_RDG_control = findViewById(R.id.menu_RDG_control);
        btnStart.setOnClickListener(playedFast);
        msp=new MySharedPreferences(this);
        checkAndRequestPermissions();

        //
        // check permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // reuqest for permission

        } else {
            Toast.makeText(this, "Premission NOT granted", Toast.LENGTH_SHORT).show();
        }
        
        
        
        
        menu_RDG_control.check(R.id.menu_RDB_button);
        menu_RDG_speed.check(R.id.menu_RDB_slow);


        //RadioGruou listener
        menu_RDG_speed.setOnCheckedChangeListener(speedConfiguration);
        menu_RDG_control.setOnCheckedChangeListener(controlConfiguration);
    }

    RadioGroup.OnCheckedChangeListener speedConfiguration = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.menu_RDB_fast:
                    speed = FAST_SPEED;
                    // do operations specific to this selection
                    break;
                case R.id.menu_RDB_slow:
                    speed = SLOW_SPEED;
                    // do operations specific to this selection
                    break;
            }
        }
    };


    RadioGroup.OnCheckedChangeListener controlConfiguration = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.menu_RDB_button:
                    msp.putInt(KEY_TOUCH_CONTROLLER,1);
                    // do operations specific to this selection
                    break;
                case R.id.menu_RDB_sensor:
                    msp.putInt(KEY_TOUCH_CONTROLLER,0);
                    break;
            }
        }
    };
    View.OnClickListener playedFast = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent next = new Intent(getApplicationContext(), MainActivity.class);
            next.putExtra(KEY_SPEED, speed);
            startActivity(next);
        }
    };

    private  boolean checkAndRequestPermissions()
    {
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }



}