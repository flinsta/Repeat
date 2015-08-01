package io.razem.repeat;

import java.util.*;
import java.util.function.Function;

/**
 * Created by julianliebl on 24.07.2015.
 */
public class Collections {

    public static class ToMap{
        /**
         * Converts a collection to a map where the key is determined by a given function and the value is simply the
         * collection item. If a key is duplicated it gets overwritten instead of throwing an exception. This behaviour
         * differs from most other libraries. A simple warning -> Data might get lost so be careful.
         * @param collection The collection which should be converted into a map.
         * @param convertFunction The function which returns the key.
         * @return Returns a map.
         */
        public static <K,E> Map<K,E> withSingleValue(Collection<E> collection, Function<E, K> convertFunction){
            return withSingleValue(collection, convertFunction, Function.identity());
        }

        /**
         * Converts a collection to a map where the key and value is determined by a given function. If a key is
         * duplicated it gets overwritten instead of throwing an exception. This behaviour differs from most other
         * libraries. A simple warning -> Data might get lost so be careful.
         * @param collection The collection which should be converted into a map.
         * @param keyConvertFunction The function which returns the key.
         * @param valueConvertFunction The function which returns the value.
         * @return Returns a map.
         */
        public static <E,K,V> Map<K,V> withSingleValue(Collection<E> collection,
                                                       Function<E, K> keyConvertFunction,
                                                       Function<E, V> valueConvertFunction){
            Map<K,V> returnMap = new HashMap<>();

            for(E element : collection){
                returnMap.put(keyConvertFunction.apply(element), valueConvertFunction.apply(element));
            }

            return returnMap;
        }

        /**
         * Converts a collection to a map where the key is determined by a given function and the value is a list. If a
         * key is duplicated the value gets added to the list associated with the key without overwriting old values.
         * It is not possible to loose data which was inside the collection using this method.
         * @param collection The collection which should be converted into a map.
         * @param convertFunction The function which returns the key.
         * @return Returns a map.
         */
        public static <K,E> Map<K,List<E>> withListAsReturnValue(Collection<E> collection,
                                                                 Function<E, K> convertFunction){
            return withListAsReturnValue(collection, convertFunction, Function.identity());
        }

        /**
         * Converts a collection to a map where the key is determined by a given function and the value is a list where
         * it's values are also determined by a given function. If a key is duplicated the value gets added to the list
         * associated with the key without overwriting old values.
         * @param collection The collection which should be converted into a map.
         * @param keyConvertFunction The function which returns the key.
         * @param valueConvertFunction The function which returns the value.
         * @return Returns a map.
         */
        public static <E,K,V> Map<K,List<V>> withListAsReturnValue(Collection<E> collection,
                                                                   Function<E, K> keyConvertFunction,
                                                                   Function<E, V> valueConvertFunction){
            Map<K,List<V>> returnMap = new HashMap<>();

            for(E element : collection){
                List<V> splitList = returnMap.computeIfAbsent(keyConvertFunction.apply(element), s -> new ArrayList<>());
                splitList.add(valueConvertFunction.apply(element));
            }

            return returnMap;
        }

        /**
         * Converts a collection to a map where the key is determined by a given function and the value is a set. If a
         * key is duplicated the value gets added to the set associated with the key without overwriting old values.
         * @param collection The collection which should be converted into a map.
         * @param convertFunction The function which returns the key.
         * @return Returns a map.
         */
        public static <K,E> Map<K,Set<E>> withSetAsReturnValue(Collection<E> collection, Function<E, K> convertFunction){
            return withSetAsReturnValue(collection, convertFunction, Function.identity());
        }

        /**
         * Converts a collection to a map where the key is determined by a given function and the value is a set where
         * it's values are also determined by a given function. If a key is duplicated the value gets added to the set
         * associated with the key without overwriting old values.
         * @param collection The collection which should be converted into a map.
         * @param keyConvertFunction The function which returns the key.
         * @param valueConvertFunction The function which returns the value.
         * @return Returns a map.
         */
        public static <E,K,V> Map<K,Set<V>> withSetAsReturnValue(Collection<E> collection,
                                                                   Function<E, K> keyConvertFunction,
                                                                   Function<E, V> valueConvertFunction){
            Map<K,Set<V>> returnMap = new HashMap<>();

            for(E element : collection){
                Set<V> splitList = returnMap.computeIfAbsent(keyConvertFunction.apply(element), s -> new HashSet<>());
                splitList.add(valueConvertFunction.apply(element));
            }

            return returnMap;
        }
    }

}
