import junit.framework.TestCase;
import org.junit.Test;

public class test1Test extends TestCase {

    test1 t = new test1();

    public void testAdd() {
        int a=3;
        int b=5;
        assertEquals(8,t.add(a,b));
    }

    public void testMulti() {
        int a=3;
        int b = 5;
        assertEquals(15,t.multi(a,b));
        assertEquals(16,t.multi(a+1,b));
        assertEquals(18,t.multi(a,b+1));
    }

    public void tf() {
        assertEquals(3,t.tf(3));
    }

    @Test
    public void one() {
        assertEquals(1,t.one());
    }

    @Test
    public void two() {
        assertEquals(2,t.two());
    }

    @Test
    public void three() {
        assertEquals(3,t.three());
    }
}