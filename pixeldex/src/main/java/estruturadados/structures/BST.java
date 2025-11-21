package estruturadados.structures;

public class BST {
    private BSTNode root;

    public BST() {
        this.root = null;
    }
    
    // INSERT
    public void insert(int value) {
      root = insertRec(root, value);
    }

    private BSTNode insertRec(BSTNode current, int value) {
      if (current == null) {
          return new BSTNode(value);
      }
      if (value < current.value) {
        current.left = insertRec(current.left, value);
      }
      else if (value > current.value) {
        current.right = insertRec(current.right, value);
      }

      return current;
    }

    // SEARCH
    public boolean search(int key) {
      return searchRec(root, key);
    }

    private boolean searchRec(BSTNode current, int value) {
      if (current == null) return false;
      if (value == current.value) return true;
      return value < current.value
        ? searchRec(current.left, value)
        : searchRec(current.right, value);
    }

    // REMOVE
    public void remove(int key) {
      root = removeRec(root, key);
    } 

    private BSTNode removeRec(BSTNode current, int value) {
      // 1. Encontrando o nó
      if (current == null) {
        return null;
      }
      if (value < current.value) {
        current.left = removeRec(current.left, value);
      }
      else if (value > current.value) {
        current.right = removeRec(current.right, value);
      }

      // 2. Removendo o nó
      else {
          // caso 1: nó folha (sem filhos)
          if (current.left == null && current.right == null) {
              return null;
          }

          // caso 2: um único filho
          if (current.left == null) return current.right;
          if (current.right == null) return current.left;

          // caso 3: dois filhos
          int menorValor = minValue(current.right);
          current.value = menorValor;
          current.right = removeRec(current.right, menorValor);
      }

      return current;
    }

    private int minValue(BSTNode node) {
        int min = node.value;
        while (node.left != null) {
            min = node.left.value;
            node = node.left;
        }
        return min;
    }

    // INORDER TRAVERSAL
    public void inOrder() {
      inOrderRec(root);
    }
    private void inOrderRec(BSTNode node) {
      if (node != null) {
        inOrderRec(node.left);
        System.out.print(node.value + " ");
        inOrderRec(node.right);
      }
    }
    // PREORFDER TRAVERSAL
    public void preOrder() {
      preOrderRec(root);
    }
    private void preOrderRec(BSTNode node) {
      if (node != null) {
        System.out.print(node.value + " ");
        preOrderRec(node.left);
        preOrderRec(node.right);
      }
    }
    // POSTORDER TRAVERSAL
    public void postOrder() {
      postOrderRec(root);
    }
    private void postOrderRec(BSTNode node) {
      if (node != null) {
        postOrderRec(node.left);
        postOrderRec(node.right);
        System.out.print(node.value + " ");
      }
    }

    public int height() {
      return heightRec(root);
    }
    private int heightRec(BSTNode node) {
      if (node == null) {
        return -1; // altura de uma árvore vazia é -1
      }
      int leftHeight = heightRec(node.left);
      int rightHeight = heightRec(node.right);
      return Math.max(leftHeight, rightHeight) + 1;
    }

    public int countNodes() {
      return countNodesRec(root);
    }
    private int countNodesRec(BSTNode node) {
      if (node == null) {
        return 0;
      }
      return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }
  }
