package ghosttrain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
        player.buyActivityWagon();
    }
}
