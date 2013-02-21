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
public class Train implements Runnable {

    private Engine engine;
    private List<Wagon> ratioWagons;
    private Schedule theSchedule;
    private int internalTime = 0;
    int totalTime;
    private int step = 10;
    PassengerFactory pF = new PassengerFactory();
    //    private static final Logger log = Logger.getLogger(Player.class.getName());

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
        //System.out.println("added an passenger wagon to the overall wagon list: " + ratioWagons);
    }

    /**
     * @param aw add Activity Wagons to list: wagons
     */
    public void addActivityWagon(ActivityWagon aw) {
        ratioWagons.add(aw);
        //System.out.println("added an activity wagon to the overall wagon list: " + ratioWagons);
    }

    public void dropOffPassenger() {
        // drop off passengers from Passenger wagons
        for (Wagon w : ratioWagons) {
            if (w instanceof PassengerWagon) {
                //System.out.println("dropoffPassenger");
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
    public List<PassengerWagon> getPassengerWagons() {
        List<PassengerWagon> pwList = new ArrayList<>();
        for (Wagon w : ratioWagons) {
            if (w instanceof PassengerWagon) {
                pwList.add((PassengerWagon) w);
                //System.out.println("added an passenger wagon to the passenger wagon list: " + pwList);
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
                //System.out.println("added an activity wagon to the activity wagon list: " + awList);
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
                //System.out.println("added an eating wagon to the eating wagon list: " + ewList);
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
                //System.out.println("added an fun wagon to the fun wagon list: " + fwList);
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
                //System.out.println("added an training wagon to the training wagon list: " + twList);
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

    @Override
    public void run() {
        int i = 0;
        while(true){
            i++;
        }
    }
    
    public void setTimeStep(int timeStep) {
        step = timeStep;
    }
    
    public boolean isAboutToArrive() {
        return internalTime + step == getNextDestination().getDistance();
    }
    
    public void update() {
        internalTime += step; 
        if( internalTime % 60 == 0 && internalTime > 0) {
            for( ActivityWagon wagon : getActivityWagons()) {
                wagon.fillBucket();
            }
        }
        if( internalTime == getNextDestination().getDistance()) {
            enterNextCity();
            System.out.println("current destination: " + getCurrentDestination().getName());
            dropOffPassenger();
            loadPassengers();
            totalTime += internalTime;
            System.err.println("totalTime: " + totalTime/60 + "min");
            internalTime = 0;
        }
        
    }
    
    public boolean hasArrived() {
        return internalTime == 0;
    }

    /**
     * add 3 passengers to the empty passenger wagons the
     * activity wagon is still empty
     */
    public void loadPassengers() {
        System.out.println("load passengers");
        for (Wagon w : getWagons()) {
            if (w instanceof PassengerWagon && w.seatfree()) {
                while (w.getPassengers().size() < 3) {
                    try {
                        w.addPassenger(pF.createPassenger(getSchedule()));
                    } catch (MaxPassengerCapacityReachedException ex) {
                        Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        checkDesination();
    }
    
    public void checkDesination(){
        for(Wagon w : ratioWagons){
            for(int i = 0; i < 3; i++){
                Destination d = w.getPassengers().get(i).getDeboarding();
                if(Schedule.DESTINATIONS.get(d.getName()) <= getSchedule().getAvailableCities() ){
                    return;
                }
            }
        }
        //None of the passenger want to go to a unlocked destination
        PassengerWagon pw = getPassengerWagons().get(0);
        Passenger toDropOff = pw.getPassengers().get(0);
        Passenger toSubstitute = pF.createPassenger(theSchedule);
        while( toSubstitute.getDeboarding().equals( toDropOff.getDeboarding() ) ) {
            toSubstitute = pF.createPassenger(theSchedule);
        }
        pw.removePassenger(toDropOff);
        try {
            pw.addPassenger(toSubstitute);
        } catch (MaxPassengerCapacityReachedException ex) {
            Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
