import java.io.*;
import java.util.*;

public class Calculator {
    private String[] tokens;
    public Calculator(String expression) {
        tokens = expression.split("\\s+"); // parse text by seperating tokens using whitespace and extra whitespace
        if (tokens.length < 3) { // handle illegal expressions
            throw new IllegalArgumentException("Expressions require three words, but the current expression is only length = " + tokens.length);
        }
    }

    //Used to Evaluate the expressions tokens and return an answer or throw an error
    public double eval() throws IllegalArgumentException { 
        double l = Double.parseDouble(tokens[0]), // parse left operand
               r = Double.parseDouble(tokens[2]); // parse right operand
        String op = tokens[1]; // set operator

        switch(op) { // evaluation logic
            case "+":
                return l + r;
            case "-":
                return l - r;
            case "/":
                return l / r;
            case "*":
                return l * r;
            default: // Invalid operator handling
                throw new IllegalArgumentException("Invalid Operator");

        }

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.print(">"); // line prompt
        String res = stdin.nextLine().trim(); // read first line and trim
        Calculator c;
        while (!res.equals("0")) { // keep reading while the a single 0 (exit case) is not read
            
            //Processing input and generating output
            try {
                c = new Calculator(res);
                System.out.printf("Result of expression %s = %.2f \n", res, c.eval());
            } catch (IllegalArgumentException e) {
                System.out.println("Error Illegal Args, try again or enter 0 to quit. \n");
            }

            // Reset prompt
            System.out.print(">");
            res = stdin.nextLine();
        }
        // Close scanner and exit
        System.out.print("Exiting...");
        stdin.close(); 
    }


}