package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Destination;
import ghosttrain.Passenger;
import ghosttrain.PassengerListener;
import ghosttrain.Train;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class PassengerWagon extends Wagon {

    private PassengerListener passengerListener;

    public PassengerWagon() throws MaxWagonCountReached {
        super();
    }

    /**
     * @param currentDest this method tests if passengers want to get off the
     * train if someone wants to get off, this passenger is deleted from the
     * passenger list the counter of the overall persons who left is increased
     */
    public void getOff(Destination currentDest) {
        // drop off passenger at correct destination
        List<Passenger> passengersToDelete = new ArrayList<>();
        for (Passenger p : getPassengers()) {
            if (p.getDeboarding() == currentDest) {
                passengersToDelete.add(p);
            } else {
//                Logger.getLogger(Train.class.getName()).log(Level.INFO, "Passenger {0} has not disembarked. Requested deboarding is at {1} . ", p.getName(), p.getDeboarding().getName());
                System.out.println("Passenger " + p.getName() + " has not disembarked. Requested deboarding is at " + p.getDeboarding().getName() + ".");
            }
        }
        for (Passenger p : passengersToDelete) {
            removePassenger(p);
            System.out.println("Passenger " + p.getName() + " has disembarked at " + currentDest.getName() + ".");
        }
        if (passengerListener != null) {
            //the counter of the overall persons who left is increased
            passengerListener.passengersGotOff(passengersToDelete.size());
        }
    }

    public void addPassengerListener(PassengerListener passengerListerner) {
        this.passengerListener = passengerListerner;
    }
}
