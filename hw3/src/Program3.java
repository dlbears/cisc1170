import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;


public class Program3 {
    public static void main(String[] args) throws IOException, NumberFormatException, FileNotFoundException {
        //Initialize
        File inFile = new File("../inputs/program3.input"), // Files Used for IO
             outFile = new File("../logs/program3.output");
        Scanner input = new Scanner(inFile); // For reading
        FileWriter output = new FileWriter(outFile); // For writing
        LocalDate now = LocalDate.now(), current; // init current date, and declare temporary date 
        Period period; // used for constructing a time period between two dates
        String line; // temp for current line read
        String[] order; // temp for columns line is split into

        //Main IO loop
        while (input.hasNextLine()) {
            // Read Input
            line = input.nextLine(); // Read line
            order = line.split(","); // Split CSV by comma
            current = LocalDate.of( // Map and Parse string fields to LocalDate object 
                Integer.parseInt(order[2]), // Year
                Integer.parseInt(order[0]), // Month
                Integer.parseInt(order[1])  // Day
            );

            // Calculation
            period = Period.between(current, now); // Construct timeline, using .getYears() to get the age
            
            // Write Output
            if (period.getYears() < 0) { // Handle invalid birthdays
                output.write("Invalid Birthday " + line.replace(',', '/'));
            }
            output.write("For Birthday " + line.replace(',', '/') + " the age is " + period.getYears() + '\n');
        }
        
        //Cleanup
        input.close();
        output.close();
    }
}
