/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of getLevel method, of class Player.
     */
    @Test
    public void testGetLevel() {
        log.info("getLevel");
        Player instance = new Player();
        int expResult = 1;
        int result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPassengers method, of class Player.
     */
    @Test
    public void testLoadPassengers() {
        log.info("loadPassengers");
        Player instance = new Player();
        instance.loadPassengers();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of staffActivityWagon method, of class Player.
     */
    @Test
    public void testStaffActivityWagon() {
        log.info("staffActivityWagon");
        Player instance = new Player();
        instance.staffActivityWagon();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of staffPassengerWagon method, of class Player.
     */
    @Test
    public void testStaffPassengerWagon() {
        log.info("staffPassengerWagon");
        Player instance = new Player();
        instance.staffPassengerWagon();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of collectIncome method, of class Player.
     */
    @Test
    public void testCollectIncome() {
        log.info("collectIncome");
        Player instance = new Player();
        instance.collectIncome();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of switchPassengersToPassengerWagon method, of class Player.
     */
    @Test
    public void testSwitchPassengersToPassengerWagon() {
        log.info("switchPassengersToPassengerWagon");
        Player instance = new Player();
        instance.switchPassengersToPassengerWagon();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buyPassengerWagon method, of class Player.
     */
    @Test
    public void testBuyPassengerWagon() {
        log.info("buyPassengerWagon");
        Player instance = new Player();
        instance.buyPassengerWagon();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buyFunWagon method, of class Player.
     */
    @Test
    public void testBuyFunWagon() {
        log.info("buyFunWagon");
        Player instance = new Player();
        instance.buyFunWagon();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buyEatingWagon method, of class Player.
     */
    @Test
    public void testBuyEatingWagon() {
        log.info("buyEatingWagon");
        Player instance = new Player();
        instance.buyEatingWagon();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buyTrainingWagon method, of class Player.
     */
    @Test
    public void testBuyTrainingWagon() {
        log.info("buyTrainingWagon");
        Player instance = new Player();
        instance.buyTrainingWagon();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Engine method, of class Player.
     */
    @Test
    public void testEngine() {
        log.info("Engine");
        Player instance = new Player();
        instance.buyEngine();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
