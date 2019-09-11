package ghosttrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private int expCounter;
    private int level;
    private Random r = new Random();
    private List<LevelListener> levelListeners;
    private ScheduleUpdateListener scheduleUpdateListener;
    private PremiumCreditListener premiumCreditListener;
    private int index;
    /**
     * RAISE_LEVEL represents the number of passenger to drop off to reach the
     * next level
     */
    private int[] RAISE_LEVEL = {3, 12, 23, 48, 70, 96, 126, 200, 248, 300, 358,
        504, 585, 672, 765, 1008, 1131, 1260, 1397, 1760, 1932, 2112, 2300, 2808,
        3038, 3276, 3524, 4200, 4495, 4800, 5115, 5984, 6353, 6732, 7123, 8208,
        8658, 9120, 9594, 10920, 11460, 12012, 12578, 14168, 14805, 15456, 16121,
        18000, 18743};
//    =(Level^2+2*Level)*(availableCities/2)
    /**
     * Level 1, 2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 50 Station: 2,
     * 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
     */
    private int[] ADD_DESTINATION = {2, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 50};
    int indexAddDest;
    private static final Logger log = Logger.getLogger(LevelAdmin.class.getName());

    public void setAddDestination(int[] addDestination) {
        ADD_DESTINATION = addDestination;
    }

    /**
     *
     */
    public LevelAdmin() {
        dropOffCounter = 0;
        level = 1;
        index = 0;
        indexAddDest = 0;
        levelListeners = new ArrayList<>(0);
    }

    public void initExperiencePointArray(int[] level) {
        RAISE_LEVEL = level;
    }

    /**
     * raise the level of the player
     *
     * @param player
     */
    public void raiseLevel() {
        log.log(Level.FINEST, "Raise level! index: {0} RAISE_LEVEL.length: {1}", new Object[]{index, RAISE_LEVEL.length});
        if (index < RAISE_LEVEL.length) {
            if ( expCounter >= RAISE_LEVEL[index] ) {
                level++;
                index++;
                for (LevelListener listener : levelListeners) {
                    listener.levelUp(level);
                }
                log.log(Level.FINEST, "level was increased. You are now at level {0}", getLevelFromLevelAdmin());
                if (level >= ADD_DESTINATION[indexAddDest]) {
                    scheduleUpdateListener.updateSchedule();
                    indexAddDest++;
                }
            }
        }
    }

    public void chanceToGetPC() {
        if (getLevelFromLevelAdmin() == 1) {
            notifyPremiumCreditAdded();
        } else if (getLevelFromLevelAdmin() > 1 && getLevelFromLevelAdmin() <= 5) {
            int x = r.nextInt(100);
            if (x < 40) {
                notifyPremiumCreditAdded();
            }
        } else if (getLevelFromLevelAdmin() > 5 && getLevelFromLevelAdmin() <= 10) {
            int x = r.nextInt(100);
            if (x < 20) {
                notifyPremiumCreditAdded();
            }
        } else if (getLevelFromLevelAdmin() > 10 && getLevelFromLevelAdmin() <= 20) {
            int x = r.nextInt(100);
            if (x < 10) {
                notifyPremiumCreditAdded();
            }
        } else if (getLevelFromLevelAdmin() > 20) {
            int x = r.nextInt(100);
            if (x < 5) {
                notifyPremiumCreditAdded();
            }
        }
    }

    private void notifyPremiumCreditAdded() {
        premiumCreditListener.addPremiumCredit();
    }

    public int getDropOffCounter() {
        return dropOffCounter;
    }

    public int getExpCounter() {
        return expCounter;
    }

    public int getLevelFromLevelAdmin() {
        return level;
    }

    @Override
    public void passengersGotOff(int numOfPassengers, Destination dest) {
        dropOffCounter += numOfPassengers;
        expCounter += numOfPassengers * (dest.getPassengerExperience());
        log.log(Level.FINEST, "Drop of counter was increased by {0}", numOfPassengers);
        log.log(Level.FINEST, "Drop of counter is {0}", dropOffCounter);
        log.log(Level.FINEST, "exp {0}", expCounter);
        for (int i = 0; i < numOfPassengers; i++) {
            chanceToGetPC();
        }
        // try to raise level
        raiseLevel();
    }

    void addLevelListener(LevelListener levelListener) {
        levelListeners.add(levelListener);
    }

    void addScheduleUpdateListener(ScheduleUpdateListener scheduleUpdateListener) {
        this.scheduleUpdateListener = scheduleUpdateListener;
    }

    void addPremiumCreditListener(PremiumCreditListener premiumCreditListener) {
        this.premiumCreditListener = premiumCreditListener;
    }
}