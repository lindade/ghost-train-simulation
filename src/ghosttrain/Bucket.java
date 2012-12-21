package ghosttrain;

/**
 *
 * @author Linda
 */
public class Bucket {

    private int capacity;
    private int content;

    public Bucket() {
        capacity = 10;
        content = 0;
    }

    public void fillBucket() {
        /**
         * Depending on the parameter of the passengers the content has to be
         * raised every minute until the capacity of the bucket is exhausted.
         */
        if (content <= capacity) {
            content++;
        }
    }
}
