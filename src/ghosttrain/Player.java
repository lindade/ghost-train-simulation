package ghosttrain;

import Datatype.Level;

/**
 *
 * @author Linda
 */
public class Player {

    private Level level;
    private Wallet wallet;
    private Destination currentDestination;
    private Train train;

    public Player() {
        wallet = new Wallet();
        train = new Train();

    }

    public void loadPassengers() {
        //train.set
        System.out.println("load passengers");
    }

    public void staffActivityWagon() {
        System.out.println("staff activity wagons");

    }

    public void collectIncome() {
        System.out.println("collect income");

    }

    public void buyPassengerWagon() {
        System.out.println("buy passenger wagon");

    }

    public void buyActivityWagon() {
        System.out.println("buy activity wagon");

    }
}
