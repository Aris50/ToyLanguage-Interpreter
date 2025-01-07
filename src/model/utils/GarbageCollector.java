
package model.utils;

import model.values.IValue;
import model.values.RefValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GarbageCollector {

    public static Map<Integer, IValue> unsafeGarbageCollector(Collection<IValue> symTableValues, Map<Integer, IValue> heap) {
        List<Integer> addresses = getAddrFromSymTable(symTableValues, heap);
        return heap.entrySet().stream()
                .filter(e -> addresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues, Map<Integer, IValue> heap) {
        Set<Integer> addresses = symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toSet());

        Set<Integer> newAddresses = addresses;
        do {
            newAddresses = newAddresses.stream()
                    .filter(heap::containsKey)
                    .map(addr -> heap.get(addr))
                    .filter(value -> value instanceof RefValue)
                    .map(value -> (RefValue) value)
                    .map(RefValue::getAddress)
                    .filter(addr -> !addresses.contains(addr))
                    .collect(Collectors.toSet());
            addresses.addAll(newAddresses);
        } while (!newAddresses.isEmpty());

        return addresses.stream().collect(Collectors.toList());
    }

    public static Map<Integer, IValue> conservativeGarbageCollector(List<Object> symTableValues, Map<Integer, IValue> heap) {
        // Collect all addresses referenced by the symbol tables
        Set<Integer> addresses = symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toSet());

        // Filter the heap to keep only the entries that are referenced by these addresses
        return heap.entrySet().stream()
                .filter(e -> addresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}