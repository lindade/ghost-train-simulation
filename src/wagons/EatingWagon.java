package wagons;

import exceptions.MaxWagonCountReached;
import ghosttrain.Passenger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class EatingWagon extends ActivityWagon {

    private static final Logger log = Logger.getLogger(EatingWagon.class.getName());

    
    public EatingWagon() throws MaxWagonCountReached {
        super();
    }

    @Override
    public void fillBucket() {
        bucket.fillBucket(demandEarning());
    }

    @Override
    public int demandEarning() {
        int earnings = 0;
        for (Passenger p : getPassengers()) {
            earnings += p.getEatingValue();
        }
        log.log(Level.FINEST, "Eating earnings: {0}", earnings);
        return earnings;
<<<<<<< HEAD
    }

    @Override
    public void addPassenger(Passenger p) {
        // has to be limited to 3 persons
        addPassenger(p);
        // just for the output on the console
        System.out.print(this.printName() + "Passenger List: ");
        this.printList();
        System.out.println();
    }
    
    @Override
    public void printList(){
        for(Passenger pas: getPassengers()){
            System.out.print(pas.getName() + "\t");
        }
    }

=======
    } 
>>>>>>> update-readme
}
