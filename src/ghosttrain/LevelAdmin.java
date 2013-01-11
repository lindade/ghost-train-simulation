package ghosttrain;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * this class sets the level of the player
 * by counting how many passengers have been dropped off
 * at the right destination
 * 
 * @author Linda
 */
public class LevelAdmin {
    
    private int dropOffCounter;
    private int level;
    private int index;
    /**
     * raiseLevel represents the number of passenger to drop off to reach the next level
     */
    private final int[] RAISE_LEVEL = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    
    /**
     *
     */
    public LevelAdmin(){
        dropOffCounter = 0;
        level = 1;
        index = 0;
    }
    
    /**
     * raise the level of the player 
     * @param player 
     */
    public void raiseLevel(){
        if(dropOffCounter == RAISE_LEVEL[index]){
            level++;
            index++;
            System.out.println("level was increased");
        }
    }
    
    public void incrementDropOffCounter(){
        dropOffCounter++;
    }

    public int getLevel() {
        return level;
    }
    
    
    
}