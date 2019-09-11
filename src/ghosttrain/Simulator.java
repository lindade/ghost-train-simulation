package ghosttrain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import util.Configurator;

/**
 *
 * @author Linda
 */
public class Simulator implements LevelListener {

    private Player player;
    private LevelAdmin la;
    private int step = 12;
    private static final Logger log = Logger.getLogger(Simulator.class.getName());

    /**
     *
     * @param player
     */
    public Simulator() {
        //TODO: load Logging properties
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(new File("./log.properties")));
        } catch (FileNotFoundException ex) {
            log.log(Level.FINE, "File Not Found!");
            log.log(Level.SEVERE, null, ex);
        } catch (IOException ioex) {
            log.log(Level.FINE, "IOEx");
            log.log(Level.SEVERE, null, ioex);
        }

        //instantiate and initialize player and train
        la = new LevelAdmin();
        this.player = new Player(la);
        this.player.getTrain().setTimeStep(step);
        la.addLevelListener(this);
    }

    public Simulator(int step, File input) {
        this();
        Configurator configurator = new Configurator(input);
        configurator.initGame(la, player);
    }

    public static void main(String[] args) {
        Simulator sim = null;
        if (args.length == 2) {
            //12, new File("F:/Bachelorarbeit/Balancing/Balancing.plist")
            int step = Integer.parseInt(args[0]);
            File f = new File( args[1]);
            sim = new Simulator(step, f.getAbsoluteFile());
        } else {
             sim = new Simulator();
        }
        sim.simulate();
    }

    public void simulate() {
        int timepaces = 0;
        // stop if Level 50 is reached
        while (player.getLevel() < 50) {
//            log.log(Level.INFO, "Level: {0}", player.getLevel());
//            log.log(Level.INFO, "timepaces: {0}", timepaces);
            timepaces += step;
            player.getTrain().update();
            player.update(timepaces);
        }
        log.log(Level.INFO, "Congratulations! You have reached Level {0}", player.getLevel());
        log.log(Level.INFO, "Total play time in hours {0}", ((player.getTrain().getTotalTime() / 60) / 60));
        log.log(Level.INFO, "Total play time endless days {0}", player.getTrain().getTotalTime() / 60 / 60 / 24);
        log.log(Level.INFO, "Dropped off passengers {0}", la.getDropOffCounter());
        log.log(Level.INFO, "Accumulated EXP {0}", la.getExpCounter());
        //log.log(Level.INFO, "Coins {0}", player.getWallet().getCoins());
        log.log(Level.INFO, "Premium Credits {0}", player.getWallet().getPC());
    }

    @Override
    public void levelUp(int level) {
        log.log(Level.INFO, "Reached Level {0} after {1} min play time.", new Object[]{player.getLevel(), player.getTrain().getTotalTime() / 60});
    }
}