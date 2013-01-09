package ghosttrain;

import java.util.ArrayList;
import java.util.List;
import wagons.Wagon;

/**
 * This class should sort the passengers into the wagons
 * The Passengers with the highest value should be sorted into the particular
 * wagon of that value to earn the highest possible amount of coins
 * 
 * One other method puts the passengers randomly into the wagons
 * 
 * Before the arrival of the train in the station the passengers who can
 * get off the train will be placed into an passenger wagon.
 * 
 * @author Linda
 */
public class PassengerSorter {
    
    /**
     * The list of wagons contains all wagons which are owned by the player
     * 
     * PassengerSorter contains all passengers who are in the train AND
     * the new passengers who are boarding the train.
     */
    List<Passenger> passengerSorter = new ArrayList<Passenger>();
    List<Wagon> wagons;

    
    
    
    /**
     * this method is already in wagon
     * delete or edit?
     * @param p 
     */
    public void addToWagon(Passenger p) {
        if (passengerSorter.size() <= 2) {
            passengerSorter.add(p);
            System.out.println("Passenger added to List");
        } else {
            System.out.println("Wagon capacity is already exhausted");
        }
    }
    
    /**
     * this method has to be optimized
     * @param passengerList 
     */
     public void sortOptimalInWagon(List<Passenger> passengerList){
         for(Passenger p: passengerList){
         if((p.getFunValue() >= p.getEatingValue()) && (p.getFunValue() >= p.getTrainingValue())){
             //in FunWagon 
         } if(p.getEatingValue() >= p.getTrainingValue()){
             //in EatingWagon
         } else{
             // in TrainingWagon
         }
         }
     }
     
     public void sortRandomInWagon(List<Passenger> passengerList){
         for(Passenger p : passengerList){
             //put p into any wagon
         }
     }
}
