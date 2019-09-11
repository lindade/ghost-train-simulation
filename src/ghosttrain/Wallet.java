package ghosttrain;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Wallet contains all coins and premiumCredits one player possesses
 * The player can earn coins and premiumCredits and freely spent as he wishes.
 * 
 * @author Linda
 */
public class Wallet {

    private int coins = 575;
    private int pc;
    private static final Logger log = Logger.getLogger(Wallet.class.getName());


    public Wallet() {
    }
    
    public void addCoins(int coin){
        coins += coin;
    }
    
    public void subCoins(int coin){
        coins -= coin;
    }
    
    public int getCoins(){
        log.log(Level.FINEST, "Coins: {0}", coins);
        return coins;
    }
        
//    public void addPC(int premiumCredits){
//        pc += premiumCredits;
//    }
    
    public void addPC(){
        pc += 1;
    }
    
    public void initPC(int credits) {
        pc = credits;
    }
    
    public void initCoins(int coins) {
        this.coins = coins;
    }
    
    public void subPC(int premiumCredits){
        pc -= premiumCredits;
    }
    
    public int getPC(){
        log.log(Level.FINEST, "PremiumCredits: {0}", pc);
        return pc;
    }
}