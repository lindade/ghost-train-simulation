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
     * As a result the index is the same like the number of the current bucketUpgrade
     * 270 default value
     * 540 first upgrade
     * 2160 second upgrade
     * 4320 third upgrade only with premium credits
     * 12960 fourth upgrade only with premium credits
     */
    private int content;

    public Bucket() {
        capacity = capacityUpgrade[0];
        content = 0;
    }

    public void fillBucket(int coins) {
        /**
         * Depending on the parameter of the passengers the content has to be
         * raised until the capacity of the bucket is exhausted
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
            //System.out.println("bucket is still empty, no Coins to be saved");
        }
        return theContent;
    }

    public int getContent() {
        return content;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfUpgrade() {
        return numberOfUpgrade;
    }
    
    public void upgrade() {
        if(numberOfUpgrade < capacityUpgrade.length) {
            numberOfUpgrade++;
            capacity = capacityUpgrade[numberOfUpgrade];
        } else {
            System.out.println("no upgrade available");
        }
    }
}