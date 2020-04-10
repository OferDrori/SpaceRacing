package com.example.Space_Racing;
import androidx.annotation.NonNull;

public class Player implements Comparable<Player> {
    private int score;
    private String name;
    double longitude = 0;
    double latitude = 0;

    public Player(int score , String name , double longitude,double latitude) {
        this.score = score;
        this.name = name;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    @NonNull
    @Override
    public String toString() {
       return this.name+"         "+this.getScore();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getlatitude() {
        return latitude;
    }
    public int compareTo(Player player)
    {
        if(player == null) {
            return 0;
        }
        return player.getScore() - this.getScore();
    }
}
