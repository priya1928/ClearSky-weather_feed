package io.egen.clearsky.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Weather {

	private String city;
	private String description;
	private int humidity;
	private int pressure;
	private int temperature;
	private Timestamp timestamp;
	@OneToOne
	private Wind wind;
	@Id
	private String id;
	
	
	public Weather(){
		this.id = UUID.randomUUID().toString();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getPressure() {
		return pressure;
	}
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
//	public Wind getWind() {
//		return wind;
//	}
//	public void setWind(Wind wind) {
//		this.wind = wind;
//	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getProperty(String prop){
		String result = "";
		if(prop.equals("temperature"))
			return String.valueOf(this.temperature);
		if(prop.equals("description"))
			return String.valueOf(this.description);
		if(prop.equals("humidity"))
			return String.valueOf(this.humidity);
		if(prop.equals("pressure"))
			return String.valueOf(this.pressure);
		if(prop.equals("timestamp"))
			return String.valueOf(this.timestamp);
		if(prop.equals("id"))
			return String.valueOf(this.id);
		if(prop.equals("wind"))
			return String.valueOf(this.getWind().toString());
		return result;
	}
}
