/*
 * Author: Zeerak Asim
 * Student #: 20237955
 * Title: Assignment 3: Instrumentation
 * Course: SOFT437: Performance Analysis
 * Date Submitted: November 10, 2023.
 * 
 * The Instrumentation class code for the assignment.
 */


import java.util.*;

public class Instrumentation{
    //Initialzing global variables for the class
    private long startTime;
    private boolean activated;
    private String logFileN;

    //Creating and setting the variable for the instantiation for Instrumentation to NULL
    private static Instrumentation i = null;

    //Constructor for Instrumentation
    private Instrumentation(){
        //Initializing the activation to false to set the Instrumentation to off by default.
        activated = false;
        
        //Naming the log file accompanied by the time it was made.
        logFileN = "instrumentation" + System.currentTimeMillis() + ".log";
    }

    //Getter for instance of Instrumentation
    public static Instrumentation getInstance(){
        //If the instantiation is null, then create a new instantiation of the Instrumentation class
        if (i == null)
            i = new Instrumentation();

        //Else just return the current instantiation
        return i;
    }

    //Method for starting the timer
    public void startTiming(String comment){

    }

    //Method for stopping the timer
    public void stopTiming(String comment){

    }

    //Method for ...
    public void comment(String comment){

    }

    //Method for ...
    public void dump(String filename){

    }

    //Method for activating the Instrumentation class
    public void activate(boolean onoff){
        activated = onoff;
    }
}