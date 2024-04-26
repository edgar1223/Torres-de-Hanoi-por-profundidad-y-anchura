/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

/**
 *
 * @author edgar
 */
public class MyObjects {
    // Método para verificar si dos objetos son iguales
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    // Método para calcular el hash code de un objeto
    public static int hashCode(Object o) {
        return (o == null) ? 0 : o.hashCode();
    }

    // Método para devolver el primer objeto no nulo de una serie de objetos
    public static <T> T firstNonNull(T... objects) {
        if (objects != null) {
            for (T obj : objects) {
                if (obj != null) {
                    return obj;
                }
            }
        }
        return null;
    }
    
}
