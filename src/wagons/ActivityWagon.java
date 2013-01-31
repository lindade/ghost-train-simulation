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

    abstract void fillBucket();

  //  abstract String printName();

    abstract int demandEarning();
    
    public Bucket getBucket(){
        return bucket;
    }
    
    public void bucketUpgrade(int upgrade){
        bucket.setCapacity(upgrade);
    }

    public int getNumberOfUpgrade() {
        return bucket.getNumberOfUpgrade();
    }
}
