package wagons;

import ghosttrain.Destination;
import ghosttrain.Passenger;
import java.util.ArrayList;

/**
 *
 * @author Linda
 */
public class PassengerWagon extends Wagon {

    static int offBoardedPassengersCount;

    public PassengerWagon() {
        wagonCount++;
        passengers = new ArrayList<Passenger>();

    }

    @Override
    public void addPassenger(Passenger p) {
        passengers.add(p);
        System.out.println("Passenger List: " + passengers);
    }

    public void getOff(Destination currentDest) {
        // drop off passenger at correct destination
        for (Passenger p : passengers) {
            if (p.getDeboarding() == currentDest) {
                System.out.println("Passenger " + p.getName() + " has deboarded at " + currentDest.getName() + ".");
                passengers.remove(p);
                offBoardedPassengersCount++;
            } else {
                System.out.println("Passenger " + p.getName() + " has not deboarded. He wants to deboard at " + p.getDeboarding() + ".");
            }
        }
    }
}
