package estruturadados.structures;

import java.util.ArrayList;


public class LinkedList<T> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }
    
    public boolean addAt(int index, T value) {
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (tail == null) tail = newNode;
            return true;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current == null) return false;
            current = current.getNext();
        }

        if (current != null) {
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            if (newNode.getNext() == null) tail = newNode;
        }
        size++;
        return true;
    }

    public void addFirst(T value) {
        addAt(0, value);
    }

    public void remove(T value) {
        if (head == null) return;

        if (head.getValue().equals(value)) {
            head = head.getNext();
            if (head == null) tail = null;
            size--;
            return;
        }

        Node current = head;
        while (current.getNext() != null && !current.getNext().getValue().equals(value)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            if (current.getNext() == null) tail = current;
        }
        size--;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size || head == null) return;

        if (index == 0) {
            head = head.getNext();
            if (head == null) tail = null;
            size--;
            return;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current.getNext() == null) return;
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            if (current.getNext() == null) tail = current;
            size--;
        }
    }

    public void removeFirst() {
        removeAt(0);
    }

    public void removeLast() {
        removeAt(size - 1);
    }

    public boolean move(int node, int position) {
        if (node < 0 || node >= size || position < 0 || position >= size || node == position) {
            return false;
        }

        Node current = head;
        T valueToMove = null;

        for (int i = 0; i < size; i++) {
            if (i == node) {
                valueToMove = current.getValue();
                break;
            }
            current = current.getNext();
        }

        if (valueToMove != null) {
            removeAt(node);
            addAt(position, valueToMove);
            return true;
        }

        return false;
    }

    public void reverse() {
        Node prev = null;
        Node current = head;
        tail = head;

        while (current != null) {
            Node nextNode = current.getNext();
            current.setNext(prev);
            prev = current;
            current = nextNode;
        }
        head = prev;
    }

    public LinkedList<T> unique() {
        LinkedList<T> uniqueList = new LinkedList<>();
        Node current = head;
        ArrayList<T> seenValues = new ArrayList<T>(size);

        while (current != null) {
            T currentValue = current.getValue();
            boolean isDuplicate = false;

            for (T seenValue : seenValues) {
                if (currentValue.equals(seenValue)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                uniqueList.add(currentValue);
                seenValues.add(currentValue);
            }

            current = current.getNext();
        }
            
        return uniqueList;
    }

    public LinkedList<T> slice(int start, int end) { // Slice inclusivo no início e no fim
        LinkedList<T> slicedList = new LinkedList<>();
        if (start < 0 || end > size || start >= end) return slicedList;

        Node current = head;
        for (int i = 0; i <= end; i++) {
            if (i >= start) {
                slicedList.add(current.getValue());
            }
            current = current.getNext();
        }
        return slicedList;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }


    public boolean isEmpty() {
        return head == null;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        public T getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

