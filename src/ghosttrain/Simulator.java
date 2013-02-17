package ghosttrain;

import java.io.File;

/**
 * The simulator creates cycle the train travels the XML file is read in
 *
 * @author Linda
 */
public class Simulator {

    private Player player;
    private Train train;
    private volatile boolean finished = false;

    /**
     * here the player is loaded and the train too
     *
     * @param player
     * @param train
     */
    public Simulator() {
        //instamtiate and initialize player and train
    }
    
    public Simulator(File input) {
        //initialize using inputs
    }
    
    public void simulate() {
        int timepaces = 0;
        int step = 10;
        while (!finished) {
            timepaces += step;
            //player.update();
            //train.update();
            //train hat nächstes ziel erreich
                //-> kaufe PW oder AW oder keinen Wagon
                //-> kaufe Engine upgrade?
                //-> bucket upgrade?
            //sonst
                // passenger switching
                // buckets auffüllen jede volle minute
                // spieler spielt 3x am tag 15mim
                    // sammelt ein, wenn voll oder wenn er gerade spielt
        }
    }

    public void main(String[] args) {
        Simulator sim = new Simulator();
        sim.simulate();
    }
}
