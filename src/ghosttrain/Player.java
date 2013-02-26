package ghosttrain;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.ActivityWagon;
import wagons.PassengerWagon;
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
    private Store store;
    private int[] levelUnlockEngine = {5, 10, 15, 20, 27, 34, 41, 48};
    private int index;
    private Random randomizer = new Random();
    private static final Logger log = Logger.getLogger(Player.class.getName());
    private Strategy currentStrategy;
    
    public enum Strategy {
        Coin_Oriented,
        Experience_Oriented;
    }
    
    public Player() {
        la = new LevelAdmin();
        la.addLevelListener(this);
        level = la.getLevelFromLevelAdmin(); // from the start 1
        wallet = new Wallet();
        train = new Train(la);
        la.addScheduleUpgradeListener(train.getSchedule());
        store = new Store(this);
        index = 0;
    }

    public int getLevel() {
        return level;
    }

    private void setLevel(int newlevel) {
        this.level = newlevel;
    }

    @Override
    public void levelUp(int level) {
        setLevel(level);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Train getTrain() {
        return train;
    }

    public void staffActivityWagon() {
        log.info("staff activity wagons");
    }

    public void staffPassengerWagon() {
        log.info("staff passenger wagons");
    }

    public void collectIncome() {
        for (ActivityWagon aw : train.getActivityWagons()) {
            Bucket bucket;
            bucket = aw.getBucket();
            int coins = bucket.emtpyBucket();
            wallet.addCoins(coins);
        }
        //log.info("collect income");
        wallet.getCoins();
    }

    public void switchPassengersToPassengerWagon() {
        log.info("switch passenger who can exit to passenger wagons");
    }

    public boolean buyPassengerWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get PassengerWagon
            PassengerWagon pw = store.buyPassengerWagon();
            if (pw != null) {
                train.addPassengerWagon(pw);
                //add LevelAdmin to PassengerWagon
                pw.addPassengerListener(la);
                return true;
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                    train.getWagons().size()});
        }
        return false;
    }

    public boolean buyFunWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get ActivityWagon
            ActivityWagon aw = store.buyFunWagon();
            if (aw != null) {
                train.addActivityWagon(aw);
                return true;
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                    train.getWagons().size()});
        }
        return false;
    }
    
    public boolean buyEatingWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get ActivityWagon
            ActivityWagon aw = store.buyEatingWagon();
            if (aw != null) {
                train.addActivityWagon(aw);
                return true;
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                    train.getWagons().size()});
        }
        return false;
    }

    public boolean buyTrainingWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get ActivityWagon
            ActivityWagon aw = store.buyTrainingWagon();
            if (aw != null) {
                train.addActivityWagon(aw);
                return true;
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                        train.getWagons().size()});
        }
        return false;
    }

    public boolean buyEngine() {
        if(index < levelUnlockEngine.length){
            //limit engine purchase by Level
            if (level == levelUnlockEngine[index] || level >= levelUnlockEngine[index]) {
                // get Engine
                boolean bought = store.buyEngine();
                index++;
                return bought;
            } else {
                log.log(Level.INFO,"You cannot buy a new engine."
                        + "First you have to level up."
                        + " You are at level {0}."
                        + "You have to reach level {1} to be able to purchase a new engine.",
                        new Object[]{getLevel(), levelUnlockEngine[index]});
                return false;
            }
        }
        return false;
    }
    
    public boolean buyBucketUpgrade(ActivityWagon aw){
        boolean bought = store.buyBucketUpgrade(aw);
        return bought;
    }
    
    public void update(int travelTime) {
        // plays 3x times a day for 15mim
        if(train.hasArrived()){
            if( buyEngine()) {
                currentStrategy = Strategy.Coin_Oriented;
            } else {
                currentStrategy = Strategy.Experience_Oriented;
            }
            buyPassengerWagon();
            int decide = randomizer.nextInt(5);
            switch( decide ) {
                case 0 : buyEatingWagon(); break;
                case 1 : buyFunWagon(); break;
                case 2 : buyTrainingWagon(); break;
                case 3 : buyPassengerWagon(); break;
                case 4 : buyPassengerWagon();
            }
//            for (ActivityWagon aw : getTrain().getActivityWagons()) {
//                    buyBucketUpgrade(aw);
//            }
            
            
        }else if(train.isAboutToArrive()){
            collectIncome();
            //passenger switching to passengerWagon
        } else if(train.startedTrip()) {
        //switch passengers
            //print passengerList
            log.info("old order");
            for( Wagon w : getTrain().getWagons() ) {
                w.printPassengerList();
            }
            PassengerSorter sorter = new PassengerSorter(getTrain());
            sorter.sortExperienceOriented();
            log.info("new order");
            for( Wagon w : getTrain().getWagons() ) {
                w.printPassengerList();
            }
        } else {
            // should player be active
            // collects income when bucket is filled completely or filled partly
            // 960 = all 16 min
            if(travelTime % 960 == 0 && travelTime > 0){
                collectIncome();
            }
        }
    }
}
