
public class MyLinkedList<Type extends Comparable<Type>> {
    private class Node {
        public Type item;
        public Node next;

        @Override
        public String toString() {
            return item.toString();
        }
    }

    private Node first = null;
    private Node current = first;
    private Node previous = null;
    private int size = 0;
    public int comparisons = 0;

    public void addBefore(Type item) {
        Node NN = new Node();
        NN.item = item;
        size++;
        if (current != null) {
            if (previous != null) {
                previous.next = NN;
                NN.next = current;
                previous = NN;
            }
            //list is empty and previous is null
            else {
                first = previous = NN;
                NN.next = current;
                first.next = previous.next = current;
            }

            return;
        }

        //current is null
        if (previous != null) {
            previous.next = NN;
            previous = NN;
            return;
        }
        if (first == null) {
            first = NN;
            return;
        }
        Node temp = first;
        while (temp != null) {
            if (temp.next == null) {
                temp.next = NN;
                break;
            }
            temp = temp.next;
        }
    }

    public void addAfter(Type item) {
        if (current == null) {
            return;
        }
        size++;

        Node node = new Node();
        node.item = item;

        Node nn = current.next;
        //right subtree
        if (nn != null) {
            current.next = node;
            node.next = nn;
            return;
        }

        current.next = node;
    }

    public Type current() {
        return current == null ? null : current.item;
    }

    public Type first() {
        current = first;
        return this.first.item;
    }

    public Type next() {
        if (current != null) {
            previous = current;
            current = current.next;
            return current == null ? null : current();
        }
        return null;
    }
    public Type remove() {
        if (current == null) {
            return null;
        }
        size--;
        Type item = current();
        //head to remove
        if (previous == null && first == current) {
            current = current.next;
            first = current;
            return item;
        }

        //remove from middle or end
        previous.next = current.next;
        current = previous.next;

        return item;
    }
    public boolean contains(Type item) {
        comparisons++;
        if (size == 0)
            return false;

        Node temp = first;
        while (temp != null) {
            comparisons++;
            if (temp.item.compareTo(item) == 0)
                return true;
            temp = temp.next;
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void sort() {
        if (size <= 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            Node C = this.first;
            Node next = this.first.next;
            for (int j = 0; j < size - 1; j++) {
                if (C.item.compareTo(next.item) > 0) {
                    Type temp = C.item;
                    C.item = next.item;
                    next.item = temp;
                }
                C = next;
                next = next.next;
            }
        }
    }


    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        String strr = "[";
        Node temp = first;
        while (temp != null) {
            strr = strr.concat(temp.toString());
            strr = strr.concat(temp.next == null ? "]" : ", ");
            temp = temp.next;
        }
        return strr;
    }
}