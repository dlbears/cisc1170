import java.util.Scanner;

public class GradeCalculator {
    public static void main(String args[]) {
      Scanner s = new Scanner(System.in);
      System.out.print("Homework = ");
      int hw = s.nextInt();
      System.out.print("\nCodeLab = ");
      int cl = s.nextInt();
      System.out.print("\nMidterm1 = ");
      int m1 = s.nextInt();
      System.out.print("\nMidterm2 = ");
      int m2 = s.nextInt();
      System.out.print("\nFinal = ");
      int fl = s.nextInt();
      System.out.print("\nThe total score is " + (hw + cl + m1 + m2 + fl));
    }
}