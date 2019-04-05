package helpers;

import java.util.Collection;
import java.util.Map;

public class CollectionLogger {
    public static <T> void print(Collection<T> collection) {
        if (collection.isEmpty()) {
            System.out.println("{}");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (T element : collection)
            sb.append(element).append(", ");
        System.out.println("{" +  sb.substring(0, sb.length() - 2) + "}");
    }

    public static <K, V> void printMap(Map<K, V> map) {
        if (map.isEmpty()) {
            System.out.println("{}");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (K k : map.keySet()) {
            sb.append(k).append(": ").append(map.get(k)).append(", ");
        }
        if (sb.length() > 2) System.out.println(sb.substring(0, sb.length() - 2) + "}");
    }
}
