package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Bucket;

/**
 *
 * @author Linda
 */
public abstract class ActivityWagon extends Wagon {

    public ActivityWagon() throws MaxWagonCountReached {
        super();
    }

     
    
    protected Bucket bucket;
    protected String name;

    abstract void fillBucket();

    abstract String printName();

    abstract int demandEarning();
    
    
}
