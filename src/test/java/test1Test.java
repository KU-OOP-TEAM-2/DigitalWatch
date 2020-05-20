import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

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
    }

    public void tf() {
        assertEquals(3,t.tf(3));
    }
}