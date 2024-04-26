/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import torres.torreshanoi.Estado;

/**
 *
 * @author edgar
 */
public class TorresDeHanoiBFS2 {

    static Queue<Nodo> cola;

public static List<String> resolverTorresDeHanoi(int numDiscos) {
    Nodo ac = new Nodo();

    Estado estadoInicial = new Estado();
    for (int i = numDiscos; i > 0; i--) {
        estadoInicial.agregarDisco("A", i);
    }

    cola = new Queue<>();
    MyHashSet<Estado> visitados = new MyHashSet<>();
    int contador = 1;
    estadoInicial.setNumE(contador);
    Nodo nodoInicial = new Nodo(estadoInicial, null, null, 0); // El costo del nodo inicial es 1

    cola.enqueue(nodoInicial);
    visitados.add(estadoInicial);
int i=1;
    while (!cola.isEmpty()) {
         //Imprimir el contenido del open set antes de iniciar cada iteración
      System.out.println("Open set antes de iniciar la iteración: "+i);
        imprimirOpenSet(cola);
        
        System.out.print("Lista de VISITADOS en la interacion "+i);
                imprimirVisitados(visitados);
        
        i++;
        Nodo nodoActual = cola.dequeu();
        Estado estadoActual = nodoActual.estado;

        if (esEstadoObjetivo(estadoActual, numDiscos)) {
            return construirCaminoConCosto(nodoActual);
        }

        List<Nodo> hijos = ac.generarHijos(nodoActual, contador);
        for (Nodo hijo : hijos) {
            //System.out.println(hijo.estado+" "+hijo.estado);
            if (!visitados.containsSameTower(hijo.estado, visitados.toList()) && !cola.containsSameTower(hijo, cola.toList())) {
                System.out.println("ENTRO Agrego OPENSER");
                cola.enqueue(hijo);
                visitados.add(hijo.estado);
            }
        }
    }

    return null; // No se encontró solución
}

// Método para imprimir el contenido del open set (cola)
public static void imprimirOpenSet(Queue<Nodo> cola) {
    System.out.println("Contenido del open set:");
    for (Nodo nodo : cola) {
        System.out.println(nodo.estado.toString());
    }
}

    static List<String> construirCaminoConCosto(Nodo nodo) {
        List<String> camino = new ArrayList<>();
        List<String> movimientos = new ArrayList<>(); // Creamos una lista para almacenar los movimientos en orden inverso
        while (nodo != null) {
            if (nodo.movimiento != null) {
                movimientos.add(nodo.estado.toString());
                movimientos.add("Matriz resultante por este movimiento:");
                movimientos.add(nodo.movimiento + " costo = " + nodo.costo);

            }
            nodo = nodo.padre;
        }
        // Agregamos los movimientos en orden inverso a la lista de salida
        for (int i = movimientos.size() - 1; i >= 0; i--) {
            camino.add(movimientos.get(i));
        }
        return camino;
    }

    static boolean esEstadoObjetivo(Estado estado, int numDiscos) {
        return estado.torres.get("A").isEmpty() && estado.torres.get("B").isEmpty() && estado.torres.get("C").size() == numDiscos;
    }

    static List<String> construirCamino(Nodo nodo) {
        List<String> camino = new ArrayList<>();
        while (nodo != null) {
            if (nodo.movimiento != null) {
                camino.add(0, nodo.movimiento);
            }
            nodo = nodo.padre;
        }
        return camino;
    }

    public static Queue<Nodo> getCola() {
        return cola;
    }

    public static void setCola(Queue<Nodo> cola) {
        TorresDeHanoiBFS2.cola = cola;
    }
public boolean containsSameTower(Nodo child, List<Nodo> visited) {
        for (Nodo visitedNode : visited) {
            if (child.estado.equals(visitedNode.estado)) {
                return true;
            }
        }
        return false;
    }

public static void imprimirVisitados(MyHashSet<Estado> visitados) {
    System.out.println("Contenido de la lista de visitados:");
    for (Estado estado : visitados) {
        System.out.println(estado.toString());
    }
}


}
