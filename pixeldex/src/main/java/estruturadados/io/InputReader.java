package estruturadados.io;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    public char readChar() {
        try {
            String line = scanner.nextLine();
            if (line.length() > 0) {
                return line.charAt(0);
            } else {
                throw new Exception("No character input");
            }
        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
            return ' ';
        }
    }
}
