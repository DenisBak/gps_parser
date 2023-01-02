package com.denis.GPS_Parser.model;

import java.util.List;
import static java.lang.Math.*;

public class Coordinate {
    private static final int EARTH_RAD = 6372795;
    private final Point lat;
    private final Point lon;

    public Coordinate(Point lat, Point lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Point getLat() {
        return lat;
    }

    public Point getLon() {
        return lon;
    }

    public static double getDistanceTraveled(List<Coordinate> coordinates) {
        double totalDistance = 0d;

        Coordinate coordinate1 = null;
        Coordinate coordinate2;

        double lat1;
        double lat2;
        double lon1;
        double lon2;

        double cLat1;
        double cLat2;
        double sLat1;
        double sLat2;

        double delta;
        double cDelta;
        double sDelta;

        double y;
        double x;

        double ad;
        double dist;

        for (Coordinate current : coordinates) {
            if (coordinate1 == null) {
                coordinate1 = current;
                continue;
            }
            coordinate2 = current;
            lat1 = coordinate1.getLat().toRadians();
            lat2 = coordinate2.getLat().toRadians();
            lon1 = coordinate1.getLon().toRadians();
            lon2 = coordinate2.getLon().toRadians();

            cLat1 = cos(lat1);
            cLat2 = cos(lat2);
            sLat1 = sin(lat1);
            sLat2 = sin(lat2);

            delta = lon2 - lon1;
            cDelta = cos(delta);
            sDelta = sin(delta);

            y = sqrt(pow(cLat2 * sDelta, 2) + pow(cLat1 * sLat2 - sLat1 * cLat2 * cDelta, 2));
            x = sLat1 * sLat2 + cLat1 * cLat2 * cDelta;

            ad = atan2(y, x);
            dist = ad * EARTH_RAD;
            totalDistance += dist;

            coordinate1 = null;
        }
        return totalDistance;
    }
    @Override
    public String toString() {
        return "Coordinate{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
