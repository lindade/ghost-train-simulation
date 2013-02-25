package wagons;

import exceptions.MaxPassengerCapacityReachedException;
import exceptions.MaxWagonCountReached;
import ghosttrain.Destination;
import ghosttrain.Passenger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author linda
 */
public class PassengerWagonTest {
    PassengerWagon wagon;
    
    public PassengerWagonTest() {
    }

    @BeforeClass
    public static void setUpClass() {       
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            wagon = new PassengerWagon();
        } catch (MaxWagonCountReached ex) {
            Logger.getLogger(PassengerWagonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            wagon.addPassenger( new Passenger("Linda", 5, 5, 5, new Destination("Berlin")));
        } catch (MaxPassengerCapacityReachedException ex) {
            Logger.getLogger(PassengerWagonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPassenger method, of class PassengerWagon.
     */
    @Test
    public void testAddPassenger() {
        log.info("addPassenger");
        Passenger p = new Passenger("Alena", 2, 3, 5, new Destination("Berlin"));
        try {
            wagon.addPassenger(p);
        } catch (MaxPassengerCapacityReachedException ex) {
            Logger.getLogger(PassengerWagonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(wagon.getPassengers().size()==2);
    }

    /**
     * Test of getOff method, of class PassengerWagon.
     */
    @Test
    public void testGetOff() {
        log.info("getOff");
        Passenger linda = wagon.getPassengers().get(0);
        Destination d = linda.getDeboarding();
        wagon.getOff(d);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(wagon.getPassengers().size()==0);
    }
   
}
