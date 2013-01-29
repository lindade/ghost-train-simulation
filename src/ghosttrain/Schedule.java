package ghosttrain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Linda
 */
public class Schedule {

    private static final String[] AVAILABLE_CITIES = {"Limbo", "London",
        "Berlin", "Cairo", "Beijing", "Istanbul", "Sydney", "Rio de Janiero",
        "San Francisco", "New York", "Havana", "Moscow", "Burial Ground",
        "Necropolis", "Underworld", "City of the Dead"};
    // distance from preceding city to this city
    private static final int[] DISTANCE_TO_CITIES = { 1400, 1600, 1800, 2000,
        4000, 6000, 8000, 10000, 12000, 14000, 16000, 18000, 20000, 20200, 20400, 20600, 20800};
    private static int availableCities = 3; // initially only "Limbo", "London", "Berlin"
    private int currentCity = 0; // starting in Limbo1
    private ArrayList<Destination> currentSchedule;
    public static final HashMap<String, Integer> DESTINATIONS; // contains the destination name and the index of the destination list
    
    /**
     * HashMap -> (DestinationName, Integer)
     */
    static {
        DESTINATIONS = new HashMap<>();
        int index = 0;
        for( String city : AVAILABLE_CITIES ) {
            DESTINATIONS.put(city, index);
            index++;
        }
    }
    
    /** 
     * ArrayList<Destination>
     * 
     * 
     **/
    public Schedule(int initCities) {
        availableCities = initCities;
        currentSchedule = new ArrayList<Destination>();
        for (int i = 0; i < AVAILABLE_CITIES.length; i++) {
            currentSchedule.add(new Destination(AVAILABLE_CITIES[i], DISTANCE_TO_CITIES[i]));
        }
    }
    
    public Schedule() {
        this(3);
    }

    public int getCitiesInWorld() {
        return AVAILABLE_CITIES.length;
    }
    
    public Destination getDesiredDestination(int index) {
        if( index <= availableCities) {
            return currentSchedule.get(index);
        }
        else{
            return null;
        }
    }
    
    public Destination getDesiredDestination(String cityName) {
        int index = DESTINATIONS.get(cityName);
        return currentSchedule.get(index);
    }

    /**
     * @return the availableCities
     */
    public int getAvailableCities() {
        return availableCities;
    }

    /**
     * @param availableCities 
     * If Level Up the availableCities to set
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
        if (currentCity == availableCities) {
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