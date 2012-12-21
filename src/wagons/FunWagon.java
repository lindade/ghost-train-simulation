/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wagons;

import ghosttrain.Bucket;
import ghosttrain.Passenger;
import java.util.ArrayList;

/**
 *
 * @author Linda
 */
public class FunWagon extends ActivityWagon {

    public FunWagon(String name) {
        wagonCount++;
        passengers = new ArrayList<Passenger>();
        bucket = new Bucket();
        this.name = name;
    }

    @Override
    public void fillBucket() {
    }

    @Override
    public String printName() {
        return String.format("Funwagon: " + name + "\n");
    }

    @Override
    public int demandEarning() {
        int earnings = 0;
        for (Passenger p : passengers) {
            earnings += p.getFunValue();
        }
        System.out.println("Fun earnings: " + earnings);
        return earnings;
    }

    @Override
    public void addPassenger(Passenger p) {
        // has to be limited to 3 persons
        passengers.add(p);
        // just for the output on the console
        System.out.print(this.printName() + "Passenger List: ");
        this.printList();
        System.out.println();
    }

    @Override
    public void printList() {
        for (Passenger pas : passengers) {
            System.out.print(pas.getName() + "\t");
        }
    }
}
