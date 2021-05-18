package utils;

import javafx.util.Pair;
import play.Logger;
import models.Reading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationAnalytics {
  public static Map<Integer, String> weatherIcons = new HashMap<>();
  static {
    weatherIcons.put(100, "sun icon");
    weatherIcons.put(200, "cloud sun icon");
    weatherIcons.put(300, "cloud icon");
    weatherIcons.put(400, "cloud sun rain icon");
    weatherIcons.put(500, "cloud showers heavy icon");
    weatherIcons.put(600, "cloud rain icon");
    weatherIcons.put(700, "snowflake icon");
    weatherIcons.put(800, "bolt icon");
  }

  public static Map<Integer, String> weatherLabels = new HashMap<>();
  static {
    weatherLabels.put(100, "Clear");
    weatherLabels.put(200, "Partial Clouds");
    weatherLabels.put(300, "Cloudy");
    weatherLabels.put(400, "Light Showers");
    weatherLabels.put(500, "Heavy Showers");
    weatherLabels.put(600, "Rain");
    weatherLabels.put(700, "Snow");
    weatherLabels.put(800, "Thunder");
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
