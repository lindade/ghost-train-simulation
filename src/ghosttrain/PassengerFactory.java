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
                dependingOnDestination = 3;
                break;
            case "London":
                dependingOnDestination = 3;
                break;
            case "Berlin":
                dependingOnDestination = 3;
                break;
            case "Cairo":
                dependingOnDestination = 5;
                break;
            case "Beijing":
                dependingOnDestination = 5;
                break;
            case "Istanbul":
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
            case "Moscow":
                dependingOnDestination = 9;
                break;
            case "Burial Ground":
                dependingOnDestination = 9;
                break;
            case "Necropolis":
                dependingOnDestination = 12;
                break;
            case "Underworld":
                dependingOnDestination = 12;
                break;
            case "City of the Dead":
                dependingOnDestination = 12;
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
                r.nextInt(dependsOnDestination), schedule.getDesiredDestination(r.nextInt(available+1))); //weigthedRandom(available)
                /**
                 * The last parameter has to be one of the currently available Destinations or
                 * the next Destination not yet available on the schedule
                */
        System.out.printf("passenger parameter: %d %d %d\n", p.getEatingValue(), p.getFunValue(), p.getTrainingValue());
        System.out.printf("passenger deboarding destination: %s\n", p.getDeboarding().getName());
        return p;
    }
    
//    private int weigthedRandom(int availableCities){
//        int randomDestination;
//        int random = r.nextInt((availableCities+1)*10);
//        if(random <= 20 ){
//            randomDestination = 1;
//        } else if(random > 20) {
//            randomDestination = 2;
//        }
//            
//        return randomDestination;
//    }
}
