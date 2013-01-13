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

    public void fillBucket(int coins) {
        /**
         * Depending on the parameter of the passengers the content has to be
         * raised until the capacity of the bucket is exhausted
         *
         * here has to be a timer which controls the time the bucket gets filled
         * regularly
         */
        content += coins;
        if (content > capacity) {
            content = capacity;
        }
    }

    public int emtpyBucket() {
        int theContent = content;
        if (content != 0) {
            content = 0;
        } else {
            System.out.println("bucket is still empty, no Coins to be saved");
        }
        return theContent;
    }

    public int getContent() {
        return content;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacityUpgrade) {
        capacity += capacityUpgrade;
    }
}
