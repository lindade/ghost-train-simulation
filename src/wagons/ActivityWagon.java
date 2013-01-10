package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Bucket;

/**
 *
 * @author Linda
 */
public abstract class ActivityWagon extends Wagon {
 
    protected Bucket bucket;
    //protected String name;

    public ActivityWagon() throws MaxWagonCountReached {
        super();
        bucket = new Bucket();
    }

    abstract void fillBucket();

  //  abstract String printName();

    abstract int demandEarning();
    
    public Bucket getBucket(){
        return bucket;
    }
    
    
}
