package ghosttrain;

/**
 *
 * @author Linda
 */
public class Passenger {

    private String name;
    private int fun;
    private int eating;
    private int training;
    private Destination deboarding;

    public Passenger(String name, int fun, int eating, int training, Destination d) {
        this.name = name;
        this.fun = fun;
        this.eating = eating;
        this.training = training;
        deboarding = d; // Eine die schon dabei ist oder die nächste die freigeschaltet wird.
    }

    public String getName() {
        return name;
    }

    public int getFunValue() {
        return fun;
    }

    public int getEatingValue() {
        return eating;
    }

    public int getTrainingValue() {
        return training;
    }

    public Destination getDeboarding() {
        return deboarding;
    }

    @Override
    public String toString() {
        return String.format("Passenger '%s' [e:%s, f:%s, t:%s] destination: %s", getName(), getEatingValue(), getFunValue(), getTrainingValue(), getDeboarding().getName());
    }
    
    
}