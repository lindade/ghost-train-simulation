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
        System.out.println("getLevel");
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
        System.out.println("loadPassengers");
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
        System.out.println("staffActivityWagon");
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
        System.out.println("staffPassengerWagon");
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
        System.out.println("collectIncome");
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
        System.out.println("switchPassengersToPassengerWagon");
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
        System.out.println("buyPassengerWagon");
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
        System.out.println("buyFunWagon");
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
        System.out.println("buyEatingWagon");
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
        System.out.println("buyTrainingWagon");
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
        System.out.println("Engine");
        Player instance = new Player();
        instance.buyEngine();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
