/*
 * Author: Zeerak Asim
 * Student #: 20237955
 * Title: Assignment 3 Instrumentation
 * Course: SOFT437 Performance Analysis
 * Date Submitted: November 10, 2023.
 * 
 * The Instrumentation class code for the assignment.
 */

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Instrumentation{
    //Initialzing global variables for the class
    private long startTime = 0;
    private long stopTime = 0;
    private long timeTaken = 0;
    private long totalTime = 0;
    private boolean activated;
    private String logFileN;
    private List <String> logFileMessages = new ArrayList<>();

    //Creating and setting the variable for the instantiation for Instrumentation to NULL
    private static Instrumentation i = null;

    //Constructor for Instrumentation
    private Instrumentation(){
        //Initializing the activation to false to set the Instrumentation to off by default
        activated = false;
        
        //Setting the format of the date, and then getting
        SimpleDateFormat currentdate = new SimpleDateFormat("ddyyMMhhmmss");

        //Naming the log file accompanied by the time it was made
        logFileN = "instrumentation" + currentdate.format(new Date()) + ".log";
    }

    //Getter for instance of Instrumentation
    public static Instrumentation getInstance(){
        //If the instantiation is null, then create a new instantiation of the Instrumentation class
        if (i == null)
            i = new Instrumentation();

        //Else just return the current instantiation
        return i;
    }

    //Getter for the filename where log content will be written to
    public String getFileName(){
        //Return the file name
        return logFileN;
    }

    //Getter for the last time taken
    public long getLastTimeTaken(){
        return timeTaken;
    }

    //Getter for total time taken
    public long getTotalTimeTaken(){
        return totalTime;
    }

    public void writeInLogFile(String logFileMsg){
        //Checking if the activation of the instrumentation class is set to true
        if (activated == true)
            //Will append the log file message in the logFileMessages array
            logFileMessages.add(logFileMsg);
    }
    
    //Method for starting the timer
    public void startTiming(String comment){
        //Checking if the activation of the instrumentation class is set to true
        if (activated == true){
            //Set the start time variable with the current time
            startTime = System.currentTimeMillis();
            writeInLogFile("STARTTIMING: " + comment);
        }
    }

    //Method for stopping the timer
    public void stopTiming(String comment){
        //Checking if the activation of the instrumentation class is set to true
        if (activated == true){
            //Set the stop time variable with the current time
            stopTime = System.currentTimeMillis();
            //Will calculate the difference from the stop and start times to get the total time the software took to perform in ms
            timeTaken = (stopTime - startTime);

            if(comment == "Measuring Main Process with Instrumentation Complete!")
                timeTaken = totalTime;

            else
                totalTime += timeTaken;

            writeInLogFile("STOPTIMING: " + comment + " " + timeTaken + "ms");
        }
    }

    //Method for adding a comment when logging
    public void comment(String comment){
        if (activated == true)
            writeInLogFile("COMMENT: " + comment);
    }

    //Method for writing the log content to a file
    public void dump(String filename){
        //If the instrumentation is not activated, write the log content to file and just return.
        if (activated == false)
            return;
        
        //Implementing a try catch structure to attempt to write the log content to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            //Will iterate through every log file message that is in the array logFilesMessages
            for (String logFileMessage : logFileMessages)
                writer.write(logFileMessage + "\n");

            //Used to flush the buffer after writing, and ensures the log file content is saved in the designated file
            writer.flush();
        }
        //Otherwise throw an IO exception error
        catch(IOException error){
            System.out.println("Error with writing the log content to the designated file!");
            error.printStackTrace();
        }
    }

    //Method for activating the Instrumentation class
    public void activate(boolean onoff){
        activated = onoff;
    }
}