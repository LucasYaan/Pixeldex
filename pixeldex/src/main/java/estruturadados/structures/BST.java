package estruturadados.structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BST<K, V> {
    private BSTNode root;
    private Comparator<K> comparator;
    private class BSTNode {
        K key;
        V value;
        BSTNode left, right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BST(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    // INSERT
    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }
    private BSTNode insertRec(BSTNode current, K key, V value) {
        if (current == null) return new BSTNode(key, value);

        if (comparator.compare(key, current.key) < 0) {
            current.left = insertRec(current.left, key, value);
        } else {
            current.right = insertRec(current.right, key, value);
        }
        return current;
    }

    // SEARCH
    public V search(K key) {
        BSTNode node = searchRec(root, key);
        return node != null ? node.value : null;
    }
    private BSTNode searchRec(BSTNode current, K key) {
        if (current == null) return null;

        int cmp = comparator.compare(key, current.key);
        if (cmp == 0) return current;
        return cmp < 0
          ? searchRec(current.left, key)
          : searchRec(current.right, key);
    }

    // REMOVE
    public void remove(K key) {
      root = removeRec(root, key);
    } 
    private BSTNode removeRec(BSTNode current, K key) {
      // 1. Encontrando o nó
      if (current == null) return null;

      int cmp = comparator.compare(key, current.key);

      if (cmp < 0) current.left = removeRec(current.left, key);
      else if (cmp > 0) current.right = removeRec(current.right, key);

      // 2. Removendo o nó
      else {
          // caso 1: nó folha (sem filhos)
          if (current.left == null && current.right == null) return null;

          // caso 2: um único filho
          if (current.left == null) return current.right;
          if (current.right == null) return current.left;

          // caso 3: dois filhos
          BSTNode temp = minValue(current.right);
          current.key = temp.key;
          current.value = temp.value;
          current.right = removeRec(current.right, temp.key);
      }

      return current;
    }
    private BSTNode minValue(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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

    // MÉTRICAS DA ÁRVORE
    public int height() {
      return heightRec(root);
    }
    private int heightRec(BSTNode node) {
      if (node == null) return -1; // altura de uma árvore vazia é -1
      int leftHeight = heightRec(node.left);
      int rightHeight = heightRec(node.right);
      return Math.max(leftHeight, rightHeight) + 1;
    }

    public int countNodes() {
      return countNodesRec(root);
    }
    private int countNodesRec(BSTNode node) {
      if (node == null) return 0;
      return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    public int countLeaves() {
      return countLeavesRec(root);
    }
    private int countLeavesRec(BSTNode node) {
      if (node == null) return 0;
      if (node.left == null && node.right == null) return 1;
      return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    public boolean isBST() {
      return isBSTRec(root, null, null);
      }
    private boolean isBSTRec(BSTNode node, K min, K max) {
      if (node == null) return true;
      if (min != null && comparator.compare(node.key, min) <= 0) return false;
      if (max != null && comparator.compare(node.key, max) >= 0) return false;

      return isBSTRec(node.left, min, node.key) && isBSTRec(node.right, node.key, max);
    }
    
    public List<V> range(K min, K max) {
      List<V> result = new ArrayList<>();
      rangeRec(root, min, max, result);
      return result;
    }
    private void rangeRec(BSTNode node, K min, K max, List<V> result) {
      if (node == null) return;

      if (comparator.compare(min, node.key) < 0)
        rangeRec(node.left, min, max, result);

      if (comparator.compare(min, node.key) <= 0 && comparator.compare(max, node.key) >= 0)
        result.add(node.value);

      if (comparator.compare(max, node.key) > 0)
        rangeRec(node.right, min, max, result);
    }
  }
