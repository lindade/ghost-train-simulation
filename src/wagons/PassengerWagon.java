package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Destination;
import ghosttrain.Passenger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Linda
 */
public class PassengerWagon extends Wagon {

    static int offBoardedPassengersCount;

    public PassengerWagon() throws MaxWagonCountReached {
        super();
    }

    public void getOff(Destination currentDest) {
        // drop off passenger at correct destination
        List<Passenger> passengersToDelete = new ArrayList<Passenger>();
        for (Passenger p : getPassengers()) {
            if (p.getDeboarding() == currentDest) {
                passengersToDelete.add(p);
            } else {
                 System.out.println("Passenger " + p.getName() + " has not disembarked. Requested deboarding is at " + p.getDeboarding().getName() + ".");
            }
        }
        for (Passenger p : passengersToDelete) {
            removePassenger(p);
            offBoardedPassengersCount++;
            System.out.println("Passenger " + p.getName() + " has disembarked at " + currentDest.getName() + ".");
        }
    }

}
