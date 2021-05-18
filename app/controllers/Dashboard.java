package controllers;

import java.util.List;
import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.Conversions;
import utils.StationAnalytics;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;

    for(Station station: stations) {
      station.latestReading = StationAnalytics.getLatestReading(station.readings);
      Conversions.performConversions(station.latestReading);
      Conversions.setMinMaxValues(station);
    }

    render("dashboard.html", member, stations);
  }

  public static void deleteStation(Long id, Long stationId) {
    Logger.info ("Removing station");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = Station.findAll();
    Station station = Station.findById(stationId);
    member.stations.remove(station);
    member.save();
    station.delete();

    redirect("/dashboard");
  }

  public static void addStation(String name, double latitude, double longitude) {
    Logger.info ("Adding station: " + name);
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = Station.findAll();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    station.save();
    member.save();

    redirect("/dashboard");
  }
}
