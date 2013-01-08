package ghosttrain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Linda
 */
public class Schedule {

    private static final String[] AVAILABLE_CITIES = {"Limbo1", "London", "Paris",
        "Cairo", "Peking", "Tokyo", "Sydney", "Rio de Janiero", "San Francisco",
        "New York", "Havanna", "Limbo2", "Limbo3", "Limbo4", "Limbo5"};
    // distance from preceding city to this city
    private static final int[] DISTANCE_TO_CITIES = {2000, 4000, 6000, 8000, 5000};
    private static int availableCities = 3; // initially only "Limbo1", "London", "Paris"
    private int currentCity = 0; // starting in Limbo1
    private ArrayList<Destination> currentSchedule;
    public static final HashMap<String, Integer> DESTINATIONS;
    
    static {
        DESTINATIONS = new HashMap<>();
        int index = 0;
        for( String city : AVAILABLE_CITIES ) {
            DESTINATIONS.put(city, index);
            index++;
        }
    }
    
    /**
     * HashMap -> (DestinationName, Int)
     * ArrayList<Destination>
     * 
     * 
     **/
    

    public Schedule(int initCities) {
        availableCities = initCities;
        currentSchedule = new ArrayList<Destination>(availableCities);
        for (int i = 0; i < availableCities; i++) {
            currentSchedule.add(new Destination(AVAILABLE_CITIES[i], DISTANCE_TO_CITIES[i]));
        }
    }

    public Schedule() {
        this(3);
    }

    public int getCitiesInWorld() {
        return AVAILABLE_CITIES.length;
    }

    /**
     * @return the availableCities
     */
    public int getAvailableCities() {
        return availableCities;
    }

    /**
     * @param availableCities the availableCities to set
     */
    public void setAvailableCities(int availableCities) {
        this.availableCities = availableCities;
    }

    /**
     * @return the currentCity
     */
    public int getCurrentCity() {
        return currentCity;
    }

    /**
     * @param currentCity the currentCity to set
     */
    public void setCurrentCity(int currentCity) {
        if (currentCity == availableCities - 1) {
            this.currentCity = 0;
        } else {
            this.currentCity = currentCity;
        }
    }

    public Destination getCurrentStop() {
        return currentSchedule.get(currentCity);
    }

    public Destination getNextStop() {
        if (currentCity == availableCities - 1) {
            return currentSchedule.get(0);
        } else {
            return currentSchedule.get(currentCity + 1);
        }
    }
    
    public static  boolean doesDestinationExist(String destination ) {
        Integer index =  DESTINATIONS.get(destination);
        if ( index != null) {
            return index < availableCities;
        }
        return false;
    }
}
