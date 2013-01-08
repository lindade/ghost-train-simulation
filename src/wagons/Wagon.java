package wagons;

import exceptions.MaxPassengerCapacityReachedException;
import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Linda
 */
public abstract class Wagon {

    private List<Passenger> passengers;
    private static final int MAX_PASSENGER_CAPACITY = 3;
    private static int wagonCount = 0;
    private static int maxWagonCount = 50;

    public Wagon() throws MaxWagonCountReached {
        increaseWagonCount();
        passengers = new ArrayList<>(0);
    }

    public void getWagonCount() {
        System.out.println("Wagons created: " + wagonCount);
    }
    
    /**
     * controls that no more than 50 wagons are build.
     * @throws MaxWagonCountReached 
     */
    private static void increaseWagonCount() throws MaxWagonCountReached {
        if (wagonCount < maxWagonCount) {
            wagonCount++;
        } else {
            throw new MaxWagonCountReached("Train already has maximum number of wagons");
        }
    }

    public void addPassenger(Passenger p) throws MaxPassengerCapacityReachedException {
        if (passengers.size() < MAX_PASSENGER_CAPACITY) {
            passengers.add(p);
        } else {
            throw new MaxPassengerCapacityReachedException("Maximum number of Passengers reached!");
        }
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void removePassenger(Passenger p) {
        passengers.remove(p);
    }

    public void printList() {
        for (Passenger pas : getPassengers()) {
            System.out.print(pas.getName() + "\t");
        }
    }
}
