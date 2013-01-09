package ghosttrain;

import java.util.ArrayList;
import java.util.List;
import wagons.ActivityWagon;
import wagons.PassengerWagon;
import wagons.Wagon;

/**
 *
 * @author Linda
 */
public class Train {

    private Engine engine;
    private List<Wagon> ratioWagons;
    private Schedule theSchedule;

    public Train() {
        engine = new Engine();
        ratioWagons = new ArrayList<Wagon>();
        theSchedule = new Schedule(3);
    }
    
    /**
     * in wagon is also an add wagon method
     * @param pw 
     */
    //add Passenger Wagons to list: wagons
    public void addPassengerWagon(PassengerWagon pw) {
        ratioWagons.add(pw);
        System.out.println("PassengerWagon List: " + ratioWagons);
    }

    /**
     * in wagon is also an add wagon method
     * @param aw 
     */
    //add Activity Wagons to list: wagons
    public void addActivityWagon(ActivityWagon aw) {
        ratioWagons.add(aw);
        System.out.println("ActivityWagon List: " + ratioWagons);
    }

    public void dropOffPassenger() {
        // drop off passengers from Passenger wagons
        for (Wagon w : ratioWagons) {
            if (w instanceof PassengerWagon) {
                PassengerWagon pw = (PassengerWagon) w;
                pw.getOff(this.getCurrentDestination());
            }
        }
    }

    public void enterNextCity() {
        Destination d = theSchedule.getNextStop();
        //wait or do something for time d.getDistance()
        //then
        System.out.println("on my way for distance: " + d.getDistance());
        theSchedule.setCurrentCity(theSchedule.getCurrentCity() + 1);
    }

    public Destination getCurrentDestination() {
        return theSchedule.getCurrentStop();
    }

    public List<Wagon> getWagons() {
        return ratioWagons;
    }
}
