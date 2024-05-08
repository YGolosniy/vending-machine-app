package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Log {

    private static final String LOG_FILE = "src/main/resources/Log.txt";

    public static void logTransaction(String logMessage) {

        try(PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            String timestamp = dateFormat.format(new Date());
            writer.println(timestamp + " " + logMessage);

        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

}