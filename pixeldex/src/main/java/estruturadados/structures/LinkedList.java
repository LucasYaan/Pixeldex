package estruturadados.structures;

public class LinkedList<T> {
    public Node head;
    public Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
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
    }

    public void remove(T value) {
        if (head == null) return;

        if (head.getValue().equals(value)) {
            head = head.getNext();
            if (head == null) tail = null;
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

    public void addAt(int index, T value) {
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (tail == null) tail = newNode;
            return;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current == null) return;
            current = current.getNext();
        }

        if (current != null) {
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            if (newNode.getNext() == null) tail = newNode;
        }
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

