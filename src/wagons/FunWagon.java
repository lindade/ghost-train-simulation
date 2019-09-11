package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;
import ghosttrain.Train;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class FunWagon extends ActivityWagon {

    private static final Logger log = Logger.getLogger(FunWagon.class.getName());

    
    public FunWagon() throws MaxWagonCountReached {
        super();
    }

    @Override
    public void fillBucket() {
        bucket.fillBucket(demandEarning());
    }

    @Override
    public int demandEarning() {
        int earnings = 0;
        for (Passenger p : getPassengers()) {
            earnings += p.getFunValue();
        }
        log.log(Level.FINEST, "Fun earnings: {0}", earnings);
        return earnings;
    }

}
