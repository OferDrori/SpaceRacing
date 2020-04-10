package com.example.Space_Racing;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.Space_Racing.Keys.KEY_SCORES;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MySharedPreferences msp;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        msp = new MySharedPreferences(this);
        lstView = findViewById(R.id.listPlaces);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        showtopTenList();
        // Add a marker in Sydney and move the camera

    }

    private void showtopTenList() {
        ArrayList<Player> topPlayers;
        Gson gson = new Gson();
        topPlayers = gson.fromJson(msp.getString(KEY_SCORES, ""), new TypeToken<List<Player>>() {
        }.getType());
        ArrayList<Player> topPlayersStr = new ArrayList<>();

        for (int i = 0; i < topPlayers.size(); i++) {
            topPlayersStr.add(topPlayers.get(i));
            LatLng sydney = new LatLng(topPlayers.get(i).getlatitude(),topPlayers.get(i).getLongitude());
             mMap.addMarker(new MarkerOptions().position( new LatLng(topPlayers.get(i).getlatitude(), topPlayers.get(i).getLongitude())).title(topPlayers.get(i).getName()));

    }
        ArrayAdapter<Player> arrayAdapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, topPlayersStr);
        lstView.setAdapter(arrayAdapter);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "Hello ", Toast.LENGTH_LONG).show();

               Player player= (Player) adapterView.getAdapter().getItem(i);
               LatLng latLng= new LatLng(player.getlatitude(),player.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
            }
        });
    }
}
