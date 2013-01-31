package ghosttrain;

/**
 *
 * @author Linda
 */
public class Bucket {

    private int capacity;
    private int[] capacityUpgrade = {270, 540, 2160};
     private int numberOfUpgrade = 0;
    /**
     * the first number: 270 is just a placeholder
     * As a result the index is the same like the number of the current bucketUpgrade
     * 270 default value
     * 540 first upgrade
     * 2160 second upgrade
     * 4320 third upgrade only with premium credits
     * 12960 fourth upgrade only with premium credits
     */
    private int upgradeIndex = 1;
    private int content;

    public Bucket() {
        capacity = 270;
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
        capacity = capacityUpgrade;
    }

    public int getNumberOfUpgrade() {
        return numberOfUpgrade;
    }
}