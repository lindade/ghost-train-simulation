package ghosttrain;

import exceptions.MaxPassengerCapacityReachedException;
import java.util.ArrayList;
import java.util.List;
import wagons.Wagon;
import java.util.Random;

/**
 * This class should sort the passengers into the wagons The Passengers with the
 * highest value should be sorted into the particular wagon of that value to
 * earn the highest possible amount of coins
 *
 * One other method puts the passengers randomly into the wagons
 *
 * Before the arrival of the train in the station the passengers who can get off
 * the train will be placed into an passenger wagon.
 *
 * @author Linda
 */
public class PassengerSorter {

    /**
     * The list of wagons contains all wagons which are owned by the player
     *
     * PassengerSorter contains all passengers who are in the train AND the new
     * passengers who are boarding the train.
     */
    private Train train;
    private List<Wagon> ratioWagons;
    private List<Passenger> passengerSorter = new ArrayList<>();
    private Random r = new Random();

    public PassengerSorter(Train train) {
        this.train = train;
        this.ratioWagons = train.getWagons();
    }

    /**
     * remove or edit?
     * @param p
     */
//    public void addToWagon(Passenger p) {
//         train.getPassengerWagons();
//    }
    
    /**
     * this method has to be optimized sort passengers into activity wagons
     *
     * @param passengerList
     */
    public void sortOptimalInWagon(List<Passenger> passengerList) {
        for (Passenger p : passengerList) {
            if ((p.getFunValue() >= p.getEatingValue()) && (p.getFunValue() >= p.getTrainingValue())) {
                //train.getActivityWagons(); need funwagon not general activity wagon
            }
            if (p.getEatingValue() >= p.getTrainingValue()) {
                //in EatingWagon
            } else {
                // in TrainingWagon
            }
        }
    }

    public void sortRandomInWagon() {
        for (Wagon w : ratioWagons) {
            ArrayList<Passenger> inWagon = new ArrayList<>();
            for (Passenger p : w.getPassengers()) {
                inWagon.add(p);
            }
            for (Passenger p : inWagon) {
                w.removePassenger(p);
            }
            passengerSorter.addAll(inWagon);
        }
        ArrayList<Passenger> mixedPassengers = new ArrayList<>();
        while (!passengerSorter.isEmpty()) {
            int randomPassenger = r.nextInt(passengerSorter.size());
            Passenger p = passengerSorter.get(randomPassenger);
            mixedPassengers.add(p);
            passengerSorter.remove(p);
        }

        int currentPassengerIndex = 0;
        for (Wagon w : ratioWagons) {
            for (int i = currentPassengerIndex; i < currentPassengerIndex + 3; i++) {
                try {
                    w.addPassenger(mixedPassengers.get(i));
                } catch (MaxPassengerCapacityReachedException ex) {
                    System.err.println("Tried to add too many passengers...");
                }
            }
            currentPassengerIndex += 3;
            if( currentPassengerIndex >= mixedPassengers.size() ) break;
        }

    }
}
