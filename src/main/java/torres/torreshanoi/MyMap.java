/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author edgar
 */
/**
 * Una implementación simple de un mapa personalizado.
 *
 * @param <K> El tipo de las claves.
 * @param <V> El tipo de los valores.
 */
public interface MyMap<K, V> {

    V get(K key);

    void put(K key, V value);

    boolean containsKey(K key);

    boolean isEmpty();

    int size();

    void remove(K key);

    void clear();

    Set<K> getKeys();
}
