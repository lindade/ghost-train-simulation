package ghosttrain;

/**
 *
 * @author Linda
 */
public class GhostTrain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Player player = new Player();
        Train train = new Train();

        player.loadPassengers();
        player.staffActivityWagon();
        System.out.println("Current city: " + train.getCurrentDestination().getName());
        train.enterNextCity();
        System.out.println("2nd city: " + train.getCurrentDestination().getName());
        train.dropOffPassenger();
        player.collectIncome();
        player.buyPassengerWagon();
        player.buyActivityWagon();
    }
}
