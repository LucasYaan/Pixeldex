package estruturadados.io;

public abstract class Menu {

    public static void printLine(String line) {
        printLine(line, true);
    }

    public static void printLine(String line, boolean skipLine) {
        System.out.println(line);
        if (skipLine) {
            System.out.println();
        }
    }

    public static void skipLine() {
        System.out.println();
    }
}
