package com.denis.GPS_Parser.model;

import com.denis.GPS_Parser.controler.GPGGALineParser;

public class Longitude implements Point {

    private final double degrees;
    private final String direction;

    public Longitude(double degrees, double minutes, String direction) {
        this.degrees = degrees + (minutes / 60);
        this.direction = direction;
    }

    @Override
    public double getDegrees() {
        return degrees;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    public static Longitude parse(String line) {
        String[] coordinateInfo = line.split(",");

        String point = coordinateInfo[GPGGALineParser.LON_INDEX];
        int degrees = Integer.parseInt(point.substring(0, GPGGALineParser.LON_MIN_START_INDEX));
        double minutes = Double.parseDouble(point.substring(GPGGALineParser.LON_MIN_START_INDEX));
        String direction = coordinateInfo[GPGGALineParser.LON_DIRECTION_INDEX];

        return new Longitude(degrees, minutes, direction);
    }

    @Override
    public String toString() {
        return "Longitude{" +
                "degrees=" + degrees +
                ", direction='" + direction + '\'' +
                '}';
    }
}
