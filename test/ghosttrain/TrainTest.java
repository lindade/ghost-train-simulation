/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosttrain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wagons.ActivityWagon;
import wagons.PassengerWagon;

/**
 *
 * @author Linda
 */
public class TrainTest {
    
    public TrainTest() {
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
     * Test of addPassengerWagon method, of class Train.
     */
    @Test
    public void testAddPassengerWagon() {
        log.info("addPassengerWagon");
        PassengerWagon pw = null;
        Train instance = new Train();
        instance.addPassengerWagon(pw);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addActivityWagon method, of class Train.
     */
    @Test
    public void testAddActivityWagon() {
        log.info("addActivityWagon");
        ActivityWagon aw = null;
        Train instance = new Train();
        instance.addActivityWagon(aw);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of dropOffPassenger method, of class Train.
     */
    @Test
    public void testDropOffPassenger() {
        log.info("dropOffPassenger");
        Train instance = new Train();
        instance.dropOffPassenger();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of enterNextCity method, of class Train.
     */
    @Test
    public void testEnterNextCity() {
        log.info("enterNextCity");
        Train instance = new Train();
        instance.enterNextCity();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentDestination method, of class Train.
     */
    @Test
    public void testGetCurrentDestination() {
        log.info("getCurrentDestination");
        Train instance = new Train();
        Schedule schedule = new Schedule();
        Destination expResult = schedule.getCurrentStop();
        Destination result = instance.getCurrentDestination();
        log.info(schedule.getCurrentStop().getName());
        log.info(instance.getCurrentDestination().getName());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("the saved destination is the same but has not the same reference");
    }

    /**
     * Test of getWagons method, of class Train.
     */
    @Test
    public void testGetWagons() {
        log.info("getWagons");
        Train instance = new Train();
        List expResult = null;
        List result = instance.getWagons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
