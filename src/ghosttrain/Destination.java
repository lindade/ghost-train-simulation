package ghosttrain;

/**
 *
 * @author Linda
 */
public class Destination {

    private String name;
    private int distance;
    private int passengerExperience;

    public Destination(String name, int distance, int passengerExperience) {
        this.name = name;
        this.distance = distance;
        this.passengerExperience = passengerExperience;
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
    
    public int getPassengerExperience() {
        return passengerExperience;
    }
}