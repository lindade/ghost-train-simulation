package ghosttrain;

import exceptions.MaxWagonCountReached;
import wagons.PassengerWagon;

/**
 *
 * @author Linda
 */
public class Store implements LevelListener  {
    
    private int level;
    private Player player;
    private LevelListener levelListener;
    
    public Store(Player player){
        this.player = player;
        this.level = player.getLevel(); 
    }
    
    private void setLevel(int newlevel){
        this.level = newlevel;
    }
    
    public int getLevelFromStore(){
        return level;
    }
    
    @Override
    public void levelUp(int level) {
        setLevel(level);
    }
    
    public PassengerWagon buyPassengerWagon(){
        try {
            PassengerWagon pw = new PassengerWagon();
            // sub coins
            // ! cost must be variable depending on the cost for the pw 
            int cost = 2;
            player.getWallet().subCoins(cost);
            System.out.println("bought passenger wagon");
            System.out.println();
            return pw;
        } catch (MaxWagonCountReached ex) {
            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
        return null;
    }
    
}
