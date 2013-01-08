package ghosttrain;

import java.util.ArrayList;
import java.util.List;
import wagons.Wagon;

/**
 *
 * @author Linda
 */
public class PassengerSorter {

    List<Passenger> passengerSorter = new ArrayList<Passenger>();
    List<Wagon> wagons;

    /**
     * this method is already in wagon
     * delete or edit?
     * @param p 
     */
    public void addToWagon(Passenger p) {
        if (passengerSorter.size() <= 2) {
            passengerSorter.add(p);
            System.out.println("Passenger added to List");
        } else {
            System.out.println("Wagon capacity is already exhausted");
        }
    }
}
