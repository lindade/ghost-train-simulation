package ghosttrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class Schedule implements ScheduleUpdateListener {

    public static String[] AVAILABLE_CITIES = {"Limbo", "London",
        "Paris", "Cairo", "Beijing", "Tokyo", "Sydney", "Rio de Janiero",
        "San Francisco", "New York", "Havana", "Burial Ground",
        "Necropolis", "Underworld", "City of the Dead", "Moscow"};
    // distance from preceding city to this city
//    private static final String[] AVAILABLE_CITIES = {"Limbo", "San Francisco",
//        "Cairo", "Beijing", "Burial Ground ","London", "Paris", "Tokyo",
//        "Sydney", "Rio de Janiero", "New York", "Havana", "Necropolis",
//        "Underworld", "City of the Dead", "Moscow"};
//    private static final int[] DISTANCE_TO_CITIES = { 9, 34, 75, 134,
//        209, 300, 409, 534, 675, 834, 1009, 1200, 1409, 1634, 1875, 2134};
    public static int[] DISTANCE_TO_CITIES = {324, 1224, 2700, 4824,
        7524, 10800, 14724, 19224, 24300, 30024, 36324, 43200, 50724, 58824, 67500, 76824};
    //0,09h = 5,4min = 324sek
    //0,34h = 20,4min = 1224sek
    //0,75h = 45min = 2700sek
    //1,34h = 4824sek
    //2,09h = 7524sek
    //3,00h = 10800sek
    //4,09h = 14724sek
    //5,34h = 19224sek
    //6,75h = 24300sek
    //8,34h = 30024sek
    //10,90h = 36324sek
    //12,00h = 43200sek
    //14,09h = 50724sek
    //16,34h = 58824sek
    //18,75h = 67500sek
    //21,34h = 76824sek
    private static int availableCities = 2; // initially only "Limbo", "London"
    private int currentCity = 0; // starting in Limbo
    private ArrayList<Destination> currentSchedule;
    public static int[] experiencePerPassenger = new int[]{
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
    };
    public static HashMap<String, Integer> DESTINATIONS; // contains the destination name and the index of the destination list
    private static final Logger log = Logger.getLogger(Schedule.class.getName());

    /**
     * HashMap -> (DestinationName, Integer)
     */
    static {
        updateDestinations();
    }

    public static void updateDestinations() {
        DESTINATIONS = new HashMap<>();
        int index = 0;
        for (String city : AVAILABLE_CITIES) {
            DESTINATIONS.put(city, index);
            index++;
        }
    }

    /**
     * ArrayList<Destination>
     *
     *
     *
     */
    public Schedule(int initCities) {
        initSchedule(initCities);
    }

    /**
     * (Re-)Init values based on static arrays. If Configurator changes the
     * static base arrays the Schedule has to update its settings.
     *
     * @param initCities
     */
    public void initSchedule(int initCities) {
        availableCities = initCities;
        currentSchedule = new ArrayList<>();
        for (int i = 0; i < AVAILABLE_CITIES.length; i++) {
            currentSchedule.add(new Destination(AVAILABLE_CITIES[i], DISTANCE_TO_CITIES[i], experiencePerPassenger[i]));
        }
    }

    public Schedule() {
        this(3);
    }

    public int getCitiesInWorld() {
        return AVAILABLE_CITIES.length;
    }

    public Destination getDesiredDestination(int index) {
        if (index <= availableCities && index < getCitiesInWorld()) {
            return currentSchedule.get(index);
        } else {
            return null;
        }
    }

    /**
     * @return the availableCities
     */
    public int getAvailableCities() {
        return availableCities;
    }

    /**
     * @param availableCities If Level Up the availableCities to set
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

    public static boolean doesDestinationExist(String destination) {
        Integer index = DESTINATIONS.get(destination);
        if (index != null) {
            return index < availableCities;
        }
        return false;
    }

    @Override
    public void updateSchedule() {
        if (getAvailableCities() < getCitiesInWorld()) {
            setAvailableCities(getAvailableCities() + 1);
            log.log(Level.INFO, "available cities: {0} cities in world: {1}", new Object[]{getAvailableCities(), getCitiesInWorld()});
//            log.log(Level.FINEST, "updated Schedule to {0} available Cities.", (getAvailableCities()));
        }
    }

    public List<Destination> getDestinations() {
        return Collections.unmodifiableList(currentSchedule);
    }
}
