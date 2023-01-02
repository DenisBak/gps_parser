package com.denis.GPS_Parser.model;

import com.denis.GPS_Parser.controler.GPGGALineParser;

public class Latitude implements Point {

    private final double degrees;
    private final String direction;

    public Latitude(double degrees, double minutes, String direction) {
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

    public static Latitude parseGPGGALine(String line) {
        String[] coordinateInfo = line.split(",");

        String point = coordinateInfo[GPGGALineParser.LAT_INDEX];
        int degrees = Integer.parseInt(point.substring(0, GPGGALineParser.LAT_MIN_START_INDEX));
        double minutes = Double.parseDouble(point.substring(GPGGALineParser.LAT_MIN_START_INDEX));
        String direction = coordinateInfo[GPGGALineParser.LAT_DIRECTION_INDEX];
        return new Latitude(degrees, minutes, direction);
    }

    @Override
    public String toString() {
        return "Latitude{" +
                "degrees=" + degrees +
                ", direction='" + direction + '\'' +
                '}';
    }
}
