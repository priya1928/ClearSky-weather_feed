package io.egen.clearsky.repository;

import io.egen.clearsky.entity.Weather;

import java.util.List;

public interface WeatherRepo {

	public List<String> findAllCities();
	
	public Weather findForCity(String city);
	
	public Weather create(Weather weather);
	
	public Weather update(String city, Weather weather);
	
	public void delete(String city);
	
	
	//@RequestMapping(method = RequestMethod.GET, value = URI.city.property)
	public Weather getLatestPropertyForCity(String city,String property);

	//@RequestMapping(method = RequestMethod.GET, value = URI.city)
	public List<String> getHourlyAvg(String city);
	
	//@RequestMapping(method = RequestMethod.GET, value = URI.city)
	public List<String> getDailyAvg(String city);
	
}
