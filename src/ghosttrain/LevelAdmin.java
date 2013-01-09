package ghosttrain;

/**
 * this class sets the level of the player
 * by counting how many passengers have been dropped off
 * at the right destination
 * 
 * @author Linda
 */
public class LevelAdmin {
    
    private int dropOffCounter;
    /**
     * raiseLevel represents the number of passenger to drop off to reach the next level
     * has to be increased after each level-up
     */
    private int raiseLevel;
    
    public LevelAdmin(){
        dropOffCounter = 0;
        raiseLevel = 3;
    }
    
    /**
     * raise the level of the player 
     * @param player 
     */
    public void raiseLevel(Player player){
        if(dropOffCounter == raiseLevel){
            player.raiseLevel();
        }
    }
    
    public void incrementDropOffCounter(){
        dropOffCounter++;
    }
    
    
    
}
