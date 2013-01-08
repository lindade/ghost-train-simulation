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
public class EatingWagon extends ActivityWagon {

    public EatingWagon(String name) throws MaxWagonCountReached {
        super();
        this.name = name;
    }

    @Override
    public void fillBucket() {
        bucket.fillBucket(demandEarning());
    }

    @Override
    public String printName() {
        return String.format("Eatingwagon: " + name + "\n");
    }

    @Override
    public int demandEarning() {
        int earnings = 0;
        for (Passenger p : getPassengers()) {
            earnings += p.getEatingValue();
        }
        System.out.println("Eating earnings: " + earnings);
        return earnings;
    } 
}
