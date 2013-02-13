package wagons;

import exceptions.MaxPassengerCapacityReachedException;
import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;
import ghosttrain.Train;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public abstract class Wagon {

    protected List<Passenger> passengers;
    private static final int MAX_PASSENGER_CAPACITY = 3;
    private static int wagonCount = 0;
    private static final int MAX_WAGON_COUNT = 40;

    public Wagon() throws MaxWagonCountReached {
        increaseWagonCount();
        passengers = new ArrayList<>(0);
    }

    public void getWagonCount() {
        Logger.getLogger(Train.class.getName()).log(Level.INFO, "Wagons created: {0} ", wagonCount);
    }
    
    /**
     * controls that no more than 40 wagons are build.
     * @throws MaxWagonCountReached 
     */
    private static void increaseWagonCount() throws MaxWagonCountReached {
        if (wagonCount < MAX_WAGON_COUNT) {
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
  
//    delete if not needed
//    public List<Passenger> dropOffPassengers(){
//        List<Passenger> returnValue = passengers;
//        passengers = null;
//        return returnValue;
//    }
    
    public void printPassengerList() {
        for (Passenger pas : getPassengers()) {
            Logger.getLogger(Train.class.getName()).log(Level.INFO, pas.getName(), "\t");
        }
    }

    public boolean seatfree() {
        if(passengers.size() < MAX_PASSENGER_CAPACITY){
            return true;
        }
        return false;
    }
}
