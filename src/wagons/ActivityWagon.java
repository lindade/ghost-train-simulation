package wagons;

import ghosttrain.Bucket;

/**
 *
 * @author Linda
 */
public abstract class ActivityWagon extends Wagon {

    protected Bucket bucket;
    protected String name;

    abstract void fillBucket();

    abstract void printName();

    abstract int demandEarning();
}
