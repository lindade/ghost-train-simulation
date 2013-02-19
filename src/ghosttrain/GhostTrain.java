package ghosttrain;

import exceptions.MaxPassengerCapacityReachedException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.EatingWagon;
import wagons.Wagon;

/**
 * the simulation loop: at destination                          traveling
 * 1 Passenger Boarding (train)
 * 2 sort Passengers into AW (player)
 * 3 sort remaining Passengers into PW (player)
 * 4 start train (player)                                       5 produce income (train)
 *                                                              6 collect income (player)
 *                                                              7 sort Passengers into PW (player)
 * 8 Passenger getOff train from PW (train)
 * 9 cash check (train)
 * 10 buy upgrades (player)
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
        log.setLevel(Level.OFF);


        //print the engines quantity of wagons
        //System.out.println("engine quantity: " + player.getTrain().getEngine().getQuantityOfWagons());
        log.log(Level.INFO, "engine quantity: {0} ", player.getTrain().getEngine().getQuantityOfWagons());
        
        
        

//        final String KEY = "Limbo"; // für benutzereingabe
//        if( Schedule.doesDestinationExist(KEY) ) {
//            Schedule.DESTINATIONS.get(KEY);
//        }

        
        

        // test the schedule, drive through all available destinations
        int trips = 4;
        for (int i = 0; i < trips; i++) {
            // in destination
            
            //print current destination name
            //System.out.println("current destination: " + player.getTrain().getCurrentDestination().getName());
            player.getLevel();
            player.getTrain().getPassengerWagons();
            List<EatingWagon> eatingWagons = player.getTrain().getEatingWagons();
            eatingWagons.get(0).fillBucket();
            player.getTrain().getFunWagons();
            player.getTrain().getTrainingWagons(); 
            player.getTrain().loadPassengers(); // put some passengers in the passenger wagons
            
            // on the road
            player.collectIncome();
            
            //switch passengers
            //print passengerList...
            System.out.println("old order");
            for( Wagon w : player.getTrain().getWagons() ) {
                w.printPassengerList();
            }
            PassengerSorter sorter = new PassengerSorter(player.getTrain());
            // -> shuffle
            sorter.sortRandomInWagon();
            //print passengerList...
            System.out.println("new order");
            for( Wagon w : player.getTrain().getWagons() ) {
                w.printPassengerList();
            }
            
            // train approaches at next destination
            player.getTrain().enterNextCity();
            System.out.println("current destination: " + player.getTrain().getCurrentDestination().getName());
            //System.out.println("next destination" + player.getTrain().getNextDestination().getName());
            player.getTrain().dropOffPassenger();
            // buy things. Test if the limit of the engine works
//            player.getWallet().getCoins();
            player.buyPassengerWagon();
//            player.buyPassengerWagon(); // this should not work because of the wagon limitation
            player.buyEngine(); // buy engine upgrade
//            player.buyFunWagon();
//            player.buyEatingWagon();
            player.buyTrainingWagon();
//            player.buyPassengerWagon(); // this should not work because of the wagon limitation
//            player.buyEngine(); // buy engine upgrade should not work
            // upgrade activity wagons buckets
//            for (Wagon w : player.getTrain().getWagons()) {
//                if (w instanceof ActivityWagon) {
//                    ActivityWagon aw = (ActivityWagon) w;
//                    player.buyBucketUpgrade(aw);
//                    player.buyBucketUpgrade(aw);
//                    player.buyBucketUpgrade(aw); // this should not work because of the upgrade limitation 
//                }
//            }
//            player.getWallet().getCoins();
            player.getLevel();
        }
    }
}