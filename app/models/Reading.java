package models;

import play.db.jpa.Model;
import javax.persistence.Entity;


@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public int pressure;
  public double tempFahrenheit;
  public String beaufortLabel;
  public int beaufort;
  public String windCompass;
  public double windChill;
  public String weatherIcon;

  public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
  }
}
