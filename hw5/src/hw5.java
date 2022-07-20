import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class hw5 {
    
    // Output formats (opted for spaces instead of tabs, since tabs may be variably spaced)
    public static final String header = "Hour        Distance Traveled\n-----------------------------\n",
                               lineTemplate = "  %d                %d\n"; 


    // Regex Patterns for...
    public static final String CSV = "\\s*,\\s*", // CSV 
                            OR = "|", 
                            W = "\\s+"; // Whitespace (used to seperate lines/rows)

    public static int distance(int mph, int hours) {
        return mph * hours; // distance = mph * hours
    }

    public static void saveAsFile(String vehicle, String output) throws IOException {
        // Init File and Writer
        File outputFile = new File("../output/" + vehicle + ".txt"); 
        FileWriter outputWriter = new FileWriter(outputFile);

        // Write Output...
        outputWriter.write(output);

        // Cleanup
        outputWriter.close();
    }
    public static void main(String[] args) throws IOException, FileNotFoundException, IllegalArgumentException, IllegalStateException {
        // Init Input file and Scanner
        File input = new File("../input/input.csv");
        Scanner cells = new Scanner(input);

        if (cells.hasNextLine()) {
            // Column labels for error handling
            String[] columnLabels = cells.nextLine()
                                         .split(CSV);

            // Init variables and set delimiter
            String vehicle = "", output = "";
            int mph = 0, hours = 0;
            int numColumns = columnLabels.length;
            cells.useDelimiter(CSV + OR + W);

            // Input Validation: Handle not enough data columns
            if (numColumns < 3) {
                cells.close(); // Cleanup and crash
                throw new IllegalArgumentException("CSV must have exactly three columns. (Vehicle Type, Speed, and Time");
            }

            // Loop through rows
            while (cells.hasNext()) {
                // Reset/Init output with header...
                output = header;
                // Loop through columns
                for (int col = 0; col < numColumns; col++) {
                    switch (col) {
                        case 0:  // Handle first column (Vehicle type)
                            vehicle = cells.next();
                            break;
                        case 1: // Handle second column (Speed)
                            mph = Integer.parseInt(cells.next()); // Parse value
                            if (mph < 0) throw new IllegalArgumentException(columnLabels[1] + " must be non-negative."); // Input Validation: non negative speed
                            break;
                        case 2: // Handle third column (time)
                            hours = Integer.parseInt(cells.next()); // Parse value
                            if (hours < 1) throw new IllegalArgumentException(columnLabels[2] + " must be grater than or equal to 1."); // Input Validation: positive time
                            break;
                        default: // Input validation: too many columns
                            throw new IllegalStateException("Unhandled column " + col);
                    }   
                }

                // Iteratively generate output for current row
                for (int h = 1; h <= hours; h++) {
                    output += String.format(lineTemplate, h, distance(mph, h));
                }
                
                // Output for current row
                saveAsFile(vehicle, output);
            }

            // Cleanup
            cells.close();
        }
    }
}
