package models;

import play.db.jpa.Model;
import utils.Conversions;

import javax.persistence.Entity;

import static utils.Conversions.convertCelsiusToFahrenheit;
import static utils.Conversions.convertKmHrToBeaufort;

@Entity
public class Reading extends Model
{
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

  
  public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure)
  {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
    this.tempFahrenheit = convertCelsiusToFahrenheit(temperature);
    convertKmHrToBeaufort(windSpeed);
    this.beaufortLabel = Conversions.label;
    this.beaufort = Conversions.beaufort;
    this.windCompass = Conversions.getWindCompass(windDirection);
    this.windChill = Conversions.getWindChill(temperature, windSpeed);
  }
}
