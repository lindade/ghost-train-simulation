package ghosttrain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class Bucket {

    private int capacity;
    public static int[] capacityUpgrade = {270, 540, 2160}; 
     private int numberOfUpgrade = 0;
     private static final Logger log = Logger.getLogger(Bucket.class.getName());
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
        log.log(Level.FINEST, "bucket content: {0} ", content);
    }

    public int emtpyBucket() {
        int theContent = content;
        if (content != 0) {
            content = 0;
        } else {
            log.log(Level.FINEST, "bucket is still empty, no Coins to be saved");
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
//            log.info(String.format( "Bucket upgrade: numberOfUpgrade=%d , capacity=%d", numberOfUpgrade, capacity ));
        } else {
            log.info("no upgrade available");
        }
    }
}