package torres.torreshanoi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Una implementación simple de una pila personalizada.
 *
 * @param <T> El tipo de elementos almacenados en la pila.
 */
import java.util.EmptyStackException;

public class MyStack<E> {
    private Node<E> top;
    private int size;

    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E element) {
        Node<E> newNode = new Node<>(element, top);
        top = newNode;
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E poppedElement = top.getElement();
        top = top.getNext();
        size--;
        return poppedElement;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getElement();
    }
public void addAll(MyStack<E> stackToAdd) {
    MyStack<E> tempStack = new MyStack<>();
    while (!stackToAdd.isEmpty()) {
        tempStack.push(stackToAdd.pop());
    }
    while (!tempStack.isEmpty()) {
        push(tempStack.pop());
    }
}

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}