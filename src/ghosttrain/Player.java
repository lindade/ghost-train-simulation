package ghosttrain;

import exceptions.MaxWagonCountReached;
import wagons.ActivityWagon;
import wagons.EatingWagon;
import wagons.FunWagon;
import wagons.PassengerWagon;
import wagons.TrainingWagon;
import wagons.Wagon;

/**
 *
 * @author Linda
 */
public class Player implements LevelListener {

    protected int level;
    private LevelAdmin la;
    private Wallet wallet;
    private Train train;

    public Player() {
        la = new LevelAdmin();
        level = la.getLevel(); // from the start 1
        wallet = new Wallet();
        train = new Train(la);
    }

    public int getLevel() {
        System.out.println("level: "+ level);
        return level;
    }
    
    public void setLevel(int newlevel){
        this.level = newlevel;
    }
    
     public Train getTrain() {
        return train;
    }
    
    public void loadPassengers() {
        //train.set
        System.out.println("load passengers");
    }

    public void staffActivityWagon() {
        System.out.println("staff activity wagons");
    }

    public void staffPassengerWagon() {
        System.out.println("staff passenger wagons");
    }

    public void collectIncome() {
        for (Wagon w : train.getWagons()) {
            if (w instanceof ActivityWagon) {
                ActivityWagon aw = (ActivityWagon) w;
                Bucket bucket;
                bucket = aw.getBucket();
                int coins = bucket.emtpyBucket();
                wallet.addCoins(coins);
            }
        }
        System.out.println("collect income");
        wallet.getCoins();
    }

    public void switchPassengersToPassengerWagon() {
        System.out.println("switch passenger who can exit to passenger wagons");
    }

    /**
     * from here on should the methods bodies be in the shop class 
     */
    public void buyPassengerWagon() {
        // get PassengerWagon
        try {
            PassengerWagon pw = new PassengerWagon();
            train.addPassengerWagon(pw);
            //add LevelAdmin to PassengerWagon
            pw.addPassengerListener(la);
            // sub coins
            // ! cost must be variable depending on the cost in the shop for the pw 
            int cost = 2;
            wallet.subCoins(cost);
            System.out.println("bought passenger wagon");
            System.out.println();
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
    }

    public void buyFunWagon() {
        // get ActivityWagon
        try {
            ActivityWagon aw = new FunWagon();
            train.addActivityWagon(aw);
            // sub coins
            // ! cost must be variable depending on the cost in the shop for the pw 
            int cost = 2;
            wallet.subCoins(cost);
            System.out.println("bought fun wagon");
            System.out.println();
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
    }

    public void buyEatingWagon() {
        // get ActivityWagon
        try {
            ActivityWagon aw = new EatingWagon();
            train.addActivityWagon(aw);
            // sub coins
            // ! cost must be variable depending on the cost in the shop for the pw 
            int cost = 2;
            wallet.subCoins(cost);
            System.out.println("bought eating wagon");
            System.out.println();
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
    }
    
    public void buyTrainingWagon() {
        // get ActivityWagon
        try {
            ActivityWagon aw = new TrainingWagon();
            train.addActivityWagon(aw);
            // sub coins
            // ! cost must be variable depending on the cost in the shop for the pw 
            int cost = 2;
            wallet.subCoins(cost);
            System.out.println("bought training wagon");
            System.out.println();
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
    }

    public void buyEngine() {
        // get Engine
        // sub coins
        System.out.println("bought engine");
    }

    @Override
    public void levelUp(int level) {
        setLevel(level);
    }
}
