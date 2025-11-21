package estruturadados.io;

import java.io.Writer;

public class FileWriter {
    private Writer writer;

    public FileWriter(String filePath) {
        try {
            this.writer = new java.io.FileWriter(filePath);
        }
        catch (Exception e) {
            System.out.println("Error initializing FileWriter: " + e.getMessage());
        }
    }

    public void writeLine(String line) {
        try {
            writer.write(line + System.lineSeparator());
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
