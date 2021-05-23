package utils;

import java.util.HashMap;
import java.util.Map;

public class RandomFacts {

  public static Map<Integer, String> randomFacts = new HashMap<>();

  static {
    randomFacts.put(1, "You can tell the temperature by counting a cricket’s chirps!");
    randomFacts.put(2, "Sandstorms can swallow up entire cities.");
    randomFacts.put(3, "Dirt mixed with wind can make dust storms called black blizzards.");
    randomFacts.put(4, "Mild autumn weather often means bigger spiders in our homes.");
    randomFacts.put(5, "The coldest temperature ever officially recorded was -89.2°C.");
    randomFacts.put(6, "A heatwave can make train tracks bend!");
    randomFacts.put(7, "About 2,000 thunderstorms rain down on Earth every minute.");
    randomFacts.put(8, "A 2003 heatwave turned grapes to raisins before they were picked from the vine!");
    randomFacts.put(9, "Lightning often follows a volcanic eruption.");
    randomFacts.put(10, "Raindrops can be the size of a housefly and fall at more than 30kmph.");
    randomFacts.put(11, "Cape Farewell in Greenland is the windiest place on the planet.");
    randomFacts.put(12, "Hurricanes can push more than 6m of water ashore.");
    randomFacts.put(13, "In July 2001 the rainfall in Kerala, India, was blood red!");
    randomFacts.put(14, "Blizzards can make snowflakes feel like pellets hitting your face.");
    randomFacts.put(15, "Worms wriggle up from underground when a flood is coming.");
    randomFacts.put(16, "In Antarctica, snow can fall so hard you can’t see your hand in front of your face.");
    randomFacts.put(17, "A whiteout or heavy snowfall that makes it difficult to see, can make you feel sick.");
    randomFacts.put(18, "Wildfires sometimes create tornadoes made of fire called fire whirls.");
    randomFacts.put(19, "In 1972, a blizzard dumped 8m of snowfall on Iran, burying 200 villages.");
    randomFacts.put(20, "Some frogs get noisier just before it rains.");
  }

  public static String getRandomFact() {
    int randomNumber= (int)(Math.random() * (20-1) + 1);
    return randomFacts.get(randomNumber);
  }
}
