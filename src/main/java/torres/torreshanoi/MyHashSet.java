    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package torres.torreshanoi;

    /**
     *
     * @author edgar
     */
    import java.util.AbstractSet;
import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
import java.util.Map;

    public class MyHashSet<K> extends AbstractSet<K> {

        private final int INITIAL_CAPACITY = 16;
        private Object[] array;
        private int size;

        /**
         * Constructor predeterminado que inicializa el conjunto con una capacidad
         * inicial.
         */
        public MyHashSet() {
            array = new Object[INITIAL_CAPACITY];
            size = 0;
        }

        // Implementación de los métodos abstractos de AbstractSet
        @Override
        public boolean add(K element) {
            if (contains(element)) {
                return false;
            }
            if (size == array.length) {
                resize();
            }
            int index = getIndex(element);
            array[index] = element;
            size++;
            return true;
        }

        @Override
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                private int currentIndex = 0;

                @Override
                public boolean hasNext() {
                    return currentIndex < size;
                }

                @Override
                public K next() {
                    if (!hasNext()) {
                        throw new IndexOutOfBoundsException("No hay más elementos disponibles en el iterador");
                    }
                    return (K) array[currentIndex++];
                }
            };
        }

        @Override
        public int size() {
            return size;
        }

        // Otros métodos de la clase...
        // Método contains anulado para evitar el conflicto con el método de AbstractCollection


        // Constructor que acepta una lista como argumento
        public MyHashSet(List<K> list) {
            this();
            for (K element : list) {
                add(element);
            }
        }

        // Método para agregar todos los elementos de una lista al conjunto
        public void addAll(List<K> list) {
            for (K element : list) {
                add(element);
            }
        }

        private int getIndex(K element) {
            return Math.abs(element.hashCode() % array.length);
        }

        private void resize() {
            Object[] newArray = new Object[array.length * 2];
            for (Object element : array) {
                if (element != null) {
                    int index = getIndex((K) element);
                    newArray[index] = element;
                }
            }
            array = newArray;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            boolean first = true;
            for (Object element : array) {
                if (element != null) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(element.toString());
                    first = false;
                }
            }
            sb.append("]");
            return sb.toString();
        }

  public List<K> toList() {
        List<K> list = new ArrayList<>();
        for (Object element : array) {
            if (element != null) {
                list.add((K) element);
            }
        }
        return list;
    }
    public boolean containsSameTower(Estado nodo, List<Estado> list) {
        for (Estado n : list) {
           // System.out.println("Comparando nodos visitados:");
            //System.out.println("Nodo actual: " + nodo.estado);
            //System.out.println("Nodo en la lista: " + n.estado);

            if (areTowersEqualButNotOrdered(nodo.torres, n.torres)) {
              //  System.out.println("Los nodos tienen la misma torre.");
                return true;
            }
        }
       // System.out.println("Los nodos no tienen la misma torre.");
        return false;
    }


private boolean areTowersEqualButNotOrdered(Map<String, Pila<Integer>> torres1, Map<String, Pila<Integer>> torres2) {
    for (String torre : torres1.keySet()) {
        Pila<Integer> pila1 = torres1.get(torre);
        Pila<Integer> pila2 = torres2.get(torre);

        if (pila1.size() != pila2.size()) {
            return false;
        }

        // Verificar si los números están presentes en ambas pilas
        for (Integer disco : pila1) {
            if (!pila2.contains(disco)) {
                return false;
            }
        }
    }
    return true;
}


    }
