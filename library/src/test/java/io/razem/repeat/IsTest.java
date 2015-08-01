package io.razem.repeat;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by julianliebl on 24.07.2015.
 */
public class IsTest {
    @Test
    public void equalBothNullTest(){
        Object o1 = null;
        Object o2 = null;

        boolean equal = Is.equal(o1, o2);

        Assert.assertEquals(true, equal);
    }

    @Test
    public void equalFirstNullTest(){
        Object o1 = null;
        Object o2 = "notNull";

        boolean equal = Is.equal(o1, o2);

        Assert.assertEquals(false, equal);
    }

    @Test
    public void equalSecondNullTest(){
        Object o1 = "notNull";
        Object o2 = null;

        boolean equal = Is.equal(o1, o2);

        Assert.assertEquals(false, equal);
    }

    @Test
    public void equalEqualTest(){
        Object o1 = "sameValue";
        Object o2 = "sameValue";

        boolean equal = Is.equal(o1, o2);

        Assert.assertEquals(true, equal);
    }

    @Test
    public void equalNotEqualTest(){
        Object o1 = "firstValue";
        Object o2 = "secondValue";

        boolean equal = Is.equal(o1, o2);

        Assert.assertEquals(false, equal);
    }

    @Test
    public void equalDifferentObjectsTest(){
        Object o1 = "1";
        Object o2 = 1;

        boolean equal = Is.equal(o1, o2);

        Assert.assertEquals(false, equal);
    }
}
