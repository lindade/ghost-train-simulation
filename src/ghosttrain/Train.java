package ghosttrain;

import exceptions.MaxWagonCountReached;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.ActivityWagon;
import wagons.EatingWagon;
import wagons.FunWagon;
import wagons.PassengerWagon;
import wagons.TrainingWagon;
import wagons.Wagon;

/**
 *
 * @author Linda
 */
public class Train {

    private Engine engine;
    private List<Wagon> ratioWagons;
    private Schedule theSchedule;

    public Train(PassengerListener pL) {
        engine = new Engine();
        ratioWagons = new ArrayList<>();
        theSchedule = new Schedule(3);
        createFirstWagons(pL);
    }

    /**
     * @param pw add Passenger Wagons to list: wagons
     */
    public void addPassengerWagon(PassengerWagon pw) {
        ratioWagons.add(pw);
        System.out.println("added an passenger wagon to the overall wagon list: " + ratioWagons);
    }

    /**
     * @param aw add Activity Wagons to list: wagons
     */
    public void addActivityWagon(ActivityWagon aw) {
        ratioWagons.add(aw);
        System.out.println("added an activity wagon to the overall wagon list: " + ratioWagons);
    }

    public void dropOffPassenger() {
        // drop off passengers from Passenger wagons
        for (Wagon w : ratioWagons) {
            if (w instanceof PassengerWagon) {
                System.out.println("dropoffPassenger");
                PassengerWagon pw = (PassengerWagon) w;
                pw.getOff(this.getCurrentDestination());
            }
        }
    }

    /**
     * this method must be called when the train approaches at the new
     * destination
     */
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

    public Destination getNextDestination() {
        return theSchedule.getNextStop();
    }

    public List<Wagon> getWagons() {
        return ratioWagons;
    }

    /**
     * a method to get the passengerWagons of the ratioWagonList
     *
     * @return
     */
    public List<Wagon> getPassengerWagons() {
        List<Wagon> pwList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof PassengerWagon) {
                pwList.add(w);
                System.out.println("added an passenger wagon to the passenger wagon list: " + pwList);
            }
        }
        return pwList;
    }

    /**
     * a method to get the activityWagons of the ratioWagonList
     *
     * @return
     */
    public List<Wagon> getActivityWagons() {
        List<Wagon> awList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof ActivityWagon) {
                awList.add(w);
                System.out.println("added an activity wagon to the activity wagon list: " + awList);
            }
        }
        return awList;
    }

    /**
     * a method to get the eatingWagons of the ratioWagonList
     *
     * @return
     */
    public List<Wagon> getEatingWagons() {
        List<Wagon> ewList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof EatingWagon) {
                ewList.add(w);
                System.out.println("added an eating wagon to the eating wagon list: " + ewList);
            }
        }
        return ewList;
    }

    /**
     * a method to get the funWagons of the ratioWagonList
     *
     * @return
     */
    public List<Wagon> getFunWagons() {
        List<Wagon> fwList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof FunWagon) {
                fwList.add(w);
                System.out.println("added an fun wagon to the fun wagon list: " + fwList);
            }
        }
        return fwList;
    }

    /**
     * a method to get the trainingWagons of the ratioWagonList
     *
     * @return
     */
    public List<Wagon> getTrainingWagons() {
        List<Wagon> twList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof TrainingWagon) {
                twList.add(w);
                System.out.println("added an training wagon to the training wagon list: " + twList);
            }
        }
        return twList;
    }

    public Engine getEngine() {
        return engine;
    }

    public Schedule getSchedule() {
        return theSchedule;
    }

    /**
     * creates the first two wagons when a player is starting a game. The player
     * gets one passenger wagon and one activity wagon. The activity wagon is an
     * eating wagon by default.
     */
    private void createFirstWagons(PassengerListener passengerListener) {
        try {
            PassengerWagon pw = new PassengerWagon();
            pw.addPassengerListener(passengerListener);
            addPassengerWagon(pw);
            ActivityWagon aw = new EatingWagon();
            addActivityWagon(aw);
        } catch (MaxWagonCountReached ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
