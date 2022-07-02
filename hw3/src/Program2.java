import java.util.Scanner;

public class Program2 {

    //Static Stats Methods (In order of choice prompt, after line)
    public static void printCircleStats(double r) {
        double circumference = 2 * Math.PI * r, // 2πr
                area = Math.PI * Math.pow(r, 2); // πr^2
        System.out.printf("\nCircle Stats: \n\tRadius = %f\n\tCircumference = %f\n\tArea = %f\n\n", r, circumference, area);
    }
    public static void printSquareStats(double s) {
        double perimeter = s * 4, // 4s
                area = Math.pow(s, 2); // s^2
        System.out.printf("\nSquare Stats: \n\tSide = %f\n\tPerimeter = %f\n\tArea = %f\n\n", s, perimeter, area);
    }
    public static void printTriangleStats(double[] sides) {
        double perimeter = sides[0] + sides[1] + sides[2]; 
        double semi = perimeter / 2.0; // s = semiperimeter
        double area = Math.sqrt(semi * (semi - sides[0]) * (semi - sides[1]) * (semi - sides[2])); // Heron's Formula sqrt(s(s-a)(s-b)(s-c))
        System.out.printf("\nTriangle Stats: \n\tSides = [ %f, %f, %f ] \n\tPerimeter = %f\n\tArea = %f\n\n", sides[0], sides[1], sides[2], perimeter, area);
    }
    public static void printRectangleStats(double[] sides) {
        double perimeter = 2 * sides[0] + 2 * sides[1], // 2w + 2l
                area = sides[0] * sides[1]; // wl
        System.out.printf("\nRectangle Stats: \n\tWidth = %f\n\tHeight = %f\n\tPerimeter = %f\n\tArea = %f\n\n", sides[0], sides[1], perimeter, area);
    }
    // Regular Polygon meaning all sides are of the same length
    public static void printRegularPolygonStats(long nSides, double sideLength) { 
        double perimeter = nSides * sideLength, // p = n * s
                apothem = sideLength / (2 * Math.tan(Math.toRadians(180 / nSides))); // a = s / 2tan(180/n)
        double area = perimeter * apothem / 2; // pa / 2
        System.out.printf("\nRegular Polygon Stats:\n\tSides = %d\n\tLength = %f\n\tPerimeter = %f\n\tArea = %f\n\n", nSides, sideLength, perimeter, area);
    }

    public static void main(String[] args) throws IllegalArgumentException {
        // Init Scanner, and Double array to hold side lengths
        Scanner stdin = new Scanner(System.in);
        double sides[] = new double[]{};
        
        // Initial prompt and Input
        System.out.print("\nChoose a shape: \n\t0 - Line\n\t1 - Circle\n\t2 - Square\n\t3 - Triangle\n\t4 - Rectangle\n\t>= 5 - Regular n-Polygon\n\noption: ");
        long choice = stdin.nextLong();
        
        // Main IO Loop
        do {
            System.out.println();
            if (choice < 0) { // Invalid choices throw an error only when they happen as the first choice
                stdin.close();
                throw new IllegalArgumentException("Initial choice must be a non-negative long.");
            }

            // Switch based on user choice
            switch ((int) choice) {
                case 0: // Handle Lines
                    System.out.print("Length of line = "); 
                    System.out.printf("\nLine Stats:\n\t Length = %f\n\t Area = 0\n\n", stdin.nextDouble());
                    break;
                case 1: // Handle Circles
                    System.out.print("Radius of circle = ");
                    printCircleStats(stdin.nextDouble());
                    break;
                case 2: // Handle Squares
                    System.out.print("Side of square: ");
                    printSquareStats(stdin.nextDouble());
                    break;
                case 3: // Handle Triangle
                    sides = new double[3];
                    System.out.print("Side 1 of triangle: ");
                    sides[0] = stdin.nextDouble();
                    System.out.print("\nSide 2 of triangle: ");
                    sides[1] = stdin.nextDouble();
                    System.out.print("\nSide 3 of triangle: ");
                    sides[2] = stdin.nextDouble();
                    printTriangleStats(sides);
                    break;
                case 4: // Handle Rectangle
                    sides = new double[2];
                    System.out.print("Width of Rectangle: ");
                    sides[0] = stdin.nextDouble();
                    System.out.println("Height of Rectangle: ");
                    sides[1] = stdin.nextDouble();
                    printRectangleStats(sides);
                    break;
                default: // Handle Regular N-Polygons
                    System.out.print("Length of side = ");
                    printRegularPolygonStats(choice, stdin.nextDouble());
                    break;
            }
            // Prompt after initial choice with option for exit
            System.out.print("\nChoose a shape: \n\t< 0 - Exit\n\t0 - Line\n\t1 - Circle\n\t2 - Square\n\t3 - Triangle\n\t4 - Rectangle\n\t>= 5 - Regular n-Polygon\n\noption: ");
            choice = stdin.nextLong();
        } while(choice >= 0); // An invalid choice following at least one valid choice, exits the program.
        
        //Cleanup
        stdin.close();
    }
}
