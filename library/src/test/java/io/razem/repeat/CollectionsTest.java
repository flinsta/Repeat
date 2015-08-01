package io.razem.repeat;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;

/**
 * Created by julianliebl on 24.07.2015.
 */
public class CollectionsTest {
    List<TestClass> objects;
    @Before
    public void before(){
        objects = new ArrayList<>();
        objects.add(new TestClass(1, "a"));
        objects.add(new TestClass(1, "a"));
        objects.add(new TestClass(1, "b"));
        objects.add(new TestClass(1, "a"));
        objects.add(new TestClass(2, "c"));
        objects.add(new TestClass(2, "c"));
        objects.add(new TestClass(2, "c"));
        objects.add(new TestClass(3, "d"));
        objects.add(new TestClass(3, "e"));
        objects.add(new TestClass(3, "f"));
        objects.add(new TestClass(3, "g"));
    }

    @Test
    public void toMapWithSingleValue(){
        Map<Object, TestClass> map = Collections.ToMap.withSingleValue(objects, TestClass::getValue1);

        TestClass testClass1 = map.get(1);

        assertEquals(testClass1.getValue1(), Integer.valueOf(1));
        assertEquals(testClass1.getValue2(), "a");

        TestClass testClass2 = map.get(2);

        assertEquals(testClass2.getValue1(), Integer.valueOf(2));
        assertEquals(testClass2.getValue2(), "c");

        TestClass testClass3 = map.get(3);

        assertEquals(testClass3.getValue1(), Integer.valueOf(3));
        assertEquals(testClass3.getValue2(), "g");
    }

    @Test
    public void toMapWithListAsReturnValue(){
        Map<Integer, List<TestClass>> map = Collections.ToMap.withListAsReturnValue(objects, TestClass::getValue1);

        List<TestClass> testClasses1 = map.get(1);
        List<TestClass> testClasses2 = map.get(2);
        List<TestClass> testClasses3 = map.get(3);

        assertEquals(testClasses1.size(), 4);
        assertEquals(testClasses2.size(), 3);
        assertEquals(testClasses3.size(), 4);

        assertEquals(testClasses1.get(0).getValue2(), "a");
        assertEquals(testClasses1.get(1).getValue2(), "a");
        assertEquals(testClasses1.get(2).getValue2(), "b");
        assertEquals(testClasses1.get(3).getValue2(), "a");

        assertEquals(testClasses2.get(0).getValue2(), "c");
        assertEquals(testClasses2.get(1).getValue2(), "c");
        assertEquals(testClasses2.get(2).getValue2(), "c");

        assertEquals(testClasses3.get(0).getValue2(), "d");
        assertEquals(testClasses3.get(1).getValue2(), "e");
        assertEquals(testClasses3.get(2).getValue2(), "f");
        assertEquals(testClasses3.get(3).getValue2(), "g");
    }


    @Test
    public void toMapWithSetAsReturnValue(){
        Map<Object, Set<TestClass>> map = Collections.ToMap.withSetAsReturnValue(objects, TestClass::getValue1);

        Set<String> testClasses1 = map.get(1).stream().map(TestClass::getValue2).collect(toSet());
        Set<String> testClasses2 = map.get(2).stream().map(TestClass::getValue2).collect(toSet());
        Set<String> testClasses3 = map.get(3).stream().map(TestClass::getValue2).collect(toSet());

        assertEquals(testClasses1.size(), 2);
        assertEquals(testClasses2.size(), 1);
        assertEquals(testClasses3.size(), 4);

        assertEquals(testClasses1.stream().anyMatch(t -> t.equals("a")), true);
        assertEquals(testClasses1.stream().anyMatch(t -> t.equals("b")), true);

        assertEquals(testClasses2.stream().anyMatch(t -> t.equals("c")), true);

        assertEquals(testClasses3.stream().anyMatch(t -> t.equals("d")), true);
        assertEquals(testClasses3.stream().anyMatch(t -> t.equals("e")), true);
        assertEquals(testClasses3.stream().anyMatch(t -> t.equals("f")), true);
        assertEquals(testClasses3.stream().anyMatch(t -> t.equals("g")), true);
    }

    private class TestClass{
        private final Integer value1;
        private final String  value2;

        private TestClass(Integer value1, String value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public Integer getValue1() {
            return value1;
        }

        public String getValue2() {
            return value2;
        }
    }
}
