package ghosttrain;

import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class Engine {

    private int quantityOfWagons; 
    private int[] wagonAllowance = {6, 10, 15, 20, 25, 30, 35, 40}; // upgrade values
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
        log.info("Engine was capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
        quantityOfWagons = wagonAllowance[index]; // set the new value of how many wagons can be pulled
        index++;
        log.info("Engine is now capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
    }

    public int getQuantityOfWagons() {
        return quantityOfWagons;
    }
}
