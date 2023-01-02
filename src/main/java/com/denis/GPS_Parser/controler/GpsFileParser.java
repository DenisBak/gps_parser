package com.denis.GPS_Parser.controler;

import com.denis.GPS_Parser.model.Coordinate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GpsFileParser {
    private LineIterator lineIterator;

    public GpsFileParser(File file) throws IOException {
        lineIterator = FileUtils.lineIterator(file);
    }

    public List<Coordinate> initCoordinates() {
        String potentialCoordinate = "";
        String line = "";
        GPGGALineParser reader = GPGGALineParser.getNewReader();

        while (lineIterator.hasNext()) {
            line = lineIterator.nextLine();
            try {
                if (line.startsWith(GPGGALineParser.getHEADER())) {
                    potentialCoordinate = line;
                } else if (line.startsWith(GNVTGLineParser.getHeader()) && GNVTGLineParser.isMoving(line)) {
                    reader.saveNewCoordinate(potentialCoordinate);
                }
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Skipped line - " + potentialCoordinate);
            }

        }
        return reader.getCoordinates();
    }
}
