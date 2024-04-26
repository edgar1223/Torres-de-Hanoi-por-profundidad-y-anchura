/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author edgar
 */
public class Estado {

    Map<String, Pila<Integer>> torres;
    int numE;

    public int getNumE() {
        return numE;
    }

    public void setNumE(int numE) {
        this.numE = numE;
    }

    public Estado() {
        torres = new HashMap<>();
        torres.put("A", new Pila<>());
        torres.put("B", new Pila<>());
        torres.put("C", new Pila<>());
    }

    public Estado(Estado estado, int numE) {
        this();
        for (String torre : estado.torres.keySet()) {
            Pila<Integer> pilaOriginal = estado.torres.get(torre);
            Pila<Integer> nuevaPila = new Pila<>();
            Pila<Integer> tempPila = new Pila<>();
            while (!pilaOriginal.isEmpty()) {
                tempPila.push(pilaOriginal.pop());
            }
            while (!tempPila.isEmpty()) {
                int disco = tempPila.pop();
                nuevaPila.push(disco);
                pilaOriginal.push(disco);
            }
            torres.put(torre, nuevaPila);
        }
    }

    public void agregarDisco(String torre, int tamaño) {
        torres.get(torre).push(tamaño);
    }

    public int quitarDisco(String torre) {
        return torres.get(torre).pop();
    }

    public boolean torreVacia(String torre) {
        return torres.get(torre).isEmpty();
    }

    public int obtenerTamañoSuperior(String torre) {
        if (!torres.get(torre).isEmpty()) {
            return torres.get(torre).peek();
        } else {
            return Integer.MAX_VALUE; // Representa una torre vacía
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Estado other = (Estado) obj;

        // Comparar el contenido de las torres
        for (String torre : torres.keySet()) {
            if (!torres.get(torre).equals(other.torres.get(torre))) {
                return false;
            }
        }

        // Si todas las torres son iguales, los estados son iguales
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(torres);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String torre : torres.keySet()) {
            sb.append(torre).append(": ").append(torres.get(torre)).append("\n");
        }
        return sb.toString();
    }
}
