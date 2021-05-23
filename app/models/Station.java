package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import play.db.jpa.Model;

@Entity
public class Station extends Model {
  public String name;
  public double minTemperature;
  public double maxTemperature;
  public double minBeaufort;
  public double maxBeaufort;
  public double minPressure;
  public double maxPressure;
  public Reading latestReading;
  public String temperatureTrend;
  public String windSpeedTrend;
  public String pressureTrend;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();
  public double latitude;
  public double longitude;

  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}