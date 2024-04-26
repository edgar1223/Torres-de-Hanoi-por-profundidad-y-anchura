/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

import java.util.List;

/**
 *
 * @author edgar
 */
public class main {
    public static void main(String[] args) {
    int numDiscos = 3; // Cambia este valor según el número de discos que desees
    Estado estadoInicial = new Estado();
    TorresDeHanoiBFS2 ac=new TorresDeHanoiBFS2();
    for (int i = numDiscos; i > 0; i--) {
        estadoInicial.agregarDisco("A", i);
    }
    
    // Imprimir el estado inicial
    System.out.println("nodo inicial");
    System.out.println(estadoInicial);

    List<String> solucion = ac.resolverTorresDeHanoi(numDiscos);
    
    if (solucion != null) {
        
        System.out.println("Solución:");
        for (String movimiento : solucion) {
            System.out.println("---------------------------------------------------");
            System.out.println(movimiento);
        }
        
     ///   Imprimir el open set restante
       System.out.println("Open set restante:");
       for (Nodo nodo : ac.getCola()) {
           System.out.println("Estado: " + nodo.estado);
       }
    } else {
        System.out.println("No se encontró solución");
    }
}
}
