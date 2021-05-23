package controllers;

import java.util.Comparator;
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
    stations.sort(Comparator.comparing(station -> station.name, String.CASE_INSENSITIVE_ORDER));

    for(Station station: stations) {
      if(station.readings.size() > 0) {
        station.latestReading = StationAnalytics.getLatestReading(station.readings);
        Conversions.performConversions(station.latestReading);
        Conversions.setMinMaxValues(station);
        station.readings.sort(Comparator.comparing(reading -> reading.date));
        station.temperatureTrend = StationAnalytics.getWeatherTrendIcon(station.readings, "temperature");
        station.windSpeedTrend = StationAnalytics.getWeatherTrendIcon(station.readings, "windSpeed");
        station.pressureTrend = StationAnalytics.getWeatherTrendIcon(station.readings, "pressure");
        }
    }
    render("dashboard.html", member, stations);
  }

  public static void deleteStation(Long id, Long stationId) {
    Logger.info ("Removing station");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(stationId);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }

  public static void addStation(String name, double latitude, double longitude) {
    Logger.info ("Adding station: " + name);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    station.save();
    member.save();
    redirect("/dashboard");
  }
}
