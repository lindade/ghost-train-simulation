package ghosttrain;

import java.io.File;
import java.util.logging.Logger;

/**
 * The simulator creates cycle the train travels the XML file is read in
 *
 * @author Linda
 */
public class Simulator {

    private Player player;
    private int step = 12;
    private static final Logger log = Logger.getLogger(Simulator.class.getName());

    /**
     * here the player is loaded and the train too
     *
     * @param player
     */
    public Simulator() {
        //instamtiate and initialize player and train
        this.player = new Player();
        this.player.getTrain().setTimeStep(step);
    }
    
    public Simulator(File input) {
        //initialize using inputs
    }
    
    public void simulate() {
        int timepaces = 0;
        // stop if Level 50 is reached
        while (player.getLevel() < 5) {
            log.info("Level: " + player.getLevel());
            timepaces += step;
            player.getTrain().update();
            player.update();
        }
    }

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.simulate();
    }
}
