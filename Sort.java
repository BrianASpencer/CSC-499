/*
Name: Brian Spencer
Date: Oct. 9, 2019
Purpose: Sorts the contents of a file in ascending order by the length of the name, then
         alphabetically.
 */
package Sort;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Sort {
    private String [] names;
    // constructor for Sort object
    private Sort(File file) throws Exception {
        
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
        String[] n = new String[numNames];

        int index = 0;

        sc = new Scanner(file);

        // read the current line and put it in the array of names
        // increment the index variable, so we can fill up the array 
        // with names
        while (sc.hasNextLine()) {
            String inputString = sc.nextLine();
            if (inputString.length() > 0) {
                n[index] = inputString + "";
                n[index] = n[index].replaceAll(" ", "");
                index++;
            }
        }
        this.names = n;
    }
    
    // method to call sorting methods for length and alphabetically
    private void sortList() {
        sortLen();
        sortABC();
    }

    // method to sort array of names, passed as a parameter
    private void sortLen() {
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
    }
    
    private void sortABC() {
        // a bubble-sort implementation to sort names of equal length by each character
        for (int j = 1; j < names.length; j++) {
            for (int k = 0; k < names.length - j - 1; k++) {
                if (names[j].length() > names[j + 1].length()) {
                    String temp = names[j] + "";
                    names[j] = names[j + 1] + "";
                    names[j + 1] = temp + "";
                }
                if (names[k].length() == names[k + 1].length()) {
                    if ((names[k].compareToIgnoreCase(names[k + 1])) > 0) {
                        String temp = names[k] + "";
                        names[k] = names[k + 1] + "";
                        names[k + 1] = temp + "";
                    }
                }
            }
        }
    }

    private void print() {
        // printing out the array of names
        String s = "";
        int cnt = 0;
        for (String name : names) {
            if (cnt != names.length-1) {
                s += name + "\n";
            } else {
                s += name;
            }
            cnt++;
        }
        writeUsingFiles(s);
    }

    private static void writeUsingFiles(String data) {
        try {
            Files.write(Paths.get("C:\\Users\\Brian\\Desktop\\CSC499\\Output.txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // pass the path to the file as a parameter 
        File file = new File("C:\\Users\\Brian\\Desktop\\CSC499\\Sort Me.txt");

        Sort sort = new Sort(file);
        
        sort.sortList();
        sort.print();
        
    }
}
