package ghosttrain;

import wagons.ActivityWagon;
import wagons.Wagon;

/**
 *
 * @author Linda
 */
public class Player {

    private int level;
    private Wallet wallet;
    private Destination currentDestination;
    private Train train;

    public Player() {
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
        // get PassengerWagon
        // sub coins
        System.out.println("buy passenger wagon");
    }

    public void buyActivityWagon() {
        // get ActivityWagon
        // sub coins
        System.out.println("buy activity wagon");
    }
    
     public void Engine() {
     // get ActivityWagon
     // sub coins
     System.out.println("buy engine");
    }
}
