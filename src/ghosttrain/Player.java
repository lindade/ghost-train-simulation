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
public class Player implements LevelListener, PremiumCreditListener {

    protected int level;
    private LevelAdmin la;
    private Wallet wallet;
    private Train train;
    private Store store;
    private int[] levelUnlockEngine = {5, 10, 15, 20, 27, 34, 41, 48};
    private int index;
    private Random randomizer = new Random();
    private static final Logger log = Logger.getLogger(Player.class.getName());

//    public enum Strategy {
//
//        Coin_Oriented,
//        Experience_Oriented;
//    }

    public void setEngineUnlockLevels(int[] unlockLevels) {
        levelUnlockEngine = unlockLevels;
    }
    
    public Player(LevelAdmin la) {
        this.la = la;
        la.addLevelListener(this);
        level = la.getLevelFromLevelAdmin(); // from the start 1
        wallet = new Wallet();
        train = new Train(la);
        la.addScheduleUpdateListener(train.getSchedule());
        la.addPremiumCreditListener(this);
        store = new Store(this);
        index = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int newlevel) {
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
    
    public Store getStore() {
        return store;
    }

    public void collectIncome() {
        for (ActivityWagon aw : train.getActivityWagons()) {
            Bucket bucket;
            bucket = aw.getBucket();
            int coins = bucket.emtpyBucket();
            wallet.addCoins(coins);
        }
        log.finest("collect income");
        wallet.getCoins();
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
            log.log(Level.FINEST, "You cannot buy another wagon. "
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
            log.log(Level.FINEST, "You cannot buy another wagon. "
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
            log.log(Level.FINEST, "You cannot buy another wagon. "
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
            log.log(Level.FINEST, "You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                        train.getWagons().size()});
        }
        return false;
    }

    public boolean buyEngine() {
        if (index < levelUnlockEngine.length) {
            //limit engine purchase by Level
            if (level >= levelUnlockEngine[index]) {
                // get Engine
                boolean bought = store.buyEngine();
                if (bought) {
                    index++;
                }
                return bought;
            } else {
                log.log(Level.FINEST, "You cannot buy a new engine."
                        + "First you have to level up."
                        + " You are at level {0}."
                        + "You have to reach level {1} to be able to purchase a new engine.",
                        new Object[]{getLevel(), levelUnlockEngine[index]});
                return false;
            }
        }
        return false;
    }

    public boolean buyBucketUpgrade(ActivityWagon aw) {
        boolean bought = store.buyBucketUpgrade(aw);
        return bought;
    }
    
    @Override
    public void addPremiumCredit(){
        getWallet().addPC();
    }

    public void update(int travelTime) {
        PassengerSorter sorter = new PassengerSorter(getTrain());
        // plays 3 times a day for 15min
        if (train.hasArrived()) {
//            if (buyEngine()) {
//                currentStrategy = Strategy.Coin_Oriented;
//            } else {
//                currentStrategy = Strategy.Experience_Oriented;
//            }
            buyEngine();
            buyPassengerWagon();
            int decide = randomizer.nextInt(6);
            switch (decide) {
                case 0:
                    buyPassengerWagon();
                    break;
                case 1:
                    buyFunWagon();
                    break;
                case 2:
                    buyPassengerWagon();
                    break;
                case 3:
                    buyTrainingWagon();
                    break;
                case 4:
                    buyPassengerWagon();
                    break;
                case 5:
                    buyEatingWagon();
            }
            for (ActivityWagon aw : getTrain().getActivityWagons()) {
                buyBucketUpgrade(aw);
            }


        } else if (train.isAboutToArrive()) {
            collectIncome();
            //passenger switching to passengerWagon
            sorter.sortExperienceOriented();
        } else if (train.startedTrip()) {
            //switch passengers
            //print passengerList
//            log.finest("old order");
//            for (Wagon w : getTrain().getWagons()) {
//                w.printPassengerList();
//            }
//            sorter.sortRandomInWagon();
            sorter.sortExperienceOriented();
//            log.finest("new order");
//            for (Wagon w : getTrain().getWagons()) {
//                w.printPassengerList();
//            }
        } else {
            // should player be active
            // collects income whenever the player is active
            // 960 = all 16 min
            // 60 = every min
            if (travelTime % 60 == 0 && travelTime > 0) {
                collectIncome();
            }
        }
    }
}
