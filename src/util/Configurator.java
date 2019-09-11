package util;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;
import exceptions.MaxWagonCountReached;
import ghosttrain.Bucket;
import ghosttrain.Destination;
import ghosttrain.LevelAdmin;
import ghosttrain.Player;
import ghosttrain.Schedule;
import ghosttrain.Store;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wagons.PassengerWagon;
import wagons.Wagon;

/**
 *
 * @author Linda
 */
public class Configurator {

    private static final Logger log = Logger.getLogger(Configurator.class.getName());
    private final File plistFile;
    private final NSDictionary pListRoot;
    private static final String START_PARAMETER = "startParameter";
    private static final String LEVEL_REWARDS = "levelRewards";
    private static final String DESTINATIONS = "destinations";
    //cost curve of wagons
    private static final String WAGONS = "wagons";
    private static final String ENGINES = "engines";
    private static final String EXPERIENCE_POINTS = "experiencePoints";
//    private static final String IN_APP_PURCHASE = "inAppPurchase";
    private static final String GAME_SETTINGS = "gameSettings";

    public Configurator(String filePath) {
        this(new File(filePath));
    }

    public Configurator(File f) {
        this.plistFile = f;
        this.pListRoot = readPList(f);
    }

    public NSDictionary readPList(File f) {
        try {
            return (NSDictionary) com.dd.plist.PropertyListParser.parse(plistFile);
        } catch (Exception ex) {
            Logger.getLogger(Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void initGame(LevelAdmin la, Player pl) {
        initStartParameters(la, pl);
        initLevelRewards(la, pl);
        initGameSettings(la, pl);
        initExperiencePoints(la);
        initPrices(la, pl);
        initDestinations(la, pl);
        pl.getTrain().initValues();
        pl.getTrain().getSchedule().initSchedule(pl.getTrain().getSchedule().getAvailableCities());
        logStartParamters(pl, la);
    }

    private void initStartParameters(LevelAdmin la, Player pl) {
        log.info("Initializing Start Parameters using configuration file.");
        NSDictionary startParameters = (NSDictionary) pListRoot.objectForKey(START_PARAMETER);
        NSNumber level = (NSNumber) startParameters.objectForKey("level");
        log.log(Level.INFO, "Set player level to: {0}", level.intValue());
        pl.setLevel(level.intValue());

        NSNumber destinationCount = (NSNumber) startParameters.objectForKey("destinationCount");
        log.log(Level.INFO, "Set available Cities to: {0}", destinationCount.intValue());
        pl.getTrain().getSchedule().setAvailableCities(destinationCount.intValue());

        NSString engine = (NSString) startParameters.objectForKey("engine");
        int lastIndex = engine.getContent().lastIndexOf("_");
        int engineLevel = Integer.parseInt(engine.getContent().substring(lastIndex + 1));
        log.log(Level.INFO, "Set engine level: {0}", engineLevel);
        pl.getTrain().getEngine().initEngineLevel(engineLevel);

        NSNumber passengerWagonCount = (NSNumber) startParameters.objectForKey("passengerWagonCount");
        List<Wagon> wagons = pl.getTrain().getWagons();
        List<PassengerWagon> passengerWagons = pl.getTrain().getPassengerWagons();
        for (PassengerWagon pWagon : passengerWagons) {
            wagons.remove(pWagon);
        }

        for (int i = 0; i < passengerWagonCount.intValue(); i++) {
            PassengerWagon pw;
            try {
                pw = new PassengerWagon();
                pw.addPassengerListener(la);
                pl.getTrain().addPassengerWagon(pw);
            } catch (MaxWagonCountReached ex) {
                Logger.getLogger(Configurator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        NSNumber activityWagonCount = (NSNumber) startParameters.objectForKey("activityWagonCount");

        NSNumber coins = (NSNumber) startParameters.objectForKey("coins");
        NSNumber premiumCoins = (NSNumber) startParameters.objectForKey("coins"); // pc 
        pl.getWallet().initCoins(coins.intValue());
        pl.getWallet().initPC(premiumCoins.intValue());
    }

    private void initGameSettings(LevelAdmin la, Player pl) {
        log.info("Initializing GameSettings");
        NSDictionary startParameters = (NSDictionary) pListRoot.objectForKey(GAME_SETTINGS);
        NSDictionary coinGeneration = (NSDictionary) startParameters.objectForKey("coinGeneration");
        NSNumber duration = (NSNumber) coinGeneration.objectForKey("duration");
        pl.getTrain().setInterval(duration.intValue());
    }

    private void initLevelRewards(LevelAdmin la, Player pl) {
        NSArray levelRewards = (NSArray) pListRoot.objectForKey(LEVEL_REWARDS);
        NSObject[] rewards = (NSObject[]) levelRewards.getArray();
        ArrayList<Integer> engineUnlock = new ArrayList<>();
        ArrayList<Integer> destinationUnlock = new ArrayList<>();
        for (int i = 0; i < rewards.length; i++) {
            NSDictionary reward = (NSDictionary) rewards[i];
            if (reward.allKeys().length == 0) {
                continue;
            } else {
                try {
                    String destination = reward.objectForKey("destination").toString();
                    destinationUnlock.add(i + 1);
                } catch (NullPointerException npe) {
                }
                try {
                    String engine = reward.objectForKey("engine").toString();
                    engineUnlock.add(i + 1);
                } catch (NullPointerException npe) {
                }
            }
        }
        int[] destinationUnlockArray = new int[destinationUnlock.size()];
        for (int i = 0; i < destinationUnlock.size(); i++) {
            destinationUnlockArray[i] = destinationUnlock.get(i);
        }
        if (log.isLoggable(Level.FINE)) {
            StringBuilder sb = new StringBuilder();
            sb.append("destinationUnlockArray: ");
            for (int i = 0; i < destinationUnlockArray.length; i++) {
                sb.append(destinationUnlockArray[i]).append(",");
            }
            log.fine(sb.toString());
        }
        la.setAddDestination(destinationUnlockArray);

        int[] engineUnlockArray = new int[engineUnlock.size()];
        for (int i = 0; i < engineUnlock.size(); i++) {
            engineUnlockArray[i] = engineUnlock.get(i);
        }
        pl.setEngineUnlockLevels(engineUnlockArray);
    }

    private void initExperiencePoints(LevelAdmin la) {
        NSArray experiencePoints = (NSArray) pListRoot.objectForKey(EXPERIENCE_POINTS);
        NSObject[] items = experiencePoints.getArray();
        int[] experienceArray = new int[items.length - 1];
        //simulator starts with first positive experience value
        //omit first value in plist because it is 0
        for (int i = 0; i < items.length - 1; i++) {
            int level = ((NSNumber) items[i + 1]).intValue();
            experienceArray[i] = level;
        }
        if (log.isLoggable(Level.FINE)) {
            StringBuilder sb = new StringBuilder();
            sb.append("experiencePoints: ");
            for (int i = 0; i < experienceArray.length; i++) {
                sb.append(experienceArray[i]).append(",");
            }
            log.fine(sb.toString());
        }
        la.initExperiencePointArray(experienceArray);
    }

    private void initPrices(LevelAdmin la, Player pl) {
        NSDictionary levelRewards = (NSDictionary) pListRoot.objectForKey(WAGONS);
        NSDictionary prices = (NSDictionary) levelRewards.objectForKey("prices");

        // set activityWagon prices
        NSObject[] activityWagonPrices = ((NSArray) prices.objectForKey("activityWagon")).getArray();
        int[] activityWagonCost = new int[activityWagonPrices.length];
        for (int i = 0; i < activityWagonPrices.length; i++) {
            NSDictionary price = (NSDictionary) activityWagonPrices[i];
            activityWagonCost[i] = ((NSNumber) price.objectForKey("coins")).intValue();
        }
        pl.getStore().setCoinsActivityWagon(activityWagonCost);

        // set passengerWagon prices
        NSObject[] passengerWagonPrices = ((NSArray) prices.objectForKey("passengerWagon")).getArray();
        int maxPassengerWagons = 10;
        int max = Math.min(passengerWagonPrices.length, maxPassengerWagons);
        int[] passengerWagonCost = new int[max];
        for (int i = 0; i < max; i++) {
            NSDictionary price = (NSDictionary) passengerWagonPrices[i];
            passengerWagonCost[i] = ((NSNumber) price.objectForKey("coins")).intValue();
        }
        pl.getStore().setCoinsPassengerWagon(passengerWagonCost);

        // set bucket upgrade prices
        NSObject[] upgrades = ((NSArray) levelRewards.objectForKey("upgrades")).getArray();
        int[] bucketUpgrades = new int[upgrades.length];
        //allow max of 2 upgrades
        int bucketMax = 2;
        int[] bucketCosts = new int[bucketMax];
        for (int i = 0; i < upgrades.length; i++) {
            NSDictionary upgrade = (NSDictionary) upgrades[i];
            bucketUpgrades[i] = Integer.parseInt(upgrade.objectForKey("bucketSize").toString());
            NSDictionary price = (NSDictionary) upgrade.objectForKey("price");
            if (0 < i && i < 3) {
                bucketCosts[i - 1] = Integer.parseInt(price.objectForKey("coins").toString());
            }
        }
        // static bucket rule for all instances of all buckets
        Bucket.capacityUpgrade = bucketUpgrades;
        Store.costCoinsBucketUpgrade = bucketCosts;
        if (log.isLoggable(Level.FINE)) {
            StringBuilder sb = new StringBuilder();
            sb.append("bucketCosts: ");
            for (int j = 0; j < bucketCosts.length; j++) {
                sb.append(bucketCosts[j]).append(",");
            }
            log.fine(sb.toString());

            sb = new StringBuilder();
            sb.append("bucketUpgrades: ");
            for (int j = 0; j < bucketUpgrades.length; j++) {
                sb.append(bucketUpgrades[j]).append(",");
            }
            log.fine(sb.toString());
        }

        //set engine prices
        NSDictionary enginePrices = (NSDictionary) pListRoot.objectForKey(ENGINES);
        int[] engineCost = new int[enginePrices.count()];
        int[] engineAllowance = new int[enginePrices.count()];
        for (int ind = 1; ind <= enginePrices.count(); ind++) {
            NSDictionary priceDict = (NSDictionary) enginePrices.objectForKey("engine_" + ind);
            NSDictionary cost = (NSDictionary) priceDict.objectForKey("price");
            NSNumber engineCostInCoinst = (NSNumber) cost.objectForKey("coins");
            engineCost[ind - 1] = engineCostInCoinst.intValue();

            NSNumber allowance = (NSNumber) priceDict.objectForKey("wagonAllowance");
            engineAllowance[ind - 1] = allowance.intValue();
        }
        pl.getStore().setCoinsEngine(engineCost);

        if (log.isLoggable(Level.FINE)) {
            StringBuilder sb = new StringBuilder();
            sb.append("wagonAllowance: ");
            for (Integer val : engineAllowance) {
                sb.append(val).append(",");
            }
            log.fine(sb.toString());
        }
        pl.getTrain().getEngine().setWagonAllowance(engineAllowance);
    }

    private void initDestinations(LevelAdmin la, Player pl) {
        NSDictionary destinationInfo = (NSDictionary) pListRoot.objectForKey(DESTINATIONS);
        NSArray cityArray = (NSArray) destinationInfo.objectForKey("order");
        String[] cities = new String[cityArray.count()];
        for (int i = 0; i < cityArray.count(); i++) {
            cities[i] = cityArray.objectAtIndex(i).toString();
        }
        Schedule.AVAILABLE_CITIES = cities;

        NSDictionary cityInfos = (NSDictionary) destinationInfo.objectForKey("info");
        int[] travelTimes = new int[cityInfos.count()];
        int[] maxPassengerSkillValues = new int[cityInfos.count()];
        int[] expPerPassengers = new int[cityInfos.count()];
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            NSDictionary cityInfo = (NSDictionary) cityInfos.objectForKey(city);
            NSNumber travelTime = (NSNumber) cityInfo.objectForKey("travelTime");
            NSNumber maxPassengerSkillValue = (NSNumber) cityInfo.objectForKey("maxPassengerSkillValue");
            NSNumber expPerPassenger = (NSNumber) cityInfo.objectForKey("expPerPassenger");
            travelTimes[i] = travelTime.intValue();
            maxPassengerSkillValues[i] = maxPassengerSkillValue.intValue();
            expPerPassengers[i] = expPerPassenger.intValue();
        }
        Schedule.DISTANCE_TO_CITIES = travelTimes;
        //important to update static fields
        Schedule.updateDestinations();
        HashMap<String, Integer> maxSkillValue = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            maxSkillValue.put(cities[i], maxPassengerSkillValues[i]);
        }
        pl.getTrain().getPassengerFactory().setMaxPassengerSkillValue(maxSkillValue);
        Schedule.experiencePerPassenger = expPerPassengers;
    }

    private void logStartParamters(Player pl, LevelAdmin la) {
        if (log.isLoggable(Level.FINE))  {
            log.fine("================= Start Parameters =========================");
            log.log(Level.FINE, "Player level: {0}", pl.getLevel());
            log.log(Level.FINE, "Available Cities: {0}", pl.getTrain().getSchedule().getAvailableCities());
            StringBuilder destinations = new StringBuilder();
            int citiesInWorld = pl.getTrain().getSchedule().getCitiesInWorld();
            log.log(Level.FINE, "Cities in World: {0}", citiesInWorld);
            for (Destination d : pl.getTrain().getSchedule().getDestinations()) {
                log.fine(String.format("City: %12s, \tdistance: %10d, \texperience: %3d", d.getName(), d.getDistance(), d.getPassengerExperience()));
            }
        }


    }
}
