package com.denis.GPS_Parser.controler;

import com.denis.GPS_Parser.model.Coordinate;
import com.denis.GPS_Parser.model.Latitude;
import com.denis.GPS_Parser.model.Longitude;

import java.util.ArrayList;
import java.util.List;

public class GPGGALineParser {
    private static final String HEADER = "$GPGGA";

    public static final int LON_INDEX = 4;
    public static final int LON_MIN_START_INDEX = 3;
    public static final int LON_DIRECTION_INDEX = 5;

    public static final int LAT_INDEX = 2;
    public static final int LAT_MIN_START_INDEX = 2;
    public static final int LAT_DIRECTION_INDEX = 3;
    private static List<Coordinate> coordinates;

    private GPGGALineParser() {
    }

    public static GPGGALineParser getNewReader() {
        coordinates = new ArrayList<>();
        return new GPGGALineParser();
    }

    public void saveNewCoordinate(String line) {
        coordinates.add(parseGPGGARLine(line));
    }

    public static String getHEADER() {
        return HEADER;
    }

    private Coordinate parseGPGGARLine(String line) {
        return new Coordinate(Latitude.parseGPGGALine(line), Longitude.parse(line));
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}
