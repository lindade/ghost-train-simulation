package ghosttrain;

import exceptions.MaxPassengerCapacityReachedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import wagons.Wagon;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.EatingWagon;
import wagons.FunWagon;
import wagons.PassengerWagon;
import wagons.TrainingWagon;

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
 * @author
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
    private static final Logger log = Logger.getLogger(PassengerSorter.class.getName());
    private HashMap<Passenger, Wagon> waitingHall = new HashMap<>(0);

    public PassengerSorter(Train train) {
        this.train = train;
        this.ratioWagons = train.getWagons();
    }

    /**
     * remove or edit?
     *
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

    public void sortExperienceOriented() {
        //maximize deboardings
        waitingHall.clear();
        Destination destination = train.getNextDestination();
        //print destination cities:
        
//        System.out.println("Destination: " + train.getNextDestination().getName());
//        System.out.println("\n\npre sorting [");
//        for( Wagon w : train.getWagons() ) {
//            for( Passenger p : w.getPassengers() ) {
//                System.out.print(" " + p.getDeboarding().getName() + " ");
//            }
//        }
//        System.out.println("]---");
        LinkedList<Passenger> wantsToDeboard = new LinkedList<>();
        LinkedList<Passenger> others = new LinkedList<>();
        for( Wagon w : train.getWagons() ) {
            for( Passenger p: w.getPassengers() ) {
                if( p.getDeboarding().equals(destination)) {
                    wantsToDeboard.add(p);
                }
                else {
                    others.add(p);
                }
            }        
            w.clearPassengers();
        }
        for( Wagon w : train.getWagons() ) {
            if( w instanceof PassengerWagon && !wantsToDeboard.isEmpty()) {
                while( w.seatfree() && !wantsToDeboard.isEmpty() ) {
                    try {
                        w.addPassenger(wantsToDeboard.removeFirst());
                    } catch (MaxPassengerCapacityReachedException ex) {
                        Logger.getLogger(PassengerSorter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                while( w.seatfree() && !others.isEmpty()) {
                    try {
                        w.addPassenger(others.remove());
                    } catch (MaxPassengerCapacityReachedException ex) {
                        Logger.getLogger(PassengerSorter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //when wantsToDeboard is empty, but passengerwagon is not full
            } else {
                while( w.seatfree() && !others.isEmpty() ) {
                    try {
                        w.addPassenger(others.removeFirst());
                    } catch (MaxPassengerCapacityReachedException ex) {
                        Logger.getLogger(PassengerSorter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
        }
//        System.out.println("Destination: " + train.getNextDestination().getName());
//        System.out.print("\n\npost sorting [");
//        for( Wagon w : train.getWagons() ) {
//            for( Passenger p : w.getPassengers() ) {
//                System.out.print(" " + p.getDeboarding().getName() + " ");
//            }
//        }
//        System.out.println("] ---");
    }

    public void sortCoinOriented() {
        //maximize income
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
        ArrayList<Passenger> suffledPassengers = new ArrayList<>();
        while (!passengerSorter.isEmpty()) {
            int randomPassenger = r.nextInt(passengerSorter.size());
            Passenger p = passengerSorter.get(randomPassenger);
            suffledPassengers.add(p);
            passengerSorter.remove(p);
        }

        int currentPassengerIndex = 0;
        for (Wagon w : ratioWagons) {
            for (int i = currentPassengerIndex; i < currentPassengerIndex + 3; i++) {
                try {
                    w.addPassenger(suffledPassengers.get(i));
                } catch (MaxPassengerCapacityReachedException ex) {
                    log.info("Tried to add too many passengers...");
                }
            }
            currentPassengerIndex += 3;
            if (currentPassengerIndex >= suffledPassengers.size()) {
                break;
            }
        }
    }

//    public void sortToEatingWagon(Passenger p){
//        int i = 0;
//        int passengersInWagon = 0;
//        EatingWagon ew = train.getEatingWagons().get(i);
//        try {
//            ew.addPassenger(p);
//            passengersInWagon++;
//            if(passengersInWagon == 3){
//                i++;
//                passengersInWagon = 0;
//            }
//        } catch (MaxPassengerCapacityReachedException ex) {
//            log.info("Tried to add too many passengers...");
//        }
//    }
    public boolean sortToEatingWagon(Passenger p) {
        boolean added = false;
        for (EatingWagon ew : train.getEatingWagons()) {
            if (addPassengerToWagon(p, ew)) {
                added = true;
                break;
            }
        }
        return added;
    }

    public boolean sortToFunWagon(Passenger p) {
        boolean added = false;
        for (FunWagon fw : train.getFunWagons()) {
            if (addPassengerToWagon(p, fw)) {
                added = true;
                break;
            }
        }
        return added;
    }

    public boolean sortToTrainingWagon(Passenger p) {
        boolean added = false;
        for (TrainingWagon tw : train.getTrainingWagons()) {
            if (addPassengerToWagon(p, tw)) {
                added = true;
                break;
            }
        }
        return added;
    }

    public boolean sortToPassengerWagon(Passenger p) {
        boolean added = false;
        for (PassengerWagon pw : train.getPassengerWagons()) {
            if (addPassengerToWagon(p, pw)) {
                added = true;
                break;
            }
        }
        return added;
    }

    public boolean addPassengerToWagon(Passenger p, Wagon w) {
        try {
            w.addPassenger(p);
            return true;
        } catch (MaxPassengerCapacityReachedException ex) {
            return false;
        }
    }
}
