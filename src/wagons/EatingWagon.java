package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;

/**
 *
 * @author Linda
 */
public class EatingWagon extends ActivityWagon {

    public EatingWagon() throws MaxWagonCountReached {
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
            earnings += p.getEatingValue();
        }
        System.out.println("Eating earnings: " + earnings);
        return earnings;
    } 
}
