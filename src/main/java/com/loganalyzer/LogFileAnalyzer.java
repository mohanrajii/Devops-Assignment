package com.loganalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFileAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(LogFileAnalyzer.class);

    // Update this path to point to your log file, not a directory
    private static final String LOG_FILE_PATH = "D:\\Logs\\logfile.log"; // Example: "D:\\Logs\\logfile.log"
    private static final String PATTERN = "ERROR"; // The pattern to search for

    public static void main(String[] args) {
        File file = new File(LOG_FILE_PATH);
        if (!file.exists() || !file.isFile()) { // Check if it's a valid file
            logger.error("File not found or is not a file: " + LOG_FILE_PATH);
            return; // Exit if the file is not valid
        }

        analyzeLogFile(LOG_FILE_PATH, PATTERN);
    }

    public static void analyzeLogFile(String filePath, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = regex.matcher(line);
                if (matcher.find()) {
                    logger.info("Match found: " + line);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading the log file", e);
        }
    }
}
