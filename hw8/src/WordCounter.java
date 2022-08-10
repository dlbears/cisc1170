import java.io.*;


public class WordCounter {
    private String[] words;
    public WordCounter(String text) {
        words = text.trim().split("\\s+"); // split words as delimited by whitespace and trim extra whitespace
    }

    public int getCount() {
        if (words.length == 1 && words[0].equals("")) return 0; 
        return words.length; // return length of words array
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] fileNames = args; // map main inputs to fileNames
        int result;
        String accString = "";
        File currentFile;
        FileReader reader;
        WordCounter counter;
        for (String name: fileNames) {
            currentFile = new File(name);
            if (currentFile.exists() && currentFile.canRead()) { // This implicitly handles the FileNotFoundException
                reader = new FileReader(currentFile);
                result = reader.read();
                while (result != -1) { // read all characters of file into string
                    accString += (char) result;
                    result = reader.read();
                }
                reader.close();
                counter = new WordCounter(accString); // Put file content into WordCounter
                System.out.printf("For path %s, the word count is %d \n", name, counter.getCount()); // output
                counter = null;
                accString = "";
            } else {
                System.out.println("Error: file " + name + " does not exits or is not readable, skipping"); // handle invalid file paths and files
            }
        }
    }
}