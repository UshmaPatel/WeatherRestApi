package com.example.weather.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherEntry implements Serializable
{
	private Instant timestamp;

	private double temperature;

	private Integer weatherId;

	private String weatherIcon;
	
	private String main;
	
	private String description;

	private double longitude;
	
	private double latitude;
	
	private String country;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getMain() {
		return main;
	}

	public String getDescription() {
		return description;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("timestamp")
	public Instant getTimestamp() {
		return this.timestamp;
	}

	@JsonSetter("dt")
	public void setTimestamp(long unixTime) {
		this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
	}

	/**
	 * Return the temperature in Kelvin (K).
	 */
	public double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		setTemperature(Double.parseDouble(main.get("temp").toString()));
	}

	public Integer getWeatherId() {
		return this.weatherId;
	}

	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}

	public String getWeatherIcon() {
		return this.weatherIcon;
	}

	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		setWeatherId((Integer) weather.get("id"));
		setWeatherIcon((String) weather.get("icon"));
		setMain((String) weather.get("main"));
		setDescription((String) weather.get("description"));
	}
	
	
	@JsonProperty("coord")
	public void setCoord(Map<String, Object> coord) {
		setLongitude(Double.parseDouble(coord.get("lon").toString()));
		setLatitude(Double.parseDouble(coord.get("lat").toString()));

	}
	
	@JsonProperty("sys")
	public void setSys(Map<String, Object> sys)
	{
		setCountry((String) sys.get("country"));

	}

	
}
