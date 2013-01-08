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

    @Override
    public void addPassenger(Passenger p) {
        // has to be limited to 3 persons
        addPassenger(p);
        // just for the output on the console
        System.out.print(this.printName() + "Passenger List: ");
        this.printList();
        System.out.println();
    }
    
    @Override
    public void printList(){
        for(Passenger pas: getPassengers()){
            System.out.print(pas.getName() + "\t");
        }
    }

}
