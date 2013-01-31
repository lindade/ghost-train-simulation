package ghosttrain;

/**
 *
 * @author Linda
 */
public class Engine {

    private int quantityOfWagons; 
    private int[] wagonAllowance = {6, 10, 15, 20, 25, 30, 35, 40}; // upgrade values
    private int index;

    public Engine() {
        quantityOfWagons = 3; //default value 
        index = 0;
    }

    /**
     * engine upgrade extends the number of wagons which can be dragged by the engine
     */
    public void engineUpgrade() {
        System.out.println("Engine was capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
        quantityOfWagons = wagonAllowance[index]; // set the new value of how many wagons can be pulled
        index++;
        System.out.println("Engine is now capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
    }

    public int getQuantityOfWagons() {
        return quantityOfWagons;
    }
}
