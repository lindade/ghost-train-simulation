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
public class TrainingWagon extends ActivityWagon {

    public TrainingWagon(String name) {
        wagonCount++;
        passengers = new ArrayList<Passenger>();
        bucket = new Bucket();
        this.name = name;
    }

    @Override
    public void fillBucket() {
    }

    @Override
    public void printName() {
        System.out.println("Trainingwagon: " + name);
    }

    @Override
    public int demandEarning() {
        int earnings = 0;
        for (Passenger p : passengers) {
            earnings += p.getTrainingValue();
            System.out.println("Earnings: " + earnings);
        }
        return earnings;
    }

    @Override
    public void addPassenger(Passenger p) {
        //  muss noch auf 3 Personen begrenzt werden
        passengers.add(p);
        System.out.println("Passenger List: " + passengers);
    }
}
