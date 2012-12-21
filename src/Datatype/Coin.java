package Datatype;

/**
 *
 * @author Linda
 */
public class Coin {

    private int coin;

    public Coin() {
        coin = 1;
    }

    public void addCoins(int c) {
        coin += c;
    }

    public void resetCoins() {
        coin = 0;
    }
}
