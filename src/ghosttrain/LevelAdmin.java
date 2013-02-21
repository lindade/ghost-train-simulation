package ghosttrain;


/**
 * this class sets the level of the player by counting how many passengers have
 * been dropped off at the right destination
 *
 * @author Linda
 */
public class LevelAdmin implements PassengerListener {

    private int dropOffCounter;
    private int expCounter;
    private int level;
    private LevelListener levelListener;
    private ScheduleUpgradeListener scheduleUpgradeListener;
    private int index;
    /**
     * RAISE_LEVEL represents the number of passenger to drop off to reach the
     * next level
     */
   private int[] RAISE_LEVEL = {3, 12, 23, 48, 70, 96, 126, 200, 248, 300, 358,
       504, 585, 672, 765, 1008, 1131, 1260, 1397, 1760, 1932, 2112, 2300, 2808,
       3038, 3276, 3524, 4200, 4495, 4800, 5115, 5984, 6353, 6732, 7123, 8208,
       8658, 9120, 9594, 10920, 11460, 12012, 12578, 14168, 14805, 15456, 16121,
       18000, 18743, 19500, 20273};
//    =(Level^2+2*Level)*(availableCities/2)
   /**
    * Level:       1, 2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 50
    * Station:  2, 3, 4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16
    */
   private int[] ADD_DESTINATION = {2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 50};
   int indexAddDest;
   
    /**
     *
     */
    public LevelAdmin() {
        dropOffCounter = 0;
        level = 1;
        index = 0;
        indexAddDest = 0;
    }

    /**
     * raise the level of the player
     *
     * @param player
     */
    public void raiseLevel() {
        if ((expCounter == RAISE_LEVEL[index]) || (dropOffCounter >= RAISE_LEVEL[index])) {
            level++;
            index++;
            levelListener.levelUp(level);
            System.out.println("level was increased");
            if(level == ADD_DESTINATION[indexAddDest]){
                scheduleUpgradeListener.updateSchedule();
                indexAddDest++;
            }
        }
    }

    public int getLevelFromLevelAdmin() {
        return level;
    }

    @Override
    public void passengersGotOff(int numOfPassengers, Destination dest) {
        dropOffCounter += numOfPassengers;
        expCounter += numOfPassengers * (Schedule.DESTINATIONS.get(dest.getName()) + 1);
        System.out.println("Drop of counter was increased by " + numOfPassengers);
        System.out.println("Drop of counter is " + dropOffCounter);
        System.out.println("exp " + expCounter);
        // try to raise level
        raiseLevel();
    }

    void addLevelListener(LevelListener levelListener) {
        this.levelListener = levelListener;
    }

    void addScheduleUpgradeListener(ScheduleUpgradeListener scheduleUpgradeListener) {
        this.scheduleUpgradeListener = scheduleUpgradeListener;
    }
}