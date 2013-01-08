/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * the quantityUprgade should be variable and not the constant 3
     */
    public void engineUpgrade() {
        System.out.println("Engine was capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
        quantityOfWagons += 3;
        System.out.println("Engine is capable of pulling " + this.getQuantityOfWagons() + " Wagons.");
    }

    public int getQuantityOfWagons() {
        return quantityOfWagons;
    }
}
