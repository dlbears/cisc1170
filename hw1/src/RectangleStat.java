import java.util.Scanner;

public class RectangleStat {
    public static void main(String args[]) {
      Scanner s = new Scanner(System.in);
      System.out.print("width = ");
      int wd = s.nextInt();
      System.out.print("\nlength = ");
      int ln = s.nextInt();
      System.out.print("\nWhen one side is " + wd + " and the other side is " + ln + ", the area is " + (ln * wd) + " and the perimeter is " + (2*ln + 2*wd));
    }
}