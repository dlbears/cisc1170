import java.util.Scanner;

public class MonthDays extends Month {
    public static final int[] monthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private int year;

    public int getYear() { return year; }

    public MonthDays(int m, int y) {
        super(m);
        year = y;
    }
    public int getNumberOfDays() {
        // Determine if leap year
        boolean div100 = year % 100 == 0;
        boolean leapYear = (div100 && year % 400 == 0) || (!div100 && year % 4 == 0);
        
        if (leapYear && this.getMonthNumber() == 2) return 29; // Handle leap year/day for February
        else return monthLength[this.getMonthNumber() - 1]; // Default case
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        MonthDays current;
        int y, m;
    
        try {    
            while (true) { // Must provide an invalid input to break out of prompts
                System.out.print("Enter a month (1-12): ");
                m = Integer.parseInt(stdin.nextLine());
                System.out.print("Enter a year: ");
                y = Integer.parseInt(stdin.nextLine());
                current = new MonthDays(m, y);
                System.out.printf("%s %d has %d days\n\n", current.getMonthName(), current.getYear(), current.getNumberOfDays());
            }
        } catch (Exception e) { // Handle exit
            System.out.println("Invalid input, exiting gracefully...");
        } finally { // Cleanup
            current = null;
            stdin.close();
        }
    }
}
