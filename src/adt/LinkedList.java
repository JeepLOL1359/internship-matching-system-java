package adt;

import java.util.Comparator;
import java.util.Iterator;

/**
 * LinkedList.java A class that implements the ADT List using a chain of nodes,
 * with the node implemented as an inner class.
 * 
 * @author kitki
 */

/*
Can traverse forward and backward
Can do fast insert/remove at *both ends*
*/
public class LinkedList<T> implements ListInterface<T>, Iterable<T> {

    private Node head; // First node
    private Node tail; // Last node
    private int numberOfEntries;

    public LinkedList() {
        clear();
    }

    @Override
    public final void clear() {
        head = null;
        tail = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        if (newPosition < 1 || newPosition > numberOfEntries + 1) {
            return false;
        }

        Node newNode = new Node(newEntry);

        if (newPosition == 1) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (newPosition == numberOfEntries + 1) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node nodeAfter = getNodeAt(newPosition);
            Node nodeBefore = nodeAfter.prev;

            newNode.prev = nodeBefore;
            newNode.next = nodeAfter;

            nodeBefore.next = newNode;
            nodeAfter.prev = newNode;
        }

        numberOfEntries++;
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) return null;

        T result;
        if (givenPosition == 1) {
            result = head.data;
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (givenPosition == numberOfEntries) {
            result = tail.data;
            tail = tail.prev;
            if (tail != null) tail.next = null;
            else head = null;
        } else {
            Node nodeToRemove = getNodeAt(givenPosition);
            Node before = nodeToRemove.prev;
            Node after = nodeToRemove.next;

            before.next = after;
            after.prev = before;

            result = nodeToRemove.data;
        }

        numberOfEntries--;
        return result;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) return false;

        Node current = getNodeAt(givenPosition);
        current.data = newEntry;
        return true;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) return null;

        return getNodeAt(givenPosition).data;
    }

    @Override
    public boolean contains(T anEntry) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(anEntry)) return true;
            current = current.next;
        }
        return false;
    }

    // ==========================
    // Utility Method
    // ==========================
    
    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append("\n");
            current = current.next;
        }
        return sb.toString();
    }
    
    // ==========================
    // Advanced Linked List Method (Skip sorted double linked list)
    // ==========================
    private Node getNodeAt(int position) {
        Node current;
        if (position <= numberOfEntries / 2) {
            current = head;
            for (int i = 1; i < position; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = numberOfEntries; i > position; i--) {
                current = current.prev;
            }
        }
        return current;
    }
    
    @Override
    //To maintain alphabetical order based on compareTo():
    public boolean insertSorted(T newEntry) {
        Node newNode = new Node(newEntry);

        if (head == null || ((Comparable<T>) newEntry).compareTo(head.data) < 0) {
            // Insert at the head
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else {
            Node current = head;
            while (current.next != null &&
                   ((Comparable<T>) newEntry).compareTo(current.next.data) > 0) {
                current = current.next;
            }

            newNode.next = current.next;
            newNode.prev = current;

            if (current.next != null) current.next.prev = newNode;
            else tail = newNode;

            current.next = newNode;
        }

        numberOfEntries++;
        rebuildSkipPointers(); // <-- refresh skip links
        return true;
    }

    //This sets skip pointers for fast access
    private void rebuildSkipPointers() {
        int skipStep = (int) Math.sqrt(numberOfEntries); // you can make it 2 or 3 for small lists
        Node current = head;
        Node skipRef = head;

        int i = 0;

        while (current != null) {
            if (i == skipStep) {
                skipRef.skip = current;
                skipRef = current;
                i = 0;
            }
            current = current.next;
            i++;
        }
        if (skipRef != null) skipRef.skip = null; // clean end
    }
    
    @Override
    public T find(T target, Comparator<T> comparator) {
        Node current = head;
        Node last = current;
        
        while (current.skip != null && comparator.compare(current.skip.data, target) <= 0) {
            last = current;
            current = current.skip;
        }

        // now search from `last`, not `current`
        current = last;
        while (current != null && comparator.compare(current.data, target) != 0) {
            current = current.next;
        }

        return current != null ? current.data : null;
    }

    // ==========================
    // Iterator 
    // ==========================
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= numberOfEntries) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
        return getNodeAt(index + 1).data;
    }

    // ==========================
    // Inner Classes
    // ==========================

    private class Node {
        private T data;
        private Node next;
        private Node prev;
        private Node skip; // ← this is the skip pointer (optional fast path)

        private Node(T data) {
            this.data = data;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }
}