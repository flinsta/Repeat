package io.razem.repeat;

/**
 * Created by julianliebl on 24.07.2015.
 */
public class Is {

    /**
     * Determines whether two possibly-null objects are equal.
     * @param o1 an object
     * @param o2 an object to be compared with a for equality
     * @return true if objects according to Object.equals(Object) are equal or both null
     */
    public static boolean equal(Object o1, Object o2){
        if(o1 == null) return o2 == null;

        return o1.equals(o2);
    }

    public static class Not extends Is{
        public static boolean equal(Object o1, Object o2) {
            return !Is.equal(o1, o2);
        }
    }
}
