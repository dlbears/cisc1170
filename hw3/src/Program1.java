import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Program1 {
    
    public static long factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static long sumOfSquares(int n) {
        if (n == 1) {
            return 1;
        } else {
            return (long) Math.pow(n, 2) + sumOfSquares(n - 1);
        }
        
    }

    public static long fibonacci(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return fibonacci(n - 2) + fibonacci(n - 1);
        }
        
    }

    public static final File outputFile = new File("../logs/program1.output"),
                             inputFile = new File("../inputs/program1.input");

    public static FileReader inputReader;
    public static FileWriter outputWriter;

    public static void initIO(boolean reader, boolean writer) throws IOException, FileNotFoundException, IllegalArgumentException {
        if (!inputFile.exists()) throw new IllegalArgumentException("Input file named: program1.input is required.");

        if (outputFile.exists()) {
            outputFile.delete();
            outputFile.createNewFile();
        } else {
            outputFile.createNewFile();
        }

        if (reader) {
            inputReader = new FileReader(inputFile);
        } 

        if (writer) {
            outputWriter = new FileWriter(outputFile);
        }
    }

    public static int[] readInts(Reader r) throws IOException {
        int[] ns = new int[]{};
        String cl = "";
        for (int charCode = r.read(); charCode != -1; charCode = r.read()) {
            char c = (char) charCode;
            switch (c) {
                case '\n':
                    ns = Arrays.copyOf(ns, ns.length + 1);
                    ns[ns.length - 1] = Integer.parseInt(cl);
                    cl = "";
                    break;
                default:
                    cl += c;
            }
        }
        if (cl.length() > 0) {
            ns = Arrays.copyOf(ns, ns.length + 1);
            ns[ns.length - 1] = Integer.parseInt(cl);
            cl = "";
        }
        return ns;
    }

    public static void writeString(String s) throws IOException { outputWriter.write(s); }
    public static void main(String[] args) throws IOException, FileNotFoundException, IllegalArgumentException {
        initIO(true, true);
        int[] ns = readInts(inputReader);
        inputReader.close();

        for (int n: ns) {
            long nFact = factorial(n),
                 nSumSquares = sumOfSquares(n),
                 iFib;

            String outputStatic = String.format("The factorial of %d, is %d.\n", n, nFact) 
                            + String.format("The sum of squares is %d.\n", nSumSquares)
                            + String.format("First n = %d fibonacci numbers: ", n),
                   outputAccumulator = "";

            for (int i = 1; i <= n; i++) {
                iFib = fibonacci(i);
                outputAccumulator += (String.format(i == n ? "%d\n" : "%d, ", iFib));
            }    

            writeString(outputStatic + outputAccumulator + '\n');
        }

        outputWriter.close();

    }
}
