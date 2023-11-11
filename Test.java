/*
 * Author: Zeerak Asim
 * Student #: 20237955
 * Title: Assignment 3 Instrumentation
 * Course: SOFT437 Performance Analysis
 * Date Submitted: November 10, 2023.
 * 
 * The Test class code for the assignment.
 */

import java.util.*;

public class Test{
    //Setting a private limit to the array size for testing
    private static int arraySize = 100000;

    public static int[] populateArray(){
        //Initializing array and random, to generate random values for the array
        int [] array = new int[arraySize];
        Random rand = new Random();

        //Getting random values for the array
        for(int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(9999) + 1;

        return array;
    }

    public static void swap(int [] array, int i, int j){
        int temp = 0;

        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int partition(int [] array, int low, int hi){
        int key = 0;
        key = array[hi];
        int i = (low-1);

        for (int j = low; j < hi; j++){
            if (array[j] < key){
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i+1, hi);
        return (i+1);
    }

    public static void bubbleSort(int [] array){
        boolean isSwapped = false;

        for (int i = 0; i < arraySize-1; i++){
            isSwapped = false;
            
            for (int j = 0; j < arraySize-1; j++){
                if (array[j] > array[j+1]){
                    swap(array, j, j+1);
                    isSwapped = true;
                }
            }

            //if there was no swap within the inner loop, then break out of the loop
            if (isSwapped == false)
                break;
        }
    }

    public static void quickSort(int [] array, int low, int hi){
        int part = 0;

        if (low < hi){
            part = partition(array, low, hi);
            quickSort(array, low, part-1);
            quickSort(array, part+1, hi);
        }
    }

    public static long getStartTestTime(){
        long startTimePA = 0;
        startTimePA = System.currentTimeMillis();
        return startTimePA;
    }

    public static long getEndTestTime(){
        long endTimePA = 0;
        endTimePA = System.currentTimeMillis();
        return endTimePA;
    }

    public static long getMainStartTestTime(){
        long startTimePA = 0;
        startTimePA = System.currentTimeMillis();
        return startTimePA;
    }

    public static long getMainEndTestTime(){
        long endTimePA = 0;
        endTimePA = System.currentTimeMillis();
        return endTimePA;
    }

    public static void main(String args[]) {
        Instrumentation i = Instrumentation.getInstance();
        String filename = "";
        int[] arrayWI, arrayWOI;
        long startT = 0;
        long endT = 0;
        long totalTime = 0;
        long main_startT = 0;
        long totalMainTime = 0;

        i.activate(true);

        //Measure the overhead of instrumentation
        i.startTiming("Measuring Instrumentation Overhead...");
        i.startTiming("Measuring Overhead...");
        i.comment("Still measuring...");
        i.stopTiming("Measuring Overhead Complete");
        i.stopTiming("Instrumentation Overhead Complete");
        System.out.println("Time Taken: " + i.getLastTimeTaken() + " ms\n");

        //Main process timing with instrumentation
        i.startTiming("Measuring Main with Instrumentation...");

        //Generating Array with instrumentation
        i.startTiming("Generating Array with Instrumentation...");
        arrayWI = populateArray();
        i.comment("Generation with Instrumentation Successful!");
        i.stopTiming("Array Process with Instrumentation Complete!");
        System.out.println("Time Taken: " + i.getLastTimeTaken() + " ms\n");

        //Bubble Sort with instrumentation
        i.startTiming("Applying Bubble Sort Algorithm with Instrumentation...");
        bubbleSort(arrayWI);
        i.stopTiming("Bubble Sort Algorithm with Instrumentation Complete!");
        System.out.println("Time Taken: " + i.getLastTimeTaken() + " ms\n");

        //Quick Sort with instrumentation
        i.startTiming("Applying Quick Sort Algorithm with Instrumentation...");
        quickSort(arrayWI, 0, arrayWI.length-1);
        i.stopTiming("Quick Sort Algorithm with Instrumentation Complete!");
        System.out.println("Time Taken: " + i.getLastTimeTaken() + " ms\n");

        i.comment("All Processes with Instrumentation Complete!");

        //Stop timing for the main process with instrumentation
        i.stopTiming("Measuring Main Process with Instrumentation Complete!");
        System.out.println("Time Taken: " + i.getLastTimeTaken() + " ms\n");

        //Write to log file
        filename = i.getFileName();
        i.dump(filename);

        System.out.println("Without Instrumentation\n");

        //Deactivate instrumentation for the next part
        i.activate(false);

        main_startT = getMainStartTestTime();

        //Main process without instrumentation
        startT = getStartTestTime();
        arrayWOI = populateArray();
        endT = getEndTestTime();
        totalTime = endT - startT;
        totalMainTime += totalTime;
        System.out.println("Time Taken: " + totalTime + " ms\n");

        //Bubble Sort without instrumentation
        startT = getStartTestTime();
        bubbleSort(arrayWOI);
        endT = getEndTestTime();
        totalTime = endT - startT;
        totalMainTime += totalTime;
        System.out.println("Time Taken: " + totalTime + " ms\n");

        //Quick Sort without instrumentation
        startT = getStartTestTime();
        quickSort(arrayWOI, 0, arrayWOI.length-1);
        endT = getEndTestTime();
        totalTime = endT - startT;
        totalMainTime += totalTime;
        System.out.println("Time Taken: " + totalTime + " ms\n");
        
        System.out.println("Total Time Taken: " + totalMainTime + " ms\n");
        System.out.println("All Processes without Instrumentation Complete!");
    }
}