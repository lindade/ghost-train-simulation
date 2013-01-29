package ghosttrain;

import java.util.Random;

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
                dependingOnDestination = 5;
                break;
            case "London":
                dependingOnDestination = 10;
                break;
            case "Berlin":
                dependingOnDestination = 15;
                break;
            case "Cairo":
                dependingOnDestination = 20;
                break;
            case "Beijing":
                dependingOnDestination = 25;
                break;
            case "Istanbul":
                dependingOnDestination = 30;
                break;
            case "Sydney":
                dependingOnDestination = 35;
                break;
            case "Rio de Janiero":
                dependingOnDestination = 40;
                break;
            case "San Francisco":
                dependingOnDestination = 45;
                break;
            case "New York":
                dependingOnDestination = 50;
                break;
            case "Havana":
                dependingOnDestination = 55;
                break;
            case "Moscow":
                dependingOnDestination = 60;
                break;
            case "Burial Ground":
                dependingOnDestination = 65;
                break;
            case "Necropolis":
                dependingOnDestination = 70;
                break;
            case "Underworld":
                dependingOnDestination = 75;
                break;
            case "City of the Dead":
                dependingOnDestination = 80;
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
        //bereich ist 0..available+1
        
//        creates random numbers between one and 5
//        1 + random.nextInt(6);
//        creates random numbers between zero and 5
//        random.nextInt(6);
        Passenger p = new Passenger("Name"+ count++,
                1 + r.nextInt(dependsOnDestination),
                1 + r.nextInt(dependsOnDestination),
                1 + r.nextInt(dependsOnDestination), schedule.getDesiredDestination(r.nextInt(available+1) )); // the destination has to be changed
        System.out.printf("passenger parameter: %d\t%d\t%d\n", p.getEatingValue(), p.getFunValue(), p.getTrainingValue());
        System.out.printf("passenger deboarding destination: %s\n", p.getDeboarding().getName());
        /**
         * The last parameter has to be one of the currently available Destinations or
         * the next Destination available on the schedule
        */
        return p;
    }
}
