package ghosttrain;


/**
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
    
    public void getCoins(){
        System.out.println("Coins: " + coins);
    }
        
    public void addPremiumCredits(int premiumCredits){
        pc += premiumCredits;
    }
    
    public void getPremiumCredits(){
        System.out.println("PremiumCredits: " + pc);
    }
}
