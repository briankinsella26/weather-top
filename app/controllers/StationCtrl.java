package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class StationCtrl extends Controller
{
  public static void index(Long id) {
    Logger.info ("Station id = " + id);
    Station station = Station.findById(id);
    Reading latestReading = StationAnalytics.getLatestReading(station.readings);

    render("station.html", station, latestReading);
  }

  public static void deleteReading(Long id, Long readingId) {
    Logger.info ("Removing reading id: " + readingId);

    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingId);
    station.readings.remove(reading);
    station.save();
    reading.delete();

    render("station.html", station);
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