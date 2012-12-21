/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wagons;

import ghosttrain.Destination;
import ghosttrain.Passenger;

/**
 *
 * @author Linda
 */
public class WagonTest {

    public static void main(String[] args){
    Wagon wagon1 = new PassengerWagon();

    wagon1.getWagonCount ();
    ActivityWagon wagon2 = new FunWagon("Minigolf");
    ActivityWagon wagon3 = new TrainingWagon("Weightlifting");
    ActivityWagon wagon4 = new EatingWagon("Sushi");

    wagon1.getWagonCount ();

    wagon2.printName ();

    wagon3.printName ();

    wagon4.printName ();
    Passenger p1 = new Passenger("Anna", 3, 2, 3, new Destination());

    wagon2.addPassenger (p1);
    Passenger p2 = new Passenger("Benjamin", 3, 2, 3, new Destination());

    wagon2.addPassenger (p2);
    Passenger p3 = new Passenger("Claudia", 3, 2, 3, new Destination());

    wagon2.addPassenger (p3);

    wagon2.demandEarning ();
    }
}
