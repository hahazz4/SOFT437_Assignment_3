/*
 * Author: Zeerak Asim
 * Student #: 20237955
 * Title: Assignment 3 Instrumentation
 * Course: SOFT437 Performance Analysis
 * Date Submitted: November 10, 2023.
 * 
 * The Main class code for the assignment.
 */

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test{
    //Setting a private limit to the array size for testing
    private static int arraySize = 10000;

    public static int[] populateArray(){
        //Initializing array and random, to generate random values for the array
        int [] array = new int[arraySize];
        Random rand = new Random();

        //Getting random values for the array
        for(int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(99999) + 1;

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

        //
        swap(array, i+1, hi);
        //
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

    public static void main(String args[]) {
        Instrumentation i = Instrumentation.getInstance();
        String filename = "";
        int[] array = {};

        i.activate(true);

        //Measure the overhead of instrumentation
        i.startTiming("Measuring Instrumentation Overhead...");

        i.startTiming("Measuring Overhead...");
        i.stopTiming("Measuring Overhead Complete!");
        
        i.stopTiming("Instrumentation Overhead Complete");

        //Main process timing
        i.startTiming("Measuring Main...");

        //Generating Array
        i.startTiming("Generating Array...");
        array = populateArray();
        i.comment("Generation Successful!");
        i.stopTiming("Array Process Complete!");

        //Bubble Sort
        i.startTiming("Applying Bubble Sort Algorithm...");
        bubbleSort(array);
        i.stopTiming("Bubble Sort Algorithm Complete!");

        //Quick Sort
        i.startTiming("Applying Quick Sort Algorithm...");
        quickSort(array, 0, array.length-1);
        i.stopTiming("Quick Sort Algorithm Complete!");

        i.comment("All Processes Complete!");

        //Stop timing for the main process
        i.stopTiming("Measuring Main Process Complete!");

        //Write to log file
        filename = i.getFileName();
        i.dump(filename);
    }
}