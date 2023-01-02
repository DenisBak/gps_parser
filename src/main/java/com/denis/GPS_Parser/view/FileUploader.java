package com.denis.GPS_Parser.view;

import com.denis.GPS_Parser.controler.GpsFileParser;
import com.denis.GPS_Parser.model.Coordinate;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttributeView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FileUploader {
    private static int fileId = 0;
    private static final Set<String> allowedExtensionsForFileUpload;
    private static final Path pathToDir = Path.of("D:\\upload\\");
    private static File fileToParse;
    private static GpsFileParser fileParser;

    static {
        allowedExtensionsForFileUpload = new HashSet<>();
        allowedExtensionsForFileUpload.add("log");
        allowedExtensionsForFileUpload.add("txt");

    }

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, ModelMap model) {
        String fileName = String.valueOf(fileId++);
        List<Coordinate> coordinates;
        double totalDistance = 0; // in meters
        try {
            if (!allowedExtensionsForFileUpload.contains(FilenameUtils.getExtension(file.getOriginalFilename()))) {
                throw new Exception();
            }
            if (!Files.exists(pathToDir)) Files.createDirectory(pathToDir);

            fileToParse = Paths.get(pathToDir.toString(), fileName).toFile();
            file.transferTo(fileToParse);
            fileParser = new GpsFileParser(fileToParse);
            coordinates = fileParser.initCoordinates();
            totalDistance = Coordinate.getDistanceTraveled(coordinates);
            fileToParse.deleteOnExit();
        } catch (Exception e) {
            model.put("distance", totalDistance);
        }
        model.put("distance", totalDistance);
        return new ModelAndView("redirect:/", model);
    }

    @GetMapping("/")
    public String hello(@RequestParam(value = "distance", required = false) String distance, ModelMap model) {
        model.put("distance", distance);
        return "/index";
    }

}
