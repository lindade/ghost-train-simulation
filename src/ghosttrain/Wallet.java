package ghosttrain;


/**
 * Wallet contains all coins and premiumCredits one player possesses
 * The player can earn coins and premiumCredits and freely spent as he wishes.
 * 
 * @author Linda
 */
public class Wallet {

    private int coins;
    private int pc;

    public Wallet() {
    }
    
    public void addCoins(int coin){
        coins += coin;
    }
    
    public void subCoins(int coin){
        coins -= coin;
    }
    
    public void getCoins(){
        System.out.println("Coins: " + coins);
    }
        
    public void addPC(int premiumCredits){
        pc += premiumCredits;
    }
    
    public void subPC(int premiumCredits){
        pc -= premiumCredits;
    }
    
    public void getPC(){
        System.out.println("PremiumCredits: " + pc);
    }
}