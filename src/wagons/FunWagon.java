/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;

/**
 *
 * @author Linda
 */
public class FunWagon extends ActivityWagon {

    public FunWagon(String name) throws MaxWagonCountReached {
        super();
        this.name = name;
    }

    @Override
    public void fillBucket() {
        bucket.fillBucket(demandEarning());
    }

    @Override
    public String printName() {
        return String.format("Funwagon: " + name + "\n");
    }

    @Override
    public int demandEarning() {
        int earnings = 0;
        for (Passenger p : getPassengers()) {
            earnings += p.getFunValue();
        }
        System.out.println("Fun earnings: " + earnings);
        return earnings;
    }

}
