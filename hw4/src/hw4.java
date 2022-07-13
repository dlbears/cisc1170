import java.sql.Time;

public class hw4 {
    // 1.
    public static void basicArray() {
        int size = 50;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = i + 1; // Set the elements to range from 1 to 50
            nums[i] += 10; // Add 10 to each element
            System.out.printf("nums[%d] = %d\n", i, nums[i]); // Output
        }
    }    

    // 2.
    public static void printElements(String[] els) {
        int counter = 1;
        for (String el: els) {
            if (el instanceof String) { // Iterate through Strings in array and print output
                System.out.printf("Element %d in String array is: \"%s\" with length of %d\n", counter, el, el.length());
                counter++;
            } else {
                throw new IllegalArgumentException( // Should never run...
                    String.format(
                        "All the elements in the String array must be of type String. Element %d is not a String.", 
                        counter
                    )
                );
            }
        }
        System.out.println();
    }

    // 3.
    public static void printElements(int[][] els) {
        int colCounter, rowCounter = 0;
        Integer cell; // used to type check int

        for (int[] row: els) { // Iterate through rows
            colCounter = 0;
            for (int col: row) { // Iterate through columsn per row
                cell = Integer.valueOf(col); // Box int 
                if (cell instanceof Integer) { // Type check
                    System.out.printf(colCounter == 0 ? "%d" : " %d", cell); // Output
                    colCounter++; // Track columns
                } else {
                    throw new IllegalArgumentException( // Should never run...
                        String.format(
                            "All the elements in the Integer array must be of type Integer. els[%d][%d] is not a Integer.", 
                            rowCounter,
                            colCounter
                        )
                    );
                }  
            }
            System.out.println();
            rowCounter++; // Track rows
        }
        System.out.println();
    }

    // 4.
    public static void printElements(Time[] els) {
        int counter = 1;
        for (Time el: els) {
            if (el instanceof Time) { // Type check
                System.out.printf("Element %d in Time array is: %s \n", counter, el.toString()); // output
                counter++; // track index
            } else {
                throw new IllegalArgumentException( // Should never run...
                    String.format(
                        "All the elements in the Time array must be of type Time. Element %d is not.", 
                        counter
                    )
                );
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 5. 
        String[] ten = new String[] { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };
        printElements(ten);

        // 6.
        Time[] three = new Time[3];
        three[0] = Time.valueOf("14:21:00");
        three[1] = Time.valueOf("01:00:01");
        three[2] = Time.valueOf("00:50:23");
        printElements(three);

        // 7.
        int[][] values = new int[10][20];
        for (int r = 1; r <= 10; r++) {
            for (int c = 1; c <= 20; c++) {
                values[r - 1][c - 1] = r * c;
            }
        }
        System.out.printf("Number of rows for 2D Array = %d\n", values.length);
        System.out.printf("Number of columns for 2D Array = %d\n", values[0].length);
        printElements(values);
    }
}
