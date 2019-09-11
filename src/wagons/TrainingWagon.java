package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class TrainingWagon extends ActivityWagon {
    
    private static final Logger log = Logger.getLogger(TrainingWagon.class.getName());


    public TrainingWagon() throws MaxWagonCountReached {
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
            earnings += p.getTrainingValue();
        }
        log.log(Level.FINEST, "Training earnings: {0}", earnings);
        return earnings;
    }

}