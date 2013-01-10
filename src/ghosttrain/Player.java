package ghosttrain;

import exceptions.MaxWagonCountReached;
import wagons.ActivityWagon;
import wagons.FunWagon;
import wagons.PassengerWagon;
import wagons.Wagon;

/**
 *
 * @author Linda
 */
public class Player {

    private int level;
    private LevelAdmin la;
    private Wallet wallet;
    private Destination currentDestination;
    private Train train;

    public Player() {
        la = new LevelAdmin();
        wallet = new Wallet();
        train = new Train();

    }
    
    public int getLevel(){
        return level;
    }
    
    public void raiseLevel(){
        level += 1;
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
        for( Wagon w : train.getWagons()) {
            if( w instanceof ActivityWagon ) {
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

    public void buyPassengerWagon() {
        // sub coins
        // ! cost must be variable depending on the cost in the shop for the pw 
        int cost = 2;
        wallet.subCoins(cost);
        // get PassengerWagon
        try{
            PassengerWagon pw = new PassengerWagon();
            train.addPassengerWagon(pw);
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
        System.out.println("buy passenger wagon");
    }

    public void buyActivityWagon() {
        // sub coins
        // ! cost must be variable depending on the cost in the shop for the pw 
        int cost = 2;
        wallet.subCoins(cost);
        // get ActivityWagon
        try{
            ActivityWagon aw = new FunWagon();
            train.addActivityWagon(aw);
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
        System.out.println("buy activity wagon");
    }
    
     public void Engine() {
     // get ActivityWagon
     // sub coins
     System.out.println("buy engine");
    }
}
