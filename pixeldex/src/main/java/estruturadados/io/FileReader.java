package estruturadados.io;

import java.io.BufferedReader;

public class FileReader {
    private BufferedReader reader;

    public FileReader(String filePath) {
        try {
            this.reader = new BufferedReader(new java.io.FileReader(filePath));
        } catch (Exception e) {
            System.out.println("Error initializing FileReader: " + e.getMessage());
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return null;
        }
    }
}
