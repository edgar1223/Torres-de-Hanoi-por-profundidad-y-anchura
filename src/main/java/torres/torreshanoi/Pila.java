/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author edgar
 */
public class Pila<E> implements Iterable<E> {

    private Node<E> top;

   public static class Nodo<E> {
    public E elemento; // Variable elemento
    public Nodo<E> siguiente; // Variable siguiente

    public Nodo(E elemento, Nodo<E> siguiente) {
        this.elemento = elemento;
        this.siguiente = siguiente;
    }
}

    private Nodo<E> cima;
    private int tamaño;

    @Override
    public Iterator<E> iterator() {
        return new PilaIterator();
    }

    private class PilaIterator implements Iterator<E> {

        private Node<E> current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

      @Override
public E next() {
    if (!hasNext()) {
        throw new NoSuchElementException();
    }
    E item = current.element; // Cambio de current.item a current.elemento
    current = current.next; // Cambio de current.next a current.siguiente
    return item;
}


    }

    public Pila() {
        cima = null;
        tamaño = 0;
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    public void push(E elemento) {
        Nodo<E> nuevoNodo = new Nodo<>(elemento, cima);
        cima = nuevoNodo;
        tamaño++;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E elemento = cima.elemento;
        cima = cima.siguiente;
        tamaño--;
        return elemento;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return cima.elemento;
    }

    public void addAll(Pila<? extends E> pila) {
        while (!pila.isEmpty()) {
            push(pila.pop());
        }
    }

    public int size() {
        return tamaño;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<E> current = cima;
        while (current != null) {
            sb.append(current.elemento);
            if (current.siguiente != null) {
                sb.append(", ");
            }
            current = current.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
 public boolean contains(E elemento) {
        Nodo<E> current = cima;
        while (current != null) {
            if (current.elemento.equals(elemento)) {
                return true;
            }
            current = current.siguiente;
        }
        return false;
    }
}
