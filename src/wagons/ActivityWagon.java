package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Bucket;

/**
 *
 * @author Linda
 */
public abstract class ActivityWagon extends Wagon {
 
    protected Bucket bucket;

    public ActivityWagon() throws MaxWagonCountReached {
        super();
        bucket = new Bucket();
    }

    public abstract void fillBucket();

  //  abstract String printName();

    abstract int demandEarning();
    
    public Bucket getBucket(){
        return bucket;
    }
    
    public void bucketUpgrade(){
        bucket.upgrade();
    }
}
