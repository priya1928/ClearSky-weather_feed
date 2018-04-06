package io.egen.clearsky.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

//import org.hibernate.type.TrueFalseType;
import org.springframework.stereotype.Service;

import io.egen.clearsky.entity.Weather;
import io.egen.clearsky.exception.NotFoundException;
import io.egen.clearsky.repository.WeatherRepo;
import io.egen.clearsky.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{

	private WeatherRepo repository;
	
	public WeatherServiceImpl(WeatherRepo repository){
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> findAllCities() {
		return repository.findAllCities();
	}

	@Override
	@Transactional(readOnly = true)
	public Weather findForCity(String city) {
		Weather wthr = repository.findForCity(city);
		if(wthr == null){
			throw new NotFoundException("Weather report for city "+ city + " doesn't exist!");
		}
		return wthr;
	}

	@Override	
	@Transactional
	public Weather create(Weather weather) {
		return repository.create(weather);
	}

	@Override
	public Weather update(String city, Weather weather) {
		return repository.update(city, weather);
	}

	@Override
	public void delete(String city) {
		repository.delete(city);
	}

	@Override
	public String getLatestPropertyForCity(String city, String property) {
		Weather wthr = repository.findForCity(city);
		if(wthr == null){
			throw new NotFoundException("Weather report for city "+ city + " doesn't exist!");
		}
		return wthr.getProperty(property);
	}

	@Override
	public List<String> getHourlyAvg(String city) {
		List<String> wthr = repository.getHourlyAvg(city);
		if(wthr == null){
			throw new NotFoundException("Weather reports for city "+ city + " doesn't exist!");
		}
		return wthr;
	}

	@Override
	public List<String> getDailyAvg(String city) {
		List<String> wthr = repository.getDailyAvg(city);
		if(wthr == null){
			throw new NotFoundException("Weather reports for city "+ city + " doesn't exist!");
		}
		return wthr;
	}

}
