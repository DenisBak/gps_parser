package com.denis.GPS_Parser;

import com.denis.GPS_Parser.controler.GpsFileParser;
import com.denis.GPS_Parser.model.Coordinate;
import com.denis.GPS_Parser.model.Latitude;
import com.denis.GPS_Parser.model.Longitude;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GpsParserApplicationTests {
    public static void main(String[] args) throws IOException {
        List<Coordinate> coordinates;
        double totalDistance = 0; // in meters

        GpsFileParser fileParser = new GpsFileParser(new File("C:\\Users\\denis\\OneDrive\\Documents\\nmea.log"));
        coordinates = fileParser.initCoordinates();
        totalDistance = Coordinate.getDistanceTraveled(coordinates);
        System.out.println(totalDistance);
    }
}
