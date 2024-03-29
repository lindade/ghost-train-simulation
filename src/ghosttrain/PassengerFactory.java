package ghosttrain;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Linda
 */
public class PassengerFactory {

    private int dependsOnDestination;
    private Random r = new Random();
    int count = 1;
    private static final Logger log = Logger.getLogger(PassengerFactory.class.getName());
    private HashMap<String, Integer> maxPassengerSkillValue;


    public PassengerFactory() {
        maxPassengerSkillValue = new HashMap<>();
    }
    
    public void setMaxPassengerSkillValue(HashMap<String, Integer> skillValues) {
        maxPassengerSkillValue = skillValues;
    }

    /**
     * here is decided how high the parameter values of a passenger are depending
     * on the destination from which he boarded the train
     * @param currentDest
     */
    public int getRandNumDependOnDest(Destination currentDest) {
        if( maxPassengerSkillValue != null && !maxPassengerSkillValue.isEmpty() ) {
            return maxPassengerSkillValue.get(currentDest.getName());
        }
        int dependingOnDestination = 0;
        switch (currentDest.getName()) {
            case "Limbo":
                dependingOnDestination = 3;
                break;
            case "London":
                dependingOnDestination = 3;
                break;
            case "Paris":
                dependingOnDestination = 3;
                break;
            case "Cairo":
                dependingOnDestination = 5;
                break;
            case "Beijing":
                dependingOnDestination = 5;
                break;
            case "Tokyo":
                dependingOnDestination = 5;
                break;
            case "Sydney":
                dependingOnDestination = 7;
                break;
            case "Rio de Janiero":
                dependingOnDestination = 7;
                break;
            case "San Francisco":
                dependingOnDestination = 7;
                break;
            case "New York":
                dependingOnDestination = 9;
                break;
            case "Havana":
                dependingOnDestination = 9;
                break;
            case "Burial Ground":
                dependingOnDestination = 9;
                break;
            case "Necropolis":
                dependingOnDestination = 9;
                break;
            case "Underworld":
                dependingOnDestination = 12;
                break;
            case "City of the Dead":
                dependingOnDestination = 12;
                break;
            case "Moscow":
                dependingOnDestination = 15;
                break;
        }
        return dependingOnDestination;
    }


    
    /**
     * 
     * @param currentDestination
     * @return 
     */
    public Passenger createPassenger(Schedule schedule) {
        dependsOnDestination = getRandNumDependOnDest(schedule.getCurrentStop());
        int available = schedule.getAvailableCities();
        /**
         * creates random numbers between one and 5
         * 1 + random.nextInt(6);
         * creates random numbers between zero and 5
         * random.nextInt(6);
         */
        int destination = r.nextInt(weigthedRandom(available));
        
        Passenger p = new Passenger("Name"+ count++,
                r.nextInt(dependsOnDestination),
                r.nextInt(dependsOnDestination),
                r.nextInt(dependsOnDestination), schedule.getDesiredDestination(destination)); // available+1; weigthedRandom(available)
                /**
                 * The last parameter has to be one of the currently available Destinations or
                 * the next Destination not yet available on the schedule
                */
        try {
            log.finest(String.format("passenger parameter: %d %d %d. Passenger deboarding destination: %s ", p.getEatingValue(), p.getFunValue(), p.getTrainingValue(), p.getDeboarding().getName()));
        }catch (Exception e){
            log.finest(e.getMessage());
        }
        return p;
    }
    
    public int weigthedRandom(int availableCities){
        int value;
        value = r.nextInt(10);
        if (value >= 5 && availableCities < 16){
            return availableCities+1;
        }
        return availableCities;
    }
}
