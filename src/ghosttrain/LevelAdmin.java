package ghosttrain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class sets the level of the player by counting how many passengers have
 * been dropped off at the right destination
 *
 * @author Linda
 */
public class LevelAdmin implements PassengerListener {

    private int dropOffCounter;
    private int level;
    private int index;
    private LevelListener levelListener;
    /**
     * RAISE_LEVEL represents the number of passenger to drop off to reach the
     * next level
     */
//    private final int[] RAISE_LEVEL = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private final int[] RAISE_LEVEL = {3, 8, 15, 24, 35, 48, 63, 80, 99, 120, 143, 168, 195,
        224, 255, 288, 323, 360, 399, 440, 483, 528, 575, 624, 675, 728, 783, 840, 899, 960,
        1023, 1088, 1155, 1224, 1295, 1368, 1443, 1520, 1599, 1680, 1763, 1848, 1935, 2024,
        2115, 2208, 2303, 2400, 2499, 2600, 2703};
//    =dropOffCounter^2+2*dropOffCounter

    /**
     *
     */
    public LevelAdmin() {
        dropOffCounter = 0;
        level = 1;
        index = 0;
    }

    /**
     * raise the level of the player
     *
     * @param player
     */
    public void raiseLevel() {
        if ((dropOffCounter == RAISE_LEVEL[index]) || (dropOffCounter >= RAISE_LEVEL[index])) {
            level++;
            index++;
            levelListener.levelUp(level);
            System.out.println("level was increased");
        }
    }

    public int getLevelFromLevelAdmin() {
        return level;
    }

    @Override
    public void passengersGotOff(int numOfPassengers) {
        dropOffCounter += numOfPassengers;
        System.out.println("Drop of counter was increased by " + numOfPassengers);
        System.out.println("Drop of counter is " + dropOffCounter);
        // try to raise level
        raiseLevel();
    }

    void addLevelListener(LevelListener levelListener) {
        this.levelListener = levelListener;
    }
}