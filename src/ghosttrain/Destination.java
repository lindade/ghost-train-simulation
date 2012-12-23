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

    public Destination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
