package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    List<Station> stations = Station.findAll();

    render("dashboard.html", stations);
  }

  public static void deleteStation(Long id) {
    List<Station> stations = Station.findAll();
    Station station = Station.findById(id);
    Logger.info ("Removing station: " + station.name);
    stations.remove(station);
    station.delete();

    render("dashboard.html", stations);
  }

  public static void addStation(String name, double latitude, double longitude) {
    Logger.info ("Adding station: " + name);
    List<Station> stations = Station.findAll();
    Station station = new Station(name, latitude, longitude);
    stations.add(station);
    station.save();

    render("dashboard.html", stations);
  }
}
