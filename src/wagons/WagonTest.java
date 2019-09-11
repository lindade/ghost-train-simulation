package wagons;

import exceptions.MaxPassengerCapacityReachedException;
import exceptions.MaxWagonCountReached;
import ghosttrain.Destination;
import ghosttrain.Passenger;
import ghosttrain.Schedule;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class WagonTest {

    private static final Logger log = Logger.getLogger(WagonTest.class.getName());

    public static void main(String[] args) {
        PassengerWagon passengerWagon = null;
        ActivityWagon funWagon = null;
        ActivityWagon eatingWagon = null;
        ActivityWagon trainingWagon = null;
        
        /**
         * try to build different wagons types
         */
        try {
            passengerWagon = new PassengerWagon();
            passengerWagon.getWagonCount();
            funWagon = new FunWagon();
            eatingWagon = new EatingWagon();
            trainingWagon = new TrainingWagon();
        } catch (MaxWagonCountReached ex) {
            log.log(Level.FINEST,"Maximum number of wagons reached!" + " ex: {0}", ex.getMessage());
        }
        
         /**
         * create destinations
         */
        Destination berlin = new Destination("Berlin");
        Destination munich = new Destination("munich", 3000, 12);
        
        // print out the number of wagons
        passengerWagon.getWagonCount();
        
        /**
         * get names of Destination, activity wagons
        */
        berlin.getName();
        
        /**
         * create passengers
         */
        Passenger p1 = new Passenger("Anna", 1, 2, 2, munich);
        Passenger p2 = new Passenger("Ben", 2, 2, 2, berlin);
        Passenger p3 = new Passenger("Claudia", 1, 1, 4, berlin);
        try {
            // seating Passengers in passenger wagon 
            passengerWagon.addPassenger(p1);
            passengerWagon.addPassenger(p2);
            passengerWagon.addPassenger(p3);
        } catch (MaxPassengerCapacityReachedException ex) {
            // automatically generated after Logger
            log.log(Level.SEVERE, null, ex);
        }
        // unload passengers
        passengerWagon.getOff(berlin);
        
        
        try {
            // seating Passengers in activity wagon 
            funWagon.addPassenger(p1);
            funWagon.addPassenger(p2);
            funWagon.addPassenger(p3);
        } catch (MaxPassengerCapacityReachedException ex) {
            // automatically generated after Logger
            log.log(Level.SEVERE, null, ex);
        }
        // calculate the parameter of the passengers
        funWagon.demandEarning();
        // empty bucket test here the bucket empty method
        
        
        
        Passenger p11 = new Passenger("Dirk", 0, 4, 5, berlin);
        Passenger p12 = new Passenger("Eva", 5, 1, 0, berlin);
        Passenger p13 = new Passenger("Florian", 3, 2, 5, berlin);
        try {
            // seating Passengers in activity wagon 
            eatingWagon.addPassenger(p11);
            eatingWagon.addPassenger(p12);
            eatingWagon.addPassenger(p13);
            //test if maximum number of passengers is limited to 3 
            //eatingWagon.addPassenger(p1);
        } catch (MaxPassengerCapacityReachedException ex) {
            // automatically generated after Logger
            log.log(Level.SEVERE, null, ex);
        }
        eatingWagon.demandEarning();
        
        // Asks if Berlin exists as a destination
        log.log(Level.FINEST, "Is Berlin available: {0}", Schedule.doesDestinationExist("Berlin"));


        Passenger p21 = new Passenger("Gregor", 3, 4, 3, new Destination("Berlin"));
        Passenger p22 = new Passenger("Hugo", 1, 5, 5, new Destination("Berlin"));
        Passenger p23 = new Passenger("Ingrid", 3, 3, 1, new Destination("Berlin"));
        try {
            // seating Passengers in activity wagon 
            trainingWagon.addPassenger(p21);
            trainingWagon.addPassenger(p22);
            trainingWagon.addPassenger(p23);
        } catch (MaxPassengerCapacityReachedException ex) {
            // automatically generated after Logger
            log.log(Level.SEVERE, null, ex);
        }
        trainingWagon.demandEarning();
        trainingWagon.removePassenger(p22);
        log.log(Level.FINEST, "Passenger {0} unfortunately jumped out of the window. He will not be able to earn any money anymore. minus {1}", new Object[]{p22.getName(), p22.getTrainingValue()});
        trainingWagon.demandEarning();
        
        /**
         * Test case:
         * set temporary maximum wagon counter to 5, tested if more than 5 wagons could be build.
         */
        try {
            trainingWagon = new TrainingWagon();
            trainingWagon = new TrainingWagon();
        } catch (MaxWagonCountReached ex) {
            log.log(Level.FINEST,"Maximum number of wagons reached!" + " ex: {0}", ex.getMessage());
        }
    }
}