package ghosttrain;

import exceptions.MaxWagonCountReached;
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
        64800, 102900, 153600, 218700, 300000}; // 10
    private int indexPW = 0;
    private int[] costCoinsActivityWagon = {275, 1050, 2325, 4100, 6375, 9150, 12425,
        16200, 20475, 25250, 30525, 36300, 42575, 49350, 56625, 64400, 72675,
        81450, 90725, 100500, 110775, 121550, 132825, 144600, 156875, 169650,
        182925, 196700, 210975, 225750, 241025, 256800, 273075, 289850, 307125,
        324900, 343175, 361950, 381225, 401000}; // 40
    private int indexAW = 0;
    private int[] costCoinsEngine = {2400, 8100, 19200, 37500, 64800, 102900,
        153600, 218700}; // 9
    private int indexE = 0;
    private int[] costCoinsBucketUpgrade = {375, 563}; // 2

    public Store(Player player) {
        this.player = player;
    }

    public PassengerWagon buyPassengerWagon() {
        // determine costs
        int cost = costCoinsPassengerWagon[indexPW];
        // verify if enough coins are in the wallet to purchase a passenger wagon
        if (player.getWallet().getCoins() >= cost) {
            try {
                PassengerWagon pw = new PassengerWagon();
                // sub coins
                player.getWallet().subCoins(cost);
                indexPW++;
                System.out.println("bought passenger wagon");
                System.out.println();
                return pw;
            } catch (MaxWagonCountReached ex) {
                System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
            }
        } else {
            System.out.println("Not enough coins in the wallet to purchase a passenger wagon");
        }
        return null;
    }

    public FunWagon buyFunWagon() {
        // determine costs
        int cost = costCoinsActivityWagon[indexAW];
        // verify if enough coins are in the wallet to purchase a fun wagon
        if (player.getWallet().getCoins() >= cost) {
            try {
                FunWagon fw = new FunWagon();
                // sub coins
                player.getWallet().subCoins(cost);
                indexAW++;
                System.out.println("bought fun wagon");
                System.out.println();
                return fw;
            } catch (MaxWagonCountReached ex) {
                System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
            }
        } else {
            System.out.println("Not enough coins in the wallet to purchase a fun wagon");
        }
        return null;
    }

    public EatingWagon buyEatingWagon() {
        // determine costs
        int cost = costCoinsActivityWagon[indexAW];
        // verify if enough coins are in the wallet to purchase a eating wagon
        if (player.getWallet().getCoins() >= cost) {
            try {
                EatingWagon ew = new EatingWagon();
                // sub coins
                player.getWallet().subCoins(cost);
                indexAW++;
                System.out.println("bought eating wagon");
                System.out.println();
                return ew;
            } catch (MaxWagonCountReached ex) {
                System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
            }
        } else {
            System.out.println("Not enough coins in the wallet to purchase a eating wagon");
        }
        return null;
    }

    public TrainingWagon buyTrainingWagon() {
        // determine costs
        int cost = costCoinsActivityWagon[indexAW];
        // verify if enough coins are in the wallet to purchase a training wagon
        if (player.getWallet().getCoins() >= cost) {
            try {
                TrainingWagon tw = new TrainingWagon();
                // sub coins
                player.getWallet().subCoins(cost);
                indexAW++;
                System.out.println("bought training wagon");
                System.out.println();
                return tw;
            } catch (MaxWagonCountReached ex) {
                System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
            }
        } else {
            System.out.println("Not enough coins in the wallet to purchase a training wagon");
        }
        return null;
    }

    public void buyEngine() {
        // determine costs
        int cost = costCoinsEngine[indexE];
        // verify if enough coins are in the wallet to purchase an engine
        if (player.getWallet().getCoins() >= cost) {
            player.getTrain().getEngine().engineUpgrade();
            // sub coins
            player.getWallet().subCoins(cost);
            indexE++;
            System.out.println("bought engine");
        } else {
            System.out.println("Not enough coins in the wallet to purchase an engine");
        }
    }

    /**
     * have to know which upgrade the bucket already has
     */
    public void buyBucketUpgrade(ActivityWagon aw) {
        // determine costs
        int cost = costCoinsBucketUpgrade[aw.getBucket().getNumberOfUpgrade()];
        System.out.println("UpgradeNumber " + aw.getBucket().getNumberOfUpgrade());
        // verify if enough coins are in the wallet to purchase an engine
        if (player.getWallet().getCoins() >= cost) {
            aw.bucketUpgrade();
            // sub coins
            player.getWallet().subCoins(cost);
            System.out.println("bought bucket upgrade");
        } else {
            System.out.println("Not enough coins in the wallet to purchase an engine");
        }
    }
}
