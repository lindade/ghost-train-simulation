package ghosttrain;

import exceptions.MaxWagonCountReached;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.ActivityWagon;
import wagons.EatingWagon;
import wagons.FunWagon;
import wagons.PassengerWagon;
import wagons.TrainingWagon;

/**
 *
 * @author Linda
 */
public class Store {

    private Player player;
    private int[] costCoinsPassengerWagon = {300, 2400, 8100, 19200, 37500,
        64800, 102900, 153600, 218700, 300000}; // lenght=10 lenght=9
    private int indexPW = 0;
    private int[] costCoinsActivityWagon = {275, 1050, 2325, 4100, 6375, 9150, 12425,
        16200, 20475, 25250, 30525, 36300, 42575, 49350, 56625, 64400, 72675,
        81450, 90725, 100500, 110775, 121550, 132825, 144600, 156875, 169650,
        182925, 196700, 210975, 225750, 241025, 256800, 273075, 289850, 307125,
        324900, 343175, 361950, 381225, 401000}; // lenght=40 lenght=39
    private int indexAW = 0;
    private int[] costCoinsEngine = {2400, 8100, 19200, 37500, 64800, 102900,
        153600, 218700}; // lenght=9 lenght=8
    private int indexE = 0;
    private int[] costCoinsBucketUpgrade = {375, 563}; // lenght=2 index=1
    private static final Logger log = Logger.getLogger(Store.class.getName());

    public Store(Player player) {
        this.player = player;
    }

    public PassengerWagon buyPassengerWagon() {
        if(indexPW < costCoinsPassengerWagon.length){
            // determine costs
            int cost = costCoinsPassengerWagon[indexPW];
            // verify if enough coins are in the wallet to purchase a passenger wagon
            if (player.getWallet().getCoins() >= cost) {
                try {
                    PassengerWagon pw = new PassengerWagon();
                    // sub coins
                    player.getWallet().subCoins(cost);
                    indexPW++;
                    log.info("bought passenger wagon\n");
                    return pw;
                } catch (MaxWagonCountReached ex) {
                    log.log(Level.INFO,"Maximum number of wagons reached!" + " ex: {0}", ex.getMessage());
                }
            } else {
                log.info("Not enough coins in the wallet to purchase a passenger wagon");
            }
        }
        return null;
    }

    public FunWagon buyFunWagon() {
        if(indexAW < costCoinsActivityWagon.length){
            // determine costs
            int cost = costCoinsActivityWagon[indexAW];
            // verify if enough coins are in the wallet to purchase a fun wagon
            if (player.getWallet().getCoins() >= cost) {
                try {
                    FunWagon fw = new FunWagon();
                    // sub coins
                    player.getWallet().subCoins(cost);
                    indexAW++;
                    log.info("bought fun wagon\n");
                    return fw;
                } catch (MaxWagonCountReached ex) {
                    log.log(Level.INFO,"Maximum number of wagons reached!" + " ex: {0}", ex.getMessage());
                }
            } else {
                log.info("Not enough coins in the wallet to purchase a fun wagon");
            }
        }
        return null;
    }

    public EatingWagon buyEatingWagon() {
        if(indexAW < costCoinsActivityWagon.length){
            // determine costs
            int cost = costCoinsActivityWagon[indexAW];
            // verify if enough coins are in the wallet to purchase a eating wagon
            if (player.getWallet().getCoins() >= cost) {
                try {
                    EatingWagon ew = new EatingWagon();
                    // sub coins
                    player.getWallet().subCoins(cost);
                    indexAW++;
                    log.info("bought eating wagon\n");
                    return ew;
                } catch (MaxWagonCountReached ex) {
                    log.log(Level.INFO,"Maximum number of wagons reached!" + " ex: {0}", ex.getMessage());
                }
            } else {
                log.info("Not enough coins in the wallet to purchase a eating wagon");
            }
        }
        return null;
    }

    public TrainingWagon buyTrainingWagon() {
        if(indexAW < costCoinsActivityWagon.length){
            // determine costs
            int cost = costCoinsActivityWagon[indexAW];
            // verify if enough coins are in the wallet to purchase a training wagon
            if (player.getWallet().getCoins() >= cost) {
                try {
                    TrainingWagon tw = new TrainingWagon();
                    // sub coins
                    player.getWallet().subCoins(cost);
                    indexAW++;
                    log.info("bought training wagon\n");
                    return tw;
                } catch (MaxWagonCountReached ex) {
                    log.log(Level.INFO,"Maximum number of wagons reached!" + " ex: {0}", ex.getMessage());
                }
            } else {
                log.info("Not enough coins in the wallet to purchase a training wagon");
            }
        }
        return null;
    }

    public boolean buyEngine() {
        if(indexE < costCoinsEngine.length){
            // determine costs
            int cost = costCoinsEngine[indexE];
            // verify if enough coins are in the wallet to purchase an engine
            if (player.getWallet().getCoins() >= cost) {
                player.getTrain().getEngine().engineUpgrade();
                // sub coins
                player.getWallet().subCoins(cost);
                indexE++;
                log.info("bought engine");
                return true;
            } else {
                log.info("Not enough coins in the wallet to purchase an engine");
            }
        }
        return false;
    }

    public boolean buyBucketUpgrade(ActivityWagon aw) {
        // check the Upgrade the bucket already has
        if(aw.getBucket().getNumberOfUpgrade() < costCoinsBucketUpgrade.length){
            // determine costs
            int cost = costCoinsBucketUpgrade[aw.getBucket().getNumberOfUpgrade()];
//            log.info("UpgradeNumber " + aw.getBucket().getNumberOfUpgrade());
            // verify if enough coins are in the wallet to purchase a bucket upgrade
            if (player.getWallet().getCoins() >= cost) {
//                log.info("AW capacity before upgrade: " + aw.getBucket().getCapacity());
                aw.bucketUpgrade();
//                log.info("UpgradeNumber " + aw.getBucket().getNumberOfUpgrade());
                // sub coins
                player.getWallet().subCoins(cost);
                log.info("bought bucket upgrade");
//                log.info("AW capacity after upgrade: " + aw.getBucket().getCapacity());
                return true;
            } else {
                log.info("Not enough coins in the wallet to purchase a bucket upgrade");
                return false;
            }
        } else {
            log.info("Maximum Upgrade is reached.");
            return false;
        }
    }
}
