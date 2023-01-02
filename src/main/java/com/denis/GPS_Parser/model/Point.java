package com.denis.GPS_Parser.model;

public interface Point {
    double getDegrees();

    String getDirection();

    default double toRadians() {
        return getDegrees() * Math.PI / 180;
    }
}
