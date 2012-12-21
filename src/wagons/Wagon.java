package wagons;

import ghosttrain.Passenger;
import java.util.List;

/**
 *
 * @author Linda
 */
public abstract class Wagon {

    protected List<Passenger> passengers;
    protected static int wagonCount = 0;

    public void getWagonCount() {
        System.out.println("Wagons created: " + wagonCount);
    }

    abstract void addPassenger(Passenger p);
    
    abstract void printList();
}
