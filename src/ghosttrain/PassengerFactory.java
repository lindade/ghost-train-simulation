package ghosttrain;

import ghosttrain.Destination;
import ghosttrain.Passenger;
import java.util.Random;

/**
 *
 * @author Linda
 */
public class PassengerFactory {

    private Destination destination;
    private int dependsOnDestination;
    private Random r = new Random();

    public PassengerFactory() {
    }

    /**
     *
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

    public Passenger createPassenger() {
//        getRandNumDependOnDest(train.getCurrentDestination());
//        creates random numbers between one and 5
//        1 + random.nextInt(6);
//        creates random numbers between zero and 5
//        random.nextInt(6);
        Passenger p = new Passenger("Eins",1 + r.nextInt(dependsOnDestination),1 + r.nextInt(dependsOnDestination),1 + r.nextInt(dependsOnDestination), new Destination("Limbo"));
        return p;
    }
}
