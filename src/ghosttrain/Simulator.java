package ghosttrain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import logging.GtLoggingLevel;

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
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(new File("./log.properties")));
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found!");
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioex) {
            System.out.println("IOEx");
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ioex);
        }
        
        //instantiate and initialize player and train
        this.player = new Player();
        this.player.getTrain().setTimeStep(step);
    }

    public Simulator(File input) {
        //initialize using inputs
    }

    public void simulate() {
        int timepaces = 0;
        // stop if Level 50 is reached
        while (player.getLevel() < 50) {
            //log.info("Level: " + player.getLevel());
            timepaces += step;
            player.getTrain().update();
            player.update(timepaces);
        }
        log.log(GtLoggingLevel.GT_INFO, "Congratulations! You have reached Level {0}", player.getLevel());
    }

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.simulate();
    }
}
