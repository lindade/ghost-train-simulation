package ghosttrain;


/**
 * Wallet contains all coins and premiumCredits one player possesses
 * The player can earn coins and premiumCredits and freely spent as he wishes.
 * 
 * @author Linda
 */
public class Wallet {

    private int coins = 575;
    private int pc;

    public Wallet() {
    }
    
    public void addCoins(int coin){
        coins += coin;
    }
    
    public void subCoins(int coin){
        coins -= coin;
    }
    
    public int getCoins(){
        //log.info("Coins: " + coins);
        return coins;
    }
        
    public void addPC(int premiumCredits){
        pc += premiumCredits;
    }
    
    public void subPC(int premiumCredits){
        pc -= premiumCredits;
    }
    
    public int getPC(){
        //log.info("PremiumCredits: " + pc);
        return pc;
    }
}