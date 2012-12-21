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
        // has to be limited to 3 persons
        passengers.add(p);
        // just for the output on the console
        System.out.print("Passenger wagon List: ");
        this.printList();
        System.out.println();
    }

    public void getOff(Destination currentDest) {
        // drop off passenger at correct destination
        for (Passenger p : passengers) {
            if (p.getDeboarding() == currentDest) {
                System.out.println("Passenger " + p.getName() + " has deboarded at " + currentDest.getName() + ".");
                passengers.remove(p);
                offBoardedPassengersCount++;
            } else {
                System.out.println("Passenger " + p.getName() + " has not deboarded. Requested deboard is at " + p.getDeboarding().getName() + ".");
            }
        }
    }

    @Override
    void printList() {
        for (Passenger pas : passengers) {
            System.out.print(pas.getName() + "\t");
        }
    }
}
