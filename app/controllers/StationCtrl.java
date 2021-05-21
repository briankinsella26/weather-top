package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.Conversions;
import utils.StationAnalytics;

public class StationCtrl extends Controller {

  public static void index(Long id) {
    Logger.info ("Station id = " + id);
    Station station = Station.findById(id);
    if(station.readings.size() > 0 ) {
      station.latestReading = StationAnalytics.getLatestReading(station.readings);
      Conversions.performConversions(station.latestReading);
      Conversions.setMinMaxValues(station);
      station.temperatureTrend = StationAnalytics.getWeatherTrendIcon(station.readings, "temperature");
      station.windSpeedTrend = StationAnalytics.getWeatherTrendIcon(station.readings, "windSpeed");
      station.pressureTrend = StationAnalytics.getWeatherTrendIcon(station.readings, "pressure");
    }

    render("station.html", station);
  }

  public static void deleteReading(Long id, Long readingId) {
    Logger.info ("Removing reading id: " + readingId);
    Station station = Station.findById(id);
    Reading latestReading = StationAnalytics.getLatestReading(station.readings);
    Reading reading = Reading.findById(readingId);
    station.readings.remove(reading);
    station.save();
    reading.delete();

    redirect("/stations/" + id);
  }

  public static void addReading (Long id, int code, double temperature, double windSpeed, int windDirection, int pressure) {
    Logger.info ("Adding new reading");

    Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();

    redirect("/stations/" + id);
  }
}