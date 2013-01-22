package ghosttrain;

import exceptions.MaxPassengerCapacityReachedException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.PassengerWagon;
import wagons.Wagon;

/**
 * the simulation loop: 
 * at destination                                       traveling
 * 1 Passenger Boarding 
 * 2 sort Passengers into AW
 * 3 sort remaining Passengers into PW
 * 4 start train                                        5 produce income
 *                                                      6 collect income
 *                                                      7 sort Passengers into PW
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
        log.setLevel(Level.ALL);
        player.getLevel();

        player.loadPassengers(); // should put some passengers in the train
        player.staffActivityWagon();
        int count = 0;
        int count1 = 3;
        int count2 = 6;
        for (Wagon w : player.getTrain().getWagons()) {
            w.addPassenger(new Passenger("name" + count++, (int) Math.random() * 3, (int) Math.random() * 3, (int) Math.random() * 3, player.getTrain().getNextDestination()));
            w.addPassenger(new Passenger("name" + count1++, (int) Math.random() * 3, (int) Math.random() * 3, (int) Math.random() * 3, player.getTrain().getNextDestination()));
            w.addPassenger(new Passenger("name" + count2++, (int) Math.random() * 3, (int) Math.random() * 3, (int) Math.random() * 3, player.getTrain().getNextDestination()));
        }
        player.getTrain().enterNextCity();
        log.log(Level.INFO, "Current city: {0}", player.getTrain().getCurrentDestination().getName());
        log.log(Level.INFO, "Next city: {0}", player.getTrain().getNextDestination().getName());
        player.getTrain().dropOffPassenger();
        player.collectIncome();
        player.buyPassengerWagon();
        player.buyEatingWagon();

        /**
         * here I just add passengers to the empty passengerWagons
         * it is a bug to leave the new activityWagons empty
         * they should also be filled with new passengers if i would fill in
         * passengers into every empty wagon 
         * the activity wagon where nobody got off from is still full with
         * passengers
         * each passenger wagon is filled with the same instance of an passenger
         */
        for (Wagon w : player.getTrain().getWagons()) {
            if (w instanceof PassengerWagon) {
                w.addPassenger(new Passenger("name" + count++, (int) Math.random() * 3, (int) Math.random() * 3, (int) Math.random() * 3, player.getTrain().getNextDestination()));
                w.addPassenger(new Passenger("name" + count1++, (int) Math.random() * 3, (int) Math.random() * 3, (int) Math.random() * 3, player.getTrain().getNextDestination()));
//                w.addPassenger(new Passenger("name" + count2++, (int) Math.random() * 3, (int) Math.random() * 3, (int) Math.random() * 3, player.getTrain().getNextDestination()));
            }
        }

        // train approaches at next destination
        player.getTrain().enterNextCity();
        log.log(Level.INFO, "Current city: {0}", player.getTrain().getCurrentDestination().getName());
        log.log(Level.INFO, "Next city: {0}", player.getTrain().getNextDestination().getName());
        player.getTrain().dropOffPassenger();

        // test the schedule
        for (int i = 0; i < 4; i++) {
            player.getTrain().enterNextCity();
            log.log(Level.INFO, "Current city: {0}", player.getTrain().getCurrentDestination().getName());
            log.log(Level.INFO, "Next city: {0}", player.getTrain().getNextDestination().getName());
        }
        player.getLevel();
    }
}