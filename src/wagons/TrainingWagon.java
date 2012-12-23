/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Bucket;
import ghosttrain.Passenger;
import java.util.ArrayList;

/**
 *
 * @author Linda
 */
public class TrainingWagon extends ActivityWagon {

    public TrainingWagon(String name) throws MaxWagonCountReached {
        super();
        bucket = new Bucket();
        this.name = name;
    }

    @Override
    public void fillBucket() {
        bucket.fillBucket();
    }

    @Override
    public String printName() {
        return String.format("Trainingwagon: " + name + "\n");
    }

    @Override
    public int demandEarning() {
        int earnings = 0;
        for (Passenger p : getPassengers()) {
            earnings += p.getTrainingValue();
        }
        System.out.println("Training earnings: " + earnings);
        return earnings;
    }

}
