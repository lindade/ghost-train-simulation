package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;

/**
 *
 * @author Linda
 */
public class FunWagon extends ActivityWagon {

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
        System.out.println("Fun earnings: " + earnings);
        return earnings;
    }

}
