package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;

/**
 *
 * @author Linda
 */
public class TrainingWagon extends ActivityWagon {

    public TrainingWagon(String name) throws MaxWagonCountReached {
        super();
        this.name = name;
    }

    @Override
    public void fillBucket() {
        bucket.fillBucket(demandEarning());
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
