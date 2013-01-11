package ghosttrain;

import exceptions.MaxWagonCountReached;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.ActivityWagon;
import wagons.EatingWagon;
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
    private int deboardedPassengers;

    public Train() {
        engine = new Engine();
        ratioWagons = new ArrayList<Wagon>();
        theSchedule = new Schedule(3);
        createFirstWagons();
    }
    
    /**
     * @param pw 
     */
    //add Passenger Wagons to list: wagons
    public void addPassengerWagon(PassengerWagon pw) {
        ratioWagons.add(pw);
        System.out.println("PassengerWagon List: " + ratioWagons);
    }

    /**
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
                int dP = pw.tellLevelAdmin();
                setDeboardedPassengers(dP);
            }
        }
    }
       
    private void setDeboardedPassengers(int deboardedPassengers) {
        this.deboardedPassengers = deboardedPassengers;
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
    
    /**
     * creates the first two wagons when a player is starting a game.
     * The player gets one passenger wagon and one activity wagon.
     * The activity wagon is an eating wagon by default.
     */
    private void createFirstWagons() {
        try {
            PassengerWagon pw = new PassengerWagon();
            addPassengerWagon(pw);
            ActivityWagon aw = new EatingWagon();
            addActivityWagon(aw);
        } catch (MaxWagonCountReached ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Maximum number of wagons reached!" + " ex: " + ex.getMessage());
        }
    }

}
