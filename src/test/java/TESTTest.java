import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TESTTest {

    private TEST test;
    @BeforeEach
    void setUp() {
        this.test = new TEST();
    }

    @Test
    void return3() {
        Assertions.assertEquals(3,test.return3());
    }

    @Test
    void returnName() {
        Assertions.assertEquals("Name",test.returnName());
    }

    @Test
    void returnTrue() {
        Assertions.assertEquals(true,test.returnTrue());
    }
}