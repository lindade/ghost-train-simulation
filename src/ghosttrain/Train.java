/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private List<Wagon> wagons;
    private Schedule theSchedule;

    public Train() {
        engine = new Engine();
        wagons = new ArrayList<Wagon>();
        theSchedule = new Schedule(3);
    }

    //add Passenger Wagons to list: wagons
    public void addPassengerWagon(PassengerWagon pw) {
        wagons.add(pw);
        System.out.println("PassengerWagon List: " + wagons);
    }

    //add Activity Wagons to list: wagons
    public void addActivityWagon(ActivityWagon aw) {
        wagons.add(aw);
        System.out.println("ActivityWagon List: " + wagons);
    }

    public void dropOffPassenger() {
        // drop off passengers from Passenger wagons
        for (Wagon w : wagons) {
            PassengerWagon pw = new PassengerWagon();
            if (w.equals(pw)) {
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
}