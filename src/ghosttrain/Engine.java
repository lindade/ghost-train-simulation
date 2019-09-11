package ghosttrain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class Engine {

    private int quantityOfWagons; 
    private int[] wagonAllowance = {3, 6, 10, 15, 20, 25, 30, 35, 40}; // upgrade values
    private int index;
    private static final Logger log = Logger.getLogger(Engine.class.getName());

    public Engine() {
        quantityOfWagons = 3; //default value 
        index = 0;
    }

    /**
     * engine upgrade extends the number of wagons which can be dragged by the engine
     */
    public void engineUpgrade() {
        log.log(Level.FINEST, "Engine was capable of pulling {0} Wagons.", this.getQuantityOfWagons());
        index++;
        quantityOfWagons = wagonAllowance[index]; // set the new value of how many wagons can be pulled
        log.log(Level.FINEST, "Engine is now capable of pulling {0} Wagons.", this.getQuantityOfWagons());
    }
    
    public void initEngineLevel(int level) {
        index = level-1;
        quantityOfWagons = wagonAllowance[index];
    }

    public int getQuantityOfWagons() {
        return quantityOfWagons;
    }
    
    public void setWagonAllowance(int[] allowance) {
        this.wagonAllowance = allowance;
    }
}
