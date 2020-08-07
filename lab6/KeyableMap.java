import java.util.Map;
import java.util.HashMap;

class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T> { 
    protected Map<K, V> map;
    protected T key; 

    public KeyableMap(T key) { 
        this.key = key;
        map = new HashMap<K, V>(); 
    } 

    public V get(K key) { 
        return map.get(key); 
    }

    public KeyableMap<T, K, V> put(V item) {
        map.put(item.getKey(), item);  
        return this; 
    }

    public T getKey() { 
        return this.key;
    }
    
    public String toString() { 
        String str = key + ": {";
        for (Map.Entry<K, V> e: map.entrySet()) { 
            str = str + e.getValue() + ", ";
        }
        if (map.size() != 0) {
        str = str.substring(0, str.length() - 2); 
        }
        return str + "}";
    }
}
