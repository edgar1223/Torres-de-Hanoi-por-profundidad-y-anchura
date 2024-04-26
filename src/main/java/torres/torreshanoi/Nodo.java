/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

import java.util.ArrayList;
import java.util.List;
import torres.torreshanoi.Estado;

/**
 *
 * @author edgar
 */
class Nodo {

    Estado estado;
    Nodo padre;
    String movimiento;
    int costo; // Nuevo campo para almacenar el costo del nodo

    public Nodo(Estado estado, Nodo padre, String movimiento, int costo) {
        this.estado = estado;
        this.padre = padre;
        this.movimiento = movimiento;
        this.costo = costo;
    }

    public Nodo() {
    }

    public List<Nodo> generarHijos(Nodo nodoPadre, int es) {
        List<Nodo> hijos = new ArrayList<>();
        Estado estadoPadre = nodoPadre.estado;

        for (String torreOrigen : estadoPadre.torres.keySet()) {
            for (String torreDestino : estadoPadre.torres.keySet()) {
                if (!torreOrigen.equals(torreDestino)) {
                    if (!estadoPadre.torreVacia(torreOrigen)
                            && (estadoPadre.obtenerTamañoSuperior(torreDestino) > estadoPadre.obtenerTamañoSuperior(torreOrigen))) {

                        Estado nuevoEstado = new Estado(estadoPadre, es);
                        int disco = nuevoEstado.quitarDisco(torreOrigen);
                        nuevoEstado.agregarDisco(torreDestino, disco);
                        hijos.add(new Nodo(nuevoEstado, nodoPadre, torreOrigen + " -> " + torreDestino, nodoPadre.costo + 1)); // El costo del hijo es el costo del padre + 1
                    }
                }
            }
        }

        return hijos;
    }

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Nodo other = (Nodo) obj;
    return estado.equals(other.estado);
}

public boolean containsSameTower(Nodo nodo, List<Nodo> list) {
    for (Nodo n : list) {
        if (nodo.equals(n)) {
            return true;
        }
    }
    return false;
}


}
