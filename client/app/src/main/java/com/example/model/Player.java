package com.example.model;

import java.sql.Timestamp;

public class Player {
    private int id;
    private String username;
    private double points;

    public Player(int id, String username,  double points) {
        this.id = id;
        this.username = username;
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
