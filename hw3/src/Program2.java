import java.util.Scanner;

public class Program2 {

    public static void printCircleStats(double r) {
        double circumference = 2 * Math.PI * r, 
                area = Math.PI * Math.pow(r, 2);
        System.out.printf("\nCircle Stats: \n\tRadius = %f\n\tCircumference = %f\n\tArea = %f\n\n", r, circumference, area);
    }
    public static void printSquareStats(double s) {
        double perimeter = s * 4, 
                area = Math.pow(s, 2); 
        System.out.printf("\nSquare Stats: \n\tSide = %f\n\tPerimeter = %f\n\tArea = %f\n\n", s, perimeter, area);
    }
    public static void printTriangleStats(double[] sides) {
        double perimeter = sides[0] + sides[1] + sides[2];
        double semi = perimeter / 2.0;
        double area = Math.sqrt(semi * (semi - sides[0]) * (semi - sides[1]) * (semi - sides[2])); // Heron's Formula
        System.out.printf("\nTriangle Stats: \n\tSides = [ %f, %f, %f ] \n\tPerimeter = %f\n\tArea = %f\n\n", sides[0], sides[1], sides[2], perimeter, area);
    }
    public static void printRectangleStats(double[] sides) {
        double perimeter = 2 * sides[0] + 2 * sides[1], 
                area = sides[0] * sides[1];
        System.out.printf("\nRectangle Stats: \n\tWidth = %f\n\tHeight = %f\n\tPerimeter = %f\n\tArea = %f\n\n", sides[0], sides[1], perimeter, area);
    }
    public static void printRegularPolygonStats(long nSides, double sideLength) {
        double perimeter = nSides * sideLength,
                apothem = sideLength / (2 * Math.tan(Math.toRadians(180 / nSides))); 
        double area = perimeter * apothem / 2;
        System.out.printf("\nRegular Polygon Stats:\n\tSides = %d\n\tLength = %f\n\tPerimeter = %f\n\tArea = %f\n\n", nSides, sideLength, perimeter, area);
    }

    public static void main(String[] args) throws IllegalArgumentException {
        Scanner stdin = new Scanner(System.in);
        double sides[] = new double[]{};
        
        System.out.print("\nChoose a shape: \n\t0 - Line\n\t1 - Circle\n\t2 - Square\n\t3 - Triangle\n\t4 - Rectangle\n\t>= 5 - Regular n-Polygon\n\noption: ");
        long choice = stdin.nextLong();
        do {
            System.out.println();
            if (choice < 0) {
                stdin.close();
                throw new IllegalArgumentException("Choice must be non-negative long.");
            }
            switch ((int) choice) {
                case 0:
                    System.out.print("Length of line = ");
                    System.out.printf("\nLine Stats:\n\t Length = %f\n\t Area = 0\n\n", stdin.nextDouble());
                    break;
                case 1:
                    System.out.print("Radius of circle = ");
                    printCircleStats(stdin.nextDouble());
                    break;
                case 2:
                    System.out.print("Side of Square: ");
                    printSquareStats(stdin.nextDouble());
                    break;
                case 3:
                    sides = new double[3];
                    System.out.print("Side 1 of triangle: ");
                    sides[0] = stdin.nextDouble();
                    System.out.print("\nSide 2 of triangle: ");
                    sides[1] = stdin.nextDouble();
                    System.out.print("\nSide 3 of triangle: ");
                    sides[2] = stdin.nextDouble();
                    printTriangleStats(sides);
                    break;
                case 4:
                    sides = new double[2];
                    System.out.print("Width of Rectangle: ");
                    sides[0] = stdin.nextDouble();
                    System.out.println("Height of Rectangle: ");
                    sides[1] = stdin.nextDouble();
                    printRectangleStats(sides);
                    break;
                default:
                    System.out.print("Length of side = ");
                    printRegularPolygonStats(choice, stdin.nextDouble());
                    break;
            }
            System.out.print("\nChoose a shape: \n\t< 0 - Exit\n\t0 - Line\n\t1 - Circle\n\t2 - Square\n\t3 - Triangle\n\t4 - Rectangle\n\t>= 5 - Regular n-Polygon\n\noption: ");
            choice = stdin.nextLong();
        } while(choice >= 0);
        stdin.close();
    }
}
