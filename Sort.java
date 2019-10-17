/*
Name: Brian Spencer
Date: Oct. 9, 2019
Purpose: Sorts the contents of a file in ascending order by the length of the name, then
         alphabetically.
 */
package Sort;

import java.io.File;
import java.util.Scanner;

public class Sort {

    // constructor for Sort object
    public Sort(File file) throws Exception {
        readFile(file);
    }

    // method to read a file parameter to create an array of strings
    public static void readFile(File file) throws Exception {

        Scanner sc = new Scanner(file);

        int numNames = 0;

        // Scanning the file for names that aren't empty lines
        // and then incrementing the numNames variable so we 
        // can create an array of numNames size
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.length() > 0) {
                numNames++;
            }
        }

        // array to hold the names of the list
        String[] names = new String[numNames];

        int index = 0;

        sc = new Scanner(file);

        // read the current line and put it in the array of names
        // increment the index variable, so we can fill up the array 
        // with names
        while (sc.hasNextLine()) {
            String inputString = sc.nextLine();
            if (inputString.length() > 0) {
                names[index] = inputString + "";
                names[index] = names[index].replaceAll(" ", "");
                index++;
            }
        }

        // call our sortList method to sort our newly made array of names
        sortList(names);
    }

    // method to sort array of names, passed as a parameter
    public static void sortList(String[] names) {
        // a bubble-sort implementation to put the names in order of ascending length
        for (int i = 1; i < names.length; i++) {
            for (int j = 0; j < names.length - i; j++) {
                if (names[j].length() > names[j + 1].length()) {
                    String temp = names[j] + "";
                    names[j] = names[j + 1] + "";
                    names[j + 1] = temp + "";
                } 
            }
        }
        
        // a bubbl-sort implementation to sort names of equal length by each character
        for (int j = 1; j < names.length; j++) {
            for (int k = 0; k < names.length - j - 1; k++) {
                if (names[k].length() == names[k + 1].length()) {
                    if ((names[k].toLowerCase().compareTo(names[k + 1].toLowerCase())) > 0) {
                        String temp = names[k] + "";
                        names[k] = names[k + 1] + "";
                        names[k + 1] = temp + "";
                    }
                }
            }
        }
        
        // print the contents of the string array
        printList(names);
    }

    public static void printList(String[] names) {
        // printing out the array of names
        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) throws Exception {
        // pass the path to the file as a parameter 
        File file = new File("C:\\Users\\Brian\\Downloads\\Sort Me.txt");

        Sort sort = new Sort(file);
    }
}
