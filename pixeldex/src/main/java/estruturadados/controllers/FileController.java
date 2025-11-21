package estruturadados.controllers;

import estruturadados.io.FileReader;
import estruturadados.io.FileWriter;


public class FileController {
    private FileReader fileReader;
    private FileWriter fileWriter;

    public FileController(String readFilePath, String writeFilePath) {
        try {
            this.fileReader = new FileReader(readFilePath);
            this.fileWriter = new FileWriter(writeFilePath);
        } catch (Exception e) {
            System.out.println("Error initializing FileController: " + e.getMessage());
        }
    }

    public FileController(String filePath) {
        try {
            this.fileReader = new FileReader(filePath);
            this.fileWriter = new FileWriter(filePath);
        } catch (Exception e) {
            System.out.println("Error initializing FileController: " + e.getMessage());
        }
    }

    public void saveCollection(String data) {
        // Implementar parsing de PixelCollection para String

        try {
            fileWriter.writeLine(data);
        } catch (Exception e) {
            System.out.println("Error saving collection to file: " + e.getMessage());
        }
    }

    public void loadCollection() {
        return; // Implementar leitura e parsing de arquivo para PixelCollection
    }
}
