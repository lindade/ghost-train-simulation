package ghosttrain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Linda
 */
public class LevelAdminTest {
    
    public LevelAdminTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of raiseLevel method, of class LevelAdmin.
     */
    @Test
    public void testRaiseLevel() {
        System.out.println("raiseLevel");
        LevelAdmin instance = new LevelAdmin();
        instance.raiseLevel();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLevel method, of class LevelAdmin.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        LevelAdmin instance = new LevelAdmin();
        int expResult = 1;
        int result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
