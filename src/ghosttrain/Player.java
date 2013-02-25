package ghosttrain;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.ActivityWagon;
import wagons.PassengerWagon;

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
        log.info("collect income");
        wallet.getCoins();
    }

    public void switchPassengersToPassengerWagon() {
        log.info("switch passenger who can exit to passenger wagons");
    }

    /**
     * create exception and do a try-catch block in the following methods
     * instead of the if-else-branch
     */
    public void buyPassengerWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get PassengerWagon
            PassengerWagon pw = store.buyPassengerWagon();
            if (pw != null) {
                train.addPassengerWagon(pw);
                //add LevelAdmin to PassengerWagon
                pw.addPassengerListener(la);
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                    train.getWagons().size()});
        }
    }

    public void buyFunWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get ActivityWagon
            ActivityWagon aw = store.buyFunWagon();
            if (aw != null) {
                train.addActivityWagon(aw);
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                    train.getWagons().size()});
        }
    }
    
    public void buyEatingWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get ActivityWagon
            ActivityWagon aw = store.buyEatingWagon();
            if (aw != null) {
                train.addActivityWagon(aw);
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                    train.getWagons().size()});
        }
    }

    public void buyTrainingWagon() {
        // limit wagons to the strength of the engine
        if (train.getWagons().size() < train.getEngine().getQuantityOfWagons()) {
            // get ActivityWagon
            ActivityWagon aw = store.buyTrainingWagon();
            if (aw != null) {
                train.addActivityWagon(aw);
            }
        } else {
            log.log(Level.INFO,"You cannot buy another wagon. "
                    + "Your Engine can only pull {0} wagons."
                    + "You have already {1} wagons.",
                    new Object[]{train.getEngine().getQuantityOfWagons(),
                        train.getWagons().size()});
        }
    }

    public void buyEngine() {
        //limit engine purchase by Level
        if (level == levelUnlockEngine[index] || level >= levelUnlockEngine[index]) {
            // get Engine
            store.buyEngine();
            index++;
        } else {
            log.log(Level.INFO,"You cannot buy a new engine."
                    + "First you have to level up."
                    + " You are at level {0}."
                    + "You have to reach level {1} to be able to purchase a new engine.",
                    new Object[]{getLevel(), levelUnlockEngine[index]});
        }
    }
    
    public void buyBucketUpgrade(ActivityWagon aw){
        store.buyBucketUpgrade(aw);
    }
    
    public void update() {
        // should player be active
        // plays 3x times a day for 15mim
        // collects income when bucket is filled completely or filled partly
        collectIncome();
        if(train.hasArrived()){
        //switch passengers
            //print passengerList
//            log.info("old order");
//            for( Wagon w : getTrain().getWagons() ) {
//                w.printPassengerList();
//            }
            PassengerSorter sorter = new PassengerSorter(getTrain());
            // -> shuffle
            sorter.sortRandomInWagon();
            //print passengerList...
//            log.info("new order");
//            for( Wagon w : getTrain().getWagons() ) {
//                w.printPassengerList();
//            }
            buyEngine();
            buyPassengerWagon();
            int decide = randomizer.nextInt(5);
            switch( decide ) {
                case 0 : buyEatingWagon(); break;
                case 1 : buyFunWagon(); break;
                case 2 : buyTrainingWagon(); break;
                case 3 : buyPassengerWagon(); break;
                case 4 : buyPassengerWagon();
            }
            for (ActivityWagon aw : getTrain().getActivityWagons()) {
                    buyBucketUpgrade(aw);
            }
        } 
        if(train.isAboutToArrive()){
            Destination nextDest = train.getNextDestination();
            
            //passenger switching to passengerWagon
        }
    }
}
