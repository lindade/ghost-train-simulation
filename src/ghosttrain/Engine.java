package ghosttrain;

/**
 *
 * @author Linda
 */
public class Engine {

    private int quantityOfWagons;

    public Engine() {
        quantityOfWagons = 3;
    }

    /**
     * engine upgrade extends the number of wagons which can be dragged by the engine
     */
    public void engineUpgrade(int upgrade) {
        System.out.println("Engine was capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
        quantityOfWagons = upgrade;
        System.out.println("Engine is now capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
    }

    public int getQuantityOfWagons() {
        return quantityOfWagons;
    }
}
