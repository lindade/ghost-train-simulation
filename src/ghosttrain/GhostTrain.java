package ghosttrain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * the simulation loop:
 * at destination                                               traveling
 * 1 Passenger Boarding
 * 2 sort Passengers into AW
 * 3 sort remaining Passengers into PW
 * 4 start train
 *                                                             5 produce income
 *                                                             6 collect income
 *                                                             7 sort Passengers into PW
 * 8 Passenger getOff train from PW
 * 9 cash check
 *10 buy upgrades
 * @author Linda
 */
public class GhostTrain {
    private static final Logger log = Logger.getLogger(GhostTrain.class.getSimpleName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Player player = new Player();
        Train train = new Train();
//        log.setLevel(Level.OFF);
        player.loadPassengers();
        player.staffActivityWagon();
        log.log(Level.INFO, "Current city: {0}", train.getCurrentDestination().getName());
        train.enterNextCity();
        log.log(Level.INFO, "2nd city: {0}", train.getCurrentDestination().getName());
        train.dropOffPassenger();
        player.collectIncome();
        player.buyPassengerWagon();
        player.buyEatingWagon();
    }
}
