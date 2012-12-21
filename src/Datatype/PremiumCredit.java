/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datatype;

/**
 *
 * @author Linda
 */
public class PremiumCredit {

    private int premiumCredit;

    public PremiumCredit() {
        premiumCredit = 1;
    }

    public void addPremiumCredit(int pc) {
        premiumCredit += pc;
    }

    public void resetPremiumCredit() {
        premiumCredit = 0;
    }
}
