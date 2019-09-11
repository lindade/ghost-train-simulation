package ghosttrain;

import exceptions.MaxPassengerCapacityReachedException;
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
    private int internalTime = 0;
    private int totalTime;
    private int step;
    PassengerFactory pF = new PassengerFactory();
    private static final Logger log = Logger.getLogger(Train.class.getName());
    private int interval = 60;
    private final PassengerListener passengerListener;

    public Train(PassengerListener pL) {
        engine = new Engine();
        ratioWagons = new ArrayList<>();
        theSchedule = new Schedule(3);
        this.passengerListener = pL;
        initValues();
    }

    public final void initValues() {
        createFirstWagons(passengerListener);
        setInterval(interval);
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * @param pw add Passenger Wagons to list: wagons
     */
    public void addPassengerWagon(PassengerWagon pw) {
        ratioWagons.add(pw);
        log.log(Level.FINEST, "added an passenger wagon to the overall wagon list: {0}", ratioWagons);
    }

    /**
     * @param aw add Activity Wagons to list: wagons
     */
    public void addActivityWagon(ActivityWagon aw) {
        ratioWagons.add(aw);
        log.log(Level.FINEST, "added an activity wagon to the overall wagon list: {0}", ratioWagons);
    }

    public void dropOffPassenger() {
        // drop off passengers from Passenger wagons
        for (Wagon w : ratioWagons) {
            if (w instanceof PassengerWagon) {
                //log.info("dropoffPassenger");
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
        log.log(Level.FINEST, "on my way for distance: {0}", d.getDistance());
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

    public void setWagons(List<Wagon> wagons) {
        ratioWagons = wagons;
    }

    /**
     * a method to get the passengerWagons of the ratioWagonList
     *
     * @return
     */
    public List<PassengerWagon> getPassengerWagons() {
        List<PassengerWagon> pwList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof PassengerWagon) {
                pwList.add((PassengerWagon) w);
                log.log(Level.FINEST, "added an passenger wagon to the passenger wagon list: {0}", pwList);
            }
        }
        return pwList;
    }

    /**
     * a method to get the activityWagons of the ratioWagonList
     *
     * @return
     */
    public List<ActivityWagon> getActivityWagons() {
        List<ActivityWagon> awList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof ActivityWagon) {
                awList.add((ActivityWagon) w);
                log.log(Level.FINEST, "added an activity wagon to the activity wagon list: {0}", awList);
            }
        }
        return awList;
    }

    /**
     * a method to get the eatingWagons of the ratioWagonList
     *
     * @return
     */
    public List<EatingWagon> getEatingWagons() {
        List<EatingWagon> ewList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof EatingWagon) {
                ewList.add((EatingWagon) w);
                log.log(Level.FINEST, "added an eating wagon to the eating wagon list: {0}", ewList);
            }
        }
        return ewList;
    }

    /**
     * a method to get the funWagons of the ratioWagonList
     *
     * @return
     */
    public List<FunWagon> getFunWagons() {
        List<FunWagon> fwList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof FunWagon) {
                fwList.add((FunWagon) w);
                log.log(Level.FINEST, "added an fun wagon to the fun wagon list: {0}", fwList);
            }
        }
        return fwList;
    }

    /**
     * a method to get the trainingWagons of the ratioWagonList
     *
     * @return
     */
    public List<TrainingWagon> getTrainingWagons() {
        List<TrainingWagon> twList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof TrainingWagon) {
                twList.add((TrainingWagon) w);
                log.log(Level.FINEST, "added an training wagon to the training wagon list: {0}", twList);
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

    public int getTotalTime() {
        return totalTime;
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
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void setTimeStep(int timeStep) {
        step = timeStep;
    }

    public boolean isAboutToArrive() {
        return internalTime + step == getNextDestination().getDistance();
    }

    public boolean hasArrived() {
        return internalTime == 0;
    }

    public boolean startedTrip() {
        return internalTime == 12;
    }

    /**
     * add 3 passengers to the empty passenger wagons the activity wagon is
     * still empty
     */
    public void loadPassengers() {
        log.finest("load passengers");
        for (PassengerWagon pw : getPassengerWagons()) {
            if (pw.seatfree()) {
                while (pw.getPassengers().size() < 3) {
                    try {
                        pw.addPassenger(pF.createPassenger(getSchedule()));
                    } catch (MaxPassengerCapacityReachedException ex) {
                        log.log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        checkDestination();
    }

    /**
     * security function checks that not all passengers want to leave at the
     * locked destination if everyone wants to leave at the locked destination
     * the method changes one passenger to one passenger with an unlocked
     * destination
     */
    public void checkDestination() {
        for (Wagon w : ratioWagons) {
            List<Passenger> passengerList = w.getPassengers();
            for (Passenger passenger : passengerList) {
                Destination d = passenger.getDeboarding();
//                log.info(String.format("target: %s, index: %d, availableCities: %d",
//                        d.getName(),
//                        Schedule.DESTINATIONS.get(d.getName()),
//                        getSchedule().getAvailableCities()));
                if (Schedule.DESTINATIONS.get(d.getName()) < getSchedule().getAvailableCities()) {
                    return;
                }
            }
        }
//        log.info("Everyone wants to disembark at an locked location.");
        // None of the passengers want to go to an unlocked destination
        PassengerWagon pw = getPassengerWagons().get(0); //getPassengerWagons().size()
        Passenger toDropOff = pw.getPassengers().get(0);
        Passenger toSubstitute = pF.createPassenger(theSchedule);
        while (toSubstitute.getDeboarding().equals(toDropOff.getDeboarding())) {
            toSubstitute = pF.createPassenger(theSchedule);
        }
        // sub PremiumCredits Wallet.subPC();
        pw.removePassenger(toDropOff);
        try {
            pw.addPassenger(toSubstitute);
        } catch (MaxPassengerCapacityReachedException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        internalTime += step;
        if (internalTime % interval == 0 && internalTime > 0) {
            for (ActivityWagon wagon : getActivityWagons()) {
                wagon.fillBucket();
            }
        }
        if (internalTime == getNextDestination().getDistance()) {
            enterNextCity();
            log.log(Level.FINEST, "current destination: {0}", getCurrentDestination().getName());
            dropOffPassenger();
            loadPassengers();
            totalTime += internalTime;
            log.log(Level.FINEST, "totalTime: {0}min", totalTime / 60);
            log.log(Level.FINEST, "totalTime: {0}h", totalTime / 60 / 60);
            log.log(Level.FINEST, "totalTime: {0}d", totalTime / 60 / 60 / 24);
            //log.log(Level.FINEST, "totalTime: {0}h", (totalTime % (60 / 60 ))  );
            internalTime = 0;
        }

    }

    public PassengerFactory getPassengerFactory() {
        return pF;
    }
}