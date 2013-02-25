package ghosttrain;

import java.util.Random;
import java.util.logging.Logger;

/**
 * the destination where the passenger wants to leave does never match with the
 * current destination have to check the references
 * 
 * have to test the parameters of the passengers
 * 
 * @author Linda
 */
public class PassengerFactory {

    private int dependsOnDestination;
    private Random r = new Random();
    int count = 1;
    private static final Logger log = Logger.getLogger(PassengerFactory.class.getName());


    public PassengerFactory() {
    }

    /**
     * here is decided how high the parameter values of a passenger are depending
     * on the destination from which he boarded the train
     * @param currentDest
     */
    public int getRandNumDependOnDest(Destination currentDest) {
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
        Passenger p = new Passenger("Name"+ count++,
                r.nextInt(dependsOnDestination),
                r.nextInt(dependsOnDestination),
                r.nextInt(dependsOnDestination), schedule.getDesiredDestination(r.nextInt(weigthedRandom(available)))); // available+1; weigthedRandom(available)
                /**
                 * The last parameter has to be one of the currently available Destinations or
                 * the next Destination not yet available on the schedule
                */
        log.info(String.format("passenger parameter: %d %d %d\n", p.getEatingValue(), p.getFunValue(), p.getTrainingValue()));
        log.info(String.format("passenger deboarding destination: %s\n", p.getDeboarding().getName()));
        return p;
    }
    
    public int weigthedRandom(int availableCities){
        int value;
        value = r.nextInt(10);
        if (value >= 5){
            return availableCities+1;
        }
        return availableCities;
    }
}
