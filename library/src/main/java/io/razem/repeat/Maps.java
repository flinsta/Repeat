package io.razem.repeat;

import java.util.Map;

/**
 * Created by julianliebl on 13.08.15.
 */
public class Maps {
    public static boolean containsKey(Map map, Object key){
        return map != null && map.containsKey(key);
    }

    public static class Not{
        public static boolean containsKey(Map map, Object key){
            return !Maps.containsKey(map, key);
        }
    }
}
