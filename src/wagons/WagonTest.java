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

    public static void main(String[] args) {
        PassengerWagon passengerWagon = null;
        ActivityWagon funWagon = null;
        ActivityWagon eatingWagon = null;
        ActivityWagon trainingWagon = null;


        try {
            passengerWagon = new PassengerWagon();
            passengerWagon.getWagonCount();
            funWagon = new FunWagon("Minigolf");
            eatingWagon = new EatingWagon("Sushi");
            trainingWagon = new TrainingWagon("Weightlifting");
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
        Destination berlin = new Destination("Berlin");
        Destination munich = new Destination("munich", 3000);

        passengerWagon.getWagonCount();

        berlin.getName();

        funWagon.printName();

        eatingWagon.printName();

        trainingWagon.printName();
        Passenger p1 = new Passenger("Anna", 1, 2, 2, munich);
        Passenger p2 = new Passenger("Ben", 2, 2, 2, berlin);
        Passenger p3 = new Passenger("Claudia", 1, 1, 4, berlin);
        try {
            // seating Passengers in wagon 
            passengerWagon.addPassenger(p1);
            passengerWagon.addPassenger(p2);
            passengerWagon.addPassenger(p3);
        } catch (MaxPassengerCapacityReachedException ex) {
            Logger.getLogger(WagonTest.class.getName()).log(Level.SEVERE, null, ex);
        }



        passengerWagon.getOff(berlin);
        try {
            // seating Passengers in wagon 
            funWagon.addPassenger(p1);
            funWagon.addPassenger(p2);
            funWagon.addPassenger(p3);
        } catch (MaxPassengerCapacityReachedException ex) {
            Logger.getLogger(WagonTest.class.getName()).log(Level.SEVERE, null, ex);
        }



        funWagon.demandEarning();
        Passenger p11 = new Passenger("Dirk", 0, 4, 5, berlin);
        Passenger p12 = new Passenger("Eva", 5, 1, 0, berlin);
        Passenger p13 = new Passenger("Florian", 3, 2, 5, berlin);
        try {
            // seating Passengers in wagon 
            eatingWagon.addPassenger(p11);
            eatingWagon.addPassenger(p12);
            eatingWagon.addPassenger(p13);
        } catch (MaxPassengerCapacityReachedException ex) {
            Logger.getLogger(WagonTest.class.getName()).log(Level.SEVERE, null, ex);
        }



        eatingWagon.demandEarning();

        System.out.println("Is Berlin available: " + Schedule.doesDestinationExist("Berlin"));


        Passenger p21 = new Passenger("Gregor", 3, 4, 3, new Destination("Berlin"));
        Passenger p22 = new Passenger("Hugo", 1, 5, 5, new Destination("Berlin"));
        Passenger p23 = new Passenger("Ingrid", 3, 3, 1, new Destination("Berlin"));
        try {
            // seating Passengers in wagon 
            trainingWagon.addPassenger(p21);
            trainingWagon.addPassenger(p22);
            trainingWagon.addPassenger(p23);
        } catch (MaxPassengerCapacityReachedException ex) {
            Logger.getLogger(WagonTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        trainingWagon.demandEarning();
        try {
            trainingWagon = new TrainingWagon("Weightlifting");
            trainingWagon = new TrainingWagon("Weightlifting");
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
    }
}