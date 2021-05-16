package utils;

import models.Reading;

import java.util.List;

public class StationAnalytics {

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

}
