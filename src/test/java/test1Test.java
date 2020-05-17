import junit.framework.TestCase;

public class test1Test extends TestCase {

    test1 t = new test1();

    public void testAdd() {
        int a=3;
        int b=5;
        assertEquals(8,t.add(a,b));
    }
}