import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;


public class Program3 {
    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("mm,dd,yyyy");
        File inFile = new File("../inputs/program3.input"),
             outFile = new File("../logs/program3.output");
        Scanner input = new Scanner(inFile);
        FileWriter output = new FileWriter(outFile);
        LocalDate now = LocalDate.now(), current;
        Period period;
        String line;
        String[] order;
        while (input.hasNextLine()) {
            line = input.nextLine();
            order = line.split(","); 
            current = LocalDate.of(
                Integer.parseInt(order[2]), 
                Integer.parseInt(order[0]), 
                Integer.parseInt(order[1])
            );
            period = Period.between(current, now);
            if (period.getYears() < 0) output.write("Invalid Birthday " + line.replace(',', '/'));
            output.write("For Birthday " + line.replace(',', '/') + " the age is " + period.getYears() + '\n');
        }
        input.close();
        output.close();
    }
}
