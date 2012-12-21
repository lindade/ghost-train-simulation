package wagons;

import ghosttrain.Destination;
import ghosttrain.Passenger;

/**
 *
 * @author Linda
 */
public class WagonTest {

    public static void main(String[] args){
        
    PassengerWagon passengerWagon = new PassengerWagon();
    passengerWagon.getWagonCount ();
    ActivityWagon funWagon = new FunWagon("Minigolf");
    ActivityWagon eatingWagon = new EatingWagon("Sushi");
    ActivityWagon trainingWagon = new TrainingWagon("Weightlifting");
    Destination berlin = new Destination();
    Destination munich = new Destination("munich", 3000);
    
    passengerWagon.getWagonCount ();
    berlin.getName();
    
    funWagon.printName ();
    eatingWagon.printName ();
    trainingWagon.printName ();
     
    Passenger p1 = new Passenger("Anna", 1, 2, 2, munich);
    Passenger p2 = new Passenger("Ben", 2, 2, 2, berlin);
    Passenger p3 = new Passenger("Claudia", 1, 1, 4, berlin);
    
    // seating Passengers in wagon 
    passengerWagon.addPassenger (p1);
    passengerWagon.addPassenger (p2);
    passengerWagon.addPassenger (p3);
    
    passengerWagon.getOff(berlin);
    
    // seating Passengers in wagon 
    funWagon.addPassenger (p1);
    funWagon.addPassenger (p2);
    funWagon.addPassenger (p3);

    funWagon.demandEarning ();
    
    Passenger p11 = new Passenger("Dirk", 0, 4, 5, berlin);
    Passenger p12 = new Passenger("Eva", 5, 1, 0, berlin);
    Passenger p13 = new Passenger("Florian", 3, 2, 5, berlin);
    
    // seating Passengers in wagon 
    eatingWagon.addPassenger (p11);
    eatingWagon.addPassenger (p12);
    eatingWagon.addPassenger (p13);
    
    eatingWagon.demandEarning ();
    
    Passenger p21 = new Passenger("Gregor", 3, 4, 3, new Destination());
    Passenger p22 = new Passenger("Hugo", 1, 5, 5, new Destination());
    Passenger p23 = new Passenger("Ingrid", 3, 3, 1, new Destination());
    
    // seating Passengers in wagon 
    trainingWagon.addPassenger (p21);
    trainingWagon.addPassenger (p22);
    trainingWagon.addPassenger (p23);
    
    trainingWagon.demandEarning ();
    }
}