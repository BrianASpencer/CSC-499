/*
Name: Brian Spencer
Date: Oct. 30, 2019
Purpose: Sorts the contents of a file in ascending or descending order by the length 
         of the name, then alphabetically.
 */

package Sort;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sort {

    private String[] names;

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

        sc.close();

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

        sc.close();
    }

    // method to call sorting methods for length and alphabetically
    private void sortList(String key) {
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < names.length - 1; j++) {
                if (names[j].length() == names[j + 1].length()) {
                    int result = compareTo(names[j], names[j + 1], 1);
                    if (key.equals("desc")) {
                        if (result < 0) {
                            swap(names[j], names[j + 1], j);
                        }
                    } else {
                        if (result > 0) {
                            swap(names[j], names[j + 1], j);
                        }
                    }
                } else {
                    int result = compareTo(names[j], names[j + 1], 0);
                    if (key.equals("desc")) {
                        if (result < 0) {
                            swap(names[j], names[j + 1], j);
                        } 
                    } else {
                        if (result > 0) {
                            swap(names[j], names[j + 1], j);
                        }
                    }
                }
            }
        }
    }

    // a simple swap method that will swap the two Strings
    // at the inputted index and the element after that
    private void swap(String first, String next, int index) {
        names[index] = next + "";
        names[index + 1] = first + "";
    }

    // key == 1 for alphabetical
    // key == 0 for length
    private int compareTo(String first, String next, int key) {
        if (key == 1) {
            return first.compareToIgnoreCase(next);
        } else {
            return first.length() - next.length();
        }
    }

    // creates a String that will be passed to
    // be written to a file
    private void write(String key) {
        String s = "";
        int cnt = 0;
        for (String name : names) {
            if (cnt != names.length - 1) {
                s += name + "\n";
            } else {
                s += name;
            }
            cnt++;
        }
        writeUsingFiles(s, key);
    }

    // writes to a file based on the key
    private void writeUsingFiles(String data, String key) {
        String path = System.getProperty("user.dir");
        if (key.equals("desc")) {
            path = path + "//DescOutput.txt";
        } else {
            path = path + "//Output.txt";
        }
        try {
            Files.write(Paths.get(path), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        try {
            String path = System.getProperty("user.dir");

            File file = new File(path + "//" + args[0]);

            Sort sort = new Sort(file);

            String key = "";

            if (args.length > 1) {
                key = args[1] + "";
            }

            sort.sortList(key);
            sort.write(key);

            System.exit(0);
        } catch (Exception e) {
            System.exit(1);
        }
    }
}