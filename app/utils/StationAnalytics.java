package utils;

import javafx.util.Pair;
import play.Logger;
import models.Reading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationAnalytics {
  public static Map<Integer, Pair<String, String>> weatherIcons = new HashMap<>();
  static {
    weatherIcons.put(100, new Pair<>("sun icon", "Clear"));
    weatherIcons.put(200, new Pair<>("cloud sun icon", "Partial Clouds"));
    weatherIcons.put(300, new Pair<>("cloud icon", "Cloudy"));
    weatherIcons.put(400, new Pair<>("cloud sun rain icon", "Light Showers"));
    weatherIcons.put(500, new Pair<>("cloud showers heavy icon", "Heavy Showers"));
    weatherIcons.put(600, new Pair<>("cloud rain icon", "Rain"));
    weatherIcons.put(700, new Pair<>("snowflake icon", "Snow"));
    weatherIcons.put(800, new Pair<>("bolt icon", "Thunder"));
  }

  public static Reading getLatestReading(List<Reading> readings) {
    Reading latestReading = null;
    long latestId = 0;
    if(readings.size() > 0) {
      for (Reading reading : readings) {
        if (reading.id > latestId) {
          latestId = reading.id;
          latestReading = reading;
        }
      }
    }
    return latestReading;
  }

  public static Reading getMaxReading(List<Reading> readings, String readingType) {
    Reading maxReading = readings.get(0);
    for (Reading reading : readings) {
      if (readingType.equals("beaufort")) {
        if ((reading.beaufort > maxReading.beaufort)) {
          maxReading = reading;
        }
      } else if (readingType.equals("temperature")) {
        if ((reading.temperature > maxReading.temperature)) {
          maxReading = reading;
        }
      } else if (readingType.equals("pressure")) {
        if ((reading.pressure > maxReading.pressure)) {
          maxReading = reading;
        }
      } else {
        Logger.info("invalid parameters passed");
      }
    }
    return maxReading;
  }

  public static Reading getMinReading(List<Reading> readings, String readingType) {
    Reading minReading = readings.get(0);
      for (Reading reading : readings) {
        if(readingType.equals("beaufort")) {
          if ((reading.beaufort < minReading.beaufort)) {
            minReading = reading;
          }
        } else if (readingType.equals("temperature")) {
          if ((reading.temperature < minReading.temperature)) {
            minReading = reading;
          }
        } else if (readingType.equals("pressure")) {
          if ((reading.pressure < minReading.pressure)) {
            minReading = reading;
          }
        } else {
          Logger.info("invalid parameters passed");
        }
      }
    return minReading;
  }
}
