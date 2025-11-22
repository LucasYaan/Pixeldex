package estruturadados.io;

import estruturadados.objects.Pixel;

public class Menu {

    public void showHeader() {
        System.out.println("=======================================");
        System.out.println("             PIXELDEX - CLI            ");
        System.out.println("=======================================");
        System.out.println("Digite HELP para comandos.");
        System.out.println();
    }

    public void printLine(String line) {
        printLine(line, true);
    }

    public void printLine(String line, boolean skipLine) {
        System.out.println(line);
        if (skipLine) {
            System.out.println();
        }
    }

    public void skipLine() {
        System.out.println();
    }

    public void showHelp() {
        System.out.println("  COMANDOS DISPONÍVEIS: ");
        System.out.println("  ADD <id> <nome> <raridade> <poder>");
        System.out.println("  FIND <nome|id>");
        System.out.println("  REMOVE-COLLECTION <index>   (Remove da lista pelo índice)");
        System.out.println("  REMOVE-INDEX <id>           (Remove do índice pelo ID)");
        System.out.println("  REVERSE                     (Inverte a lista)");
        System.out.println("  UNIQUE                      (Remove duplicatas)");
        System.out.println("  MOVE <fromIndex> <toIndex>");
        System.out.println("  SLICE <from> <to>");
        System.out.println("  LIST                        (Lista completa)");
        System.out.println("  LIST-INDEX <INORDER|PRE|POST>");
        System.out.println("  RANGE <id_min> <id_max>");
        System.out.println("  GENERATE <quantidade>       (Gera pixels aleatórios)");
        System.out.println("  EXIT");

    }

    public void showError(String msg) {
        System.out.println("[ERRO]" + msg);
    }

    public void showSuccess(String msg) {
        System.out.println("[OK]" + msg);
    }

    public void showPixel(Pixel p) {
        System.out.println(" >>" + p.toString());
    }

}
