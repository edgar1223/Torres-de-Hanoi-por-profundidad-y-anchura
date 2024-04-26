/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torres.torreshanoi;

/**
 *
 * @author edgar
 */
import java.util.*;

public class MiHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    
    private Entry<K, V>[] buckets;
    private int size;
    private int capacity;
    
    public MiHashMap() {
        this(DEFAULT_CAPACITY);
    }
    
    public MiHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.buckets = new Entry[this.capacity];
        this.size = 0;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) return false;
        
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) return false;

        for (Entry<K, V> entry : buckets) {
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null) return null;
        
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) return null;
        
        int index = getIndex(key);
        
        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value);
            size++;
        } else {
            Entry<K, V> entry = buckets[index];
            while (entry.next != null) {
                if (entry.key.equals(key)) {
                    V oldValue = entry.value;
                    entry.value = value;
                    return oldValue;
                }
                entry = entry.next;
            }
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            } else {
                entry.next = new Entry<>(key, value);
                size++;
            }
        }
        
        // Rehash if necessary
        if ((double)size / capacity >= LOAD_FACTOR) {
            rehash();
        }
        
        return null;
    }

    @Override
    public V remove(Object key) {
        if (key == null) return null;
        
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];
        Entry<K, V> prev = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Entry<K, V> entry : buckets) {
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> entry : buckets) {
            while (entry != null) {
                values.add(entry.value);
                entry = entry.next;
            }
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (Entry<K, V> entry : buckets) {
            while (entry != null) {
                entries.add(entry);
                entry = entry.next;
            }
        }
        return entries;
    }

    private int getIndex(Object key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private void rehash() {
        capacity *= 2;
        Entry<K, V>[] newBuckets = new Entry[capacity];
        
        for (Entry<K, V> entry : buckets) {
            while (entry != null) {
                int index = getIndex(entry.key);
                if (newBuckets[index] == null) {
                    newBuckets[index] = new Entry<>(entry.key, entry.value);
                } else {
                    Entry<K, V> current = newBuckets[index];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = new Entry<>(entry.key, entry.value);
                }
                entry = entry.next;
            }
        }
        
        buckets = newBuckets;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return key + ": " + value;
        }
    }
}

