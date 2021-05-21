package utils;

import play.Logger;
import models.Reading;
import java.util.Date;
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
    if(readings.size() > 0) {
      Date latestDate = new Date();
      latestDate.setTime(0);
      for (Reading reading : readings) {
        if (reading.date.compareTo(latestDate) > 0) {
          latestDate = reading.date;
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

  public static String getWeatherTrendIcon(List<Reading> readings, String weatherParam) {
    String trend = "";
    if(readings.size() >= 3) {
      double reading1 = 0;
      double reading2 = 0;
      double reading3 = 0;

      switch (weatherParam) {
        case "temperature":
          reading1 = readings.get(0).temperature;
          reading2 = readings.get(1).temperature;
          reading3 = readings.get(2).temperature;
          break;
        case "windSpeed":
          reading1 = readings.get(0).windSpeed;
          reading2 = readings.get(1).windSpeed;
          reading3 = readings.get(2).windSpeed;
          break;
        case "pressure":
          reading1 = readings.get(0).pressure;
          reading2 = readings.get(1).pressure;
          reading3 = readings.get(2).pressure;
          break;
        default:
          System.out.println("Invalid weather params");
      }

      if ((reading2 > reading1) && (reading3 > reading2)) {
        trend = "arrow up icon";
      } else if ((reading2 < reading1) && (reading3 < reading2)) {
        trend = "arrow down icon";
      } else {
        trend = "";
      }
    }
      return trend;
  }
}
