package com.denis.GPS_Parser.controler;

public class GNVTGLineParser {
    private static final String HEADER = "$GNVTG";
    private static final int INDEX_OF_SPEED_POSITION = 5;

    public static boolean isMoving(String speedLine) {
        if (speedLine == null) return  false;
        return Double.parseDouble(
                speedLine.split(",")[INDEX_OF_SPEED_POSITION]) > 0;
    }

    public static String getHeader() {
        return HEADER;
    }
}
