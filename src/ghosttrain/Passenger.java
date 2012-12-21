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

    public Passenger(String n, int f, int e, int t, Destination d) {
        name = n;
        fun = f;
        eating = e;
        training = t;
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
}
