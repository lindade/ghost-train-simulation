package ghosttrain;

/**
 *
 * @author Linda
 */
public class Destination {

    private String name;
    private int distance;

    public Destination(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public Destination() {
        name = "Default";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
