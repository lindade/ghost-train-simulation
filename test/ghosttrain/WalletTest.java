package ghosttrain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Linda
 */
public class WalletTest {
    
    public WalletTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCoins method, of class Wallet.
     */
    @Test
    public void testAddCoins() {
        System.out.println("addCoins");
        int coin = 0;
        Wallet instance = new Wallet();
        instance.addCoins(coin);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of subCoins method, of class Wallet.
     */
    @Test
    public void testSubCoins() {
        System.out.println("subCoins");
        int coin = 0;
        Wallet instance = new Wallet();
        instance.subCoins(coin);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCoins method, of class Wallet.
     */
    @Test
    public void testGetCoins() {
        System.out.println("getCoins");
        Wallet instance = new Wallet();
        instance.getCoins();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addPC method, of class Wallet.
     */
    @Test
    public void testAddPC() {
        System.out.println("addPC");
        int premiumCredits = 0;
        Wallet instance = new Wallet();
        instance.addPC(premiumCredits);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of subPC method, of class Wallet.
     */
    @Test
    public void testSubPC() {
        System.out.println("subPC");
        int premiumCredits = 0;
        Wallet instance = new Wallet();
        instance.subPC(premiumCredits);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPC method, of class Wallet.
     */
    @Test
    public void testGetPC() {
        System.out.println("getPC");
        Wallet instance = new Wallet();
        instance.getPC();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
