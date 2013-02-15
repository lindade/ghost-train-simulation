package ghosttrain;

import exceptions.MaxPassengerCapacityReachedException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.ActivityWagon;
import wagons.PassengerWagon;
import wagons.Wagon;

/**
 * the simulation loop: at destination                          traveling
 * 1 Passenger Boarding
 * 2 sort Passengers into AW
 * 3 sort remaining Passengers into PW
 * 4 start train                                                5 produce income
 *                                                              6 collect income
 *                                                              7 sort Passengers into PW
 * 8 Passenger getOff train from PW
 * 9 cash check
 * 10 buy upgrades
 *
 * @author Linda
 */
public class GhostTrain {

    private static final Logger log = Logger.getLogger(GhostTrain.class.getSimpleName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MaxPassengerCapacityReachedException {
        Player player = new Player();
        PassengerFactory pF = new PassengerFactory();
        log.setLevel(Level.OFF);

        //print level
        player.getLevel();

        //print current destination name
        //System.out.println("" + player.getTrain().getCurrentDestination().getName());

        //print the engines quantity of wagons
        //System.out.println("engine quantity: " + player.getTrain().getEngine().getQuantityOfWagons());
        log.log(Level.INFO, "engine quantity: {0} ", player.getTrain().getEngine().getQuantityOfWagons());
        player.loadPassengers(); // should put some passengers in the passenger wagons
        player.staffActivityWagon(); // should put some passengers into the activity wagons
        player.getTrain().getActivityWagons();
        player.getTrain().getPassengerWagons();
        
        // fill Passengers into passenger wagons
        for (Wagon w : player.getTrain().getWagons()) {
            if (w instanceof PassengerWagon) {
                // einstieg, ausstieg
                while (w.getPassengers().size() < 3) {
                    w.addPassenger(pF.createPassenger(player.getTrain().getSchedule()));
                }
            }
        }
        
        //ausgeben passagierliste...
        // .> umsortieren
//        for( Wagon w : player.getTrain().getWagons() ) {
//            w.printPassengerList();
//        }
//        PassengerSorter sorter = new PassengerSorter(player.getTrain());
//        sorter.sortRandomInWagon();
//        //wieder ausgeben
//        for( Wagon w : player.getTrain().getWagons() ) {
//            w.printPassengerList();
//        }
        
        player.getTrain().enterNextCity(); // the train approaches the next city

//        final String KEY = "Limbo"; // für benutzereingabe
//        if( Schedule.doesDestinationExist(KEY) ) {
//            Schedule.DESTINATIONS.get(KEY);
//        }

        log.log(Level.INFO, "Current city: {0}", player.getTrain().getCurrentDestination().getName());
        log.log(Level.INFO, "Next city: {0}", player.getTrain().getNextDestination().getName());
        player.getTrain().dropOffPassenger();
        player.collectIncome();

        // buy things. Test if the limit of the engine works
//        player.buyPassengerWagon();
//        player.buyPassengerWagon(); // this should not work because of the wagon limitation
        player.buyEngine(); // buy engine upgrade
        
        // upgrade activity wagons buckets
        for (Wagon w : player.getTrain().getWagons()) {
            if (w instanceof ActivityWagon) {
                ActivityWagon aw = (ActivityWagon) w;
                player.buyBucketUpgrade(aw);
                player.buyBucketUpgrade(aw);
                player.buyBucketUpgrade(aw); // this should not work because of the upgrade limitation 
            }
        }
        
//        player.buyFunWagon();
//        player.buyEatingWagon();
//        player.buyTrainingWagon();
//        player.buyPassengerWagon(); // this should not work because of the wagon limitation
//        player.buyEngine(); // buy engine upgrade should not work
        player.getWallet().getCoins();

        /**
         * add 3 passengers to the empty passenger wagons the
         * activity wagon is still empty
         */
        for (Wagon w : player.getTrain().getWagons()) {
            if (w instanceof PassengerWagon && w.seatfree()) {
                // einstieg, ausstieg
                while (w.getPassengers().size() < 3) {
                    w.addPassenger(pF.createPassenger(player.getTrain().getSchedule()));
                }
            }
        }

        // test the schedule, drive through all available destinations
        int trips = 4;
        for (int i = 0; i < trips; i++) {
            // train approaches at next destination
            player.getTrain().enterNextCity();
            log.log(Level.INFO, "Current city: {0}", player.getTrain().getCurrentDestination().getName());
            log.log(Level.INFO, "Next city: {0}", player.getTrain().getNextDestination().getName());
            player.getTrain().dropOffPassenger();
//            for (Wagon w : player.getTrain().getWagons()) {
//                if (w instanceof PassengerWagon && w.seatfree()) {
//                    // einstieg, ausstieg
//                    while (w.getPassengers().size() < 3) {
//                        w.addPassenger(pF.createPassenger(player.getTrain().getSchedule()));
//                    }
//                }
//            }
            player.getLevel();
        }
    }
}