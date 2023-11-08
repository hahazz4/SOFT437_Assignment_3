/*
 * Author: Zeerak Asim
 * Student #: 20237955
 * Title: Assignment 3: Instrumentation
 * Course: SOFT437: Performance Analysis
 * Date Submitted: November 10, 2023.
 * 
 * The Main class code for the assignment.
 */

import java.util.*;

public class Main{
    public static void main(String args[]){
        //Instantiating an object for Instrumentation
        Instrumentation i = Instrumentation.getInstance();

        i.activate(true);

        //Starts the timer
        i.startTimer("");


        //Stops the timer, and tracked how long it took to execute all the code when the timer had started
        i.stopTimer("");

        //Will write the log to a specific file for the program
        i.dump();
    }
}