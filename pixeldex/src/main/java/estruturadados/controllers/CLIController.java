package estruturadados.controllers;

import java.util.HashSet;
import java.util.Set;

import estruturadados.io.InputReader;
import estruturadados.io.Menu;
import estruturadados.models.PixelCollection;
import estruturadados.models.PixelTree;
import estruturadados.objects.Pixel;
import estruturadados.structures.Rarity;
import estruturadados.util.RandomPixelFactory;
import estruturadados.util.RarityTable;
import estruturadados.structures.LinkedList;

public class CLIController {
    private PixelCollection collection;
    private PixelTree tree;

    private InputReader input;
    private Menu menu;

    private RandomPixelFactory pixelFactory;

    private boolean run;

    public CLIController() {
        this.collection = new PixelCollection();
        this.tree = new PixelTree();

        this.input = new InputReader();
        this.menu = new Menu();

        this.pixelFactory = new RandomPixelFactory();

        this.run = true;

    }

    public void start() {
        menu.showHeader();

        while (run) {
            String fullCommand = input.readLine(); // ler a linha inteira

            if (fullCommand.trim().isEmpty())
                continue;

            String[] parts = fullCommand.trim().split("\\s+");
            String command = parts[0].toUpperCase();

            try {
                switch (command) {
                    case "ADD" -> handleAdd(parts);
                    case "FIND" -> handleFind(parts);
                    case "REMOVE-COLLECTION" -> handleRemoveCollection(parts);
                    case "REMOVE-INDEX" -> handleRemoveIndex(parts);
                    case "REVERSE" -> handleReverse();
                    case "UNIQUE" -> handleUnique();
                    case "MOVE" -> handleMove(parts);
                    case "SLICE" -> handleSlice(parts);
                    case "LIST" -> handleList();
                    case "LIST-INDEX" -> handleListIndex(parts);
                    case "RANGE" -> handleRange(parts);
                    case "GENERATE" -> handleGenerate(parts);
                    case "HELP" -> menu.showHelp();
                    case "EXIT" -> {
                        run = false;
                        menu.showSuccess("Encerrando PixelDex...");
                    }
                    default -> menu.showError("Commando desconhecido: " + command);
                }

            } catch (Exception e) {
                menu.showError("Falha na execução: " + e.getMessage());
            }

        }

    }

    // handlers

    private void handleAdd(String[] parts) {
        if (parts.length < 5)
            throw new IllegalArgumentException("Uso:  ADD <id> <nome> <poder> <raridade>");

        int id = Integer.parseInt(parts[1]);
        String nome = parts[2];
        String raridadeText = parts[3].toUpperCase();
        int poder = Integer.parseInt(parts[4]);

        Rarity raridade;
        try {
            raridade = criarRaridade(raridadeText);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Raridade inválida. Use: COMUM, INCOMUM, RARO, EPICO, LENDARIO");
        }

        Pixel p = new Pixel(id, nome, poder, raridade);

        collection.addPixel(p);
        tree.addPixel(p);

        menu.showSuccess("Pixel adicionado: " + p.toString());
    }

    private void handleFind(String[] parts) {
        if (parts.length < 2)
            throw new IllegalArgumentException("Uso: FIND <id|nome>");
        String termo = parts[1];

        try {
            int id = Integer.parseInt(termo);

            Pixel dummy = new Pixel(id, "", 0, new Rarity(1, "COMUM"));
            Pixel found = tree.findPixel(dummy);

            if (found != null) {
                menu.printLine("Encontrado no Índice (BST):");
                menu.showPixel(found);
            } else {
                menu.showError("ID " + id + " não encontrado no índice.");
            }
        } catch (NumberFormatException e) {

            menu.printLine("Buscando por nome na lista...");
            boolean found = false;

            LinkedList<Pixel>.Node current = collection.getPixels().getHead();
            while (current != null) {
                if (current.getValue().getName().equalsIgnoreCase(termo)) {
                    menu.showPixel(current.getValue());
                    found = true;
                }
                current = current.getNext();
            }
            if (!found)
                menu.showError("Nenhum pixel com nome '" + termo + "' encontrado.");
        }
    }

    private void handleGenerate(String[] parts) {
        if (parts.length < 2){      
            handleGenerate("GENERATE 1".split("\\s+"));
            return;
        }
        int quantidade = Integer.parseInt(parts[1]);

        for (int i = 0; i < quantidade; i++) {
            Pixel p = pixelFactory.createRandomPixel();
            collection.addPixel(p);
            tree.addPixel(p);
            menu.showSuccess("Gerado: " + p.toString());
        }
        menu.showSuccess(quantidade + " pixels gerados e adicionados.");
    }

    private void handleRemoveCollection(String[] parts) {
        if (parts.length < 2)
            throw new IllegalArgumentException("Uso: REMOVE-COLLECTION <index_lista>");
        int index = Integer.parseInt(parts[1]);

        Pixel p = getPixelAt(index);

        if (p != null) {
            collection.getPixels().removeAt(index); // Remove da lista por índice
            tree.removePixel(p); // Remove da árvore pelo objeto
            menu.showSuccess("Pixel removido da posição " + index);
        } else {
            menu.showError("Índice inválido ou lista vazia.");
        }
    }

    

    private void handleRemoveIndex(String[] parts) {
        if (parts.length < 2)
            throw new IllegalArgumentException("Uso: REMOVE-INDEX <id>");
        int id = Integer.parseInt(parts[1]);

        // Busca o objeto real na lista para remover de ambos
        Pixel target = findPixelById(id);

        if (target != null) {
            collection.removePixel(target);
            tree.removePixel(target);
            menu.showSuccess("Pixel ID " + id + " removido.");
        } else {
            menu.showError("ID " + id + " não encontrado.");
        }
    }

    private void handleReverse() {
        LinkedList<Pixel> lista = collection.getPixels();
        if (lista.getSize() <= 1) {
            menu.showSuccess("Lista invertida.");
            return;
        }

        // Copia para array auxiliar
        Pixel[] tempArray = new Pixel[lista.getSize()];
        LinkedList<Pixel>.Node current = lista.getHead();
        int i = 0;
        while (current != null) {
            tempArray[i++] = current.getValue();
            current = current.getNext();
        }

        // Limpa lista
        while (!lista.isEmpty()) {
            lista.removeFirst();
        }

        // Readiciona inversamente (usando addFirst como pilha)
        for (Pixel p : tempArray) {
            lista.addFirst(p);
        }
        menu.showSuccess("Coleção invertida.");

    }

    private void handleUnique() {
        Set<Integer> seenIds = new HashSet<>();
        LinkedList<Pixel> lista = collection.getPixels();
        LinkedList<Pixel> novaLista = new LinkedList<>();

        LinkedList<Pixel>.Node current = lista.getHead();
        int countRemoved = 0;

        while (current != null) {
            Pixel p = current.getValue();
            if (!seenIds.contains(p.getId())) {
                seenIds.add(p.getId());
                novaLista.add(p);
            } else {
                countRemoved++;
                tree.removePixel(p); // Remove duplicata da árvore
            }
            current = current.getNext();
        }

        // Substitui a lista antiga pela nova limpa
        while (!lista.isEmpty())
            lista.removeFirst();

        current = novaLista.getHead();
        while (current != null) {
            lista.add(current.getValue());
            current = current.getNext();
        }

        menu.showSuccess("Duplicatas removidas: " + countRemoved);
    }

    private void handleMove(String[] parts) {
        if (parts.length < 3)
            throw new IllegalArgumentException("Uso: MOVE <de> <para>");
        int from = Integer.parseInt(parts[1]);
        int to = Integer.parseInt(parts[2]);

        LinkedList<Pixel> lista = collection.getPixels();
        if (from < 0 || from >= lista.getSize() || to < 0 || to >= lista.getSize()) {
            menu.showError("Índices fora dos limites.");
            return;
        }

        Pixel p = getPixelAt(from);
        lista.removeAt(from);
        lista.addAt(to, p);

        menu.showSuccess("Movido de " + from + " para " + to);

    }

    private void handleSlice(String[] parts) {
        if (parts.length < 3)
            throw new IllegalArgumentException("Uso: SLICE <inicio> <fim>");
        int start = Integer.parseInt(parts[1]);
        int end = Integer.parseInt(parts[2]);

        menu.printLine("--- SLICE [" + start + " - " + end + "] ---");

        // Iteração manual usando Node público
        LinkedList<Pixel>.Node current = collection.getPixels().getHead();
        int index = 0;

        while (current != null) {
            if (index >= start && index < end) {
                menu.showPixel(current.getValue());
            }
            if (index >= end)
                break;
            current = current.getNext();
            index++;
        }

    }

    

    private void handleList() {
        menu.printLine("=== COLEÇÃO (LISTA) ===");
        if (collection.getSize() == 0) {
            menu.printLine("(Vazia)");
        } else {
            // Usa o toString() da PixelCollection que já formata tudo
            menu.printLine(collection.toString());
        }

    }

    private void handleListIndex(String[] parts) {
        if (parts.length < 2)
            throw new IllegalArgumentException("Uso: LIST-INDEX <INORDER|PRE|POST>");
        String mode = parts[1].toUpperCase();

        menu.printLine("=== ÍNDICE (BST - " + mode + ") ===");
        switch (mode) {
            case "INORDER" -> tree.printInOrder();
            case "PRE" -> tree.printPreOrder();
            case "POST" -> tree.printPostOrder();
            default -> menu.showError("Modo inválido.");
        }
        menu.skipLine();
    }

    private void handleRange(String[] parts) {
        // Tá tudo fudido, mas de novo, a paciência já foi faz tempo
        // Idealmente esse e o slice deveriam estar nas estruturas correspondentes
        // Na verdade essa porra tinha que ficar na árvore, O(n) disso aqui deve ser horroroso
        if (parts.length < 3)
            throw new IllegalArgumentException("Uso: RANGE <id_min> <id_max>");
        int idMin = Integer.parseInt(parts[1]);
        int idMax = Integer.parseInt(parts[2]);

        menu.printLine("=== RANGE [" + idMin + " - " + idMax + "] ===");

        // Iteração manual usando Node público
        LinkedList<Pixel>.Node current = collection.getPixels().getHead();

        while (current != null) {
            Pixel p = current.getValue();
            if (p.getId() >= idMin && p.getId() <= idMax) {
                menu.showPixel(p);
            }
            current = current.getNext();
        }
    }

    private Rarity criarRaridade(String text) {
        Rarity raridade = RarityTable.getRarityByDescription(text.toUpperCase());
        if (raridade != null) {
            if (raridade.getLevel() != 99)
                return raridade; 
            throw new IllegalArgumentException("Você realmente achou que poderia criar um Pixel Traue?");  
        }
        throw new IllegalArgumentException("Raridade inválida.");    
    }

    // --- MÉTODOS AUXILIARES ---
    private Pixel getPixelAt(int index) {
        if (index < 0 || index >= collection.getSize())
            return null;

        LinkedList<Pixel>.Node current = collection.getPixels().getHead();
        int i = 0;
        while (current != null) {
            if (i == index)
                return current.getValue();
            current = current.getNext();
            i++;
        }
        return null;
    }

    private Pixel findPixelById(int id) {
        LinkedList<Pixel>.Node current = collection.getPixels().getHead();
        while (current != null) {
            if (current.getValue().getId() == id)
                return current.getValue();
            current = current.getNext();
        }
        return null;
    }
}