import java.util.Scanner;

public class CircleStat {
    public static void main(String args[]) {
      Scanner s = new Scanner(System.in);
      System.out.print("radius = ");
      int r = s.nextInt();
      System.out.print("\nWhen the radius is " + r + " the circumference is " + (2 * Math.PI * r) + " and the area is " + (Math.PI * (r * r)));
    }
}