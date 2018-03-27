package io.egen.clearsky.repository.impl;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.clearsky.entity.Weather;
import io.egen.clearsky.entity.Wind;
import io.egen.clearsky.repository.WeatherRepo;

@Repository
public class WeatherRepoImpl implements WeatherRepo {

	@PersistenceContext
	private EntityManager em;

	//	POST/Create a row in database example-db, table weather, stating weather condition as reported till by mocker
	@Override
	public Weather create(Weather weather) {
		Wind wind = new Wind();	//weather entry needs a corresponding wind entry wid which it has oneToOne relation
		wind.setDegree(weather.getWind().getDegree());
		wind.setSpeed(weather.getWind().getSpeed());	
		em.persist(wind);
		weather.setWind(wind);	//to store wind id in weather object	
		em.persist(weather);
		return weather;
	}
	
	//	GET/Return List of all cities whose weather conditions have been reported till now
	@Override
	public List<String> findAllCities() {
		TypedQuery<String> query = em.createQuery("SELECT DISTINCT(u.city) FROM Weather u",String.class);
		return query.getResultList();
	}

	//	GET/Return LATEST WEATHER report for a particular city
	@Override
	public Weather findForCity(String city) {
		TypedQuery<Weather> query = em.createQuery("SELECT u FROM Weather u WHERE u.city = :pCity ORDER BY u.timestamp DESC",Weather.class);
		query.setParameter("pCity", city);
		List<Weather> wthr = query.getResultList();
		if(!wthr.isEmpty()){
			return wthr.get(0);
		}
		return null;
	}

	//	GET/Return particular prop of LATEST WEATHER report for a particular city
	@Override
	public Weather getLatestPropertyForCity(String city, String property) {
		TypedQuery<Weather> query = em.createQuery("SELECT u FROM Weather u WHERE u.city = :pCity ORDER BY u.timestamp DESC",Weather.class);
		query.setParameter("pCity",city);
		List<Weather> wthr = query.getResultList();
		if(!wthr.isEmpty()){
			//if(property=="temperature")
				return wthr.get(0);
		}
		return null;
	}

	@Override
	public List<String> getHourlyAvg(String city) {
		TypedQuery<Double> query = em.createQuery("SELECT AVG(u.temperature) FROM Weather u WHERE u.city = :pCity GROUP BY HOUR(u.timestamp),DATE(u.timestamp)",Double.class);
		query.setParameter("pCity",city);
		List<Double> wthr = query.getResultList();
		if(!wthr.isEmpty()){
			//if(property=="temperature")
			List<String> result = new ArrayList<String>();
			for(Double d: wthr)
				result.add(String.valueOf(d));
				return result;
		}
		return null;
	}

	//	GET/Return DAILY AVG TEMPERATURE as report for a particular city
	@Override
	public List<String> getDailyAvg(String city) {
		TypedQuery<Double> query = em.createQuery("SELECT AVG(u.temperature) FROM Weather u WHERE u.city = :pCity GROUP BY DATE(u.timestamp)",Double.class);
		query.setParameter("pCity",city);
		List<Double> wthr = query.getResultList();
		if(!wthr.isEmpty()){
			//if(property=="temperature")
			List<String> result = new ArrayList<String>();
			for(Double d: wthr)
				result.add(String.valueOf(d));
				return result;
		}
		return null;
	}

	@Override
	public Weather update(String city, Weather weather) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String city) {	
		// TODO Auto-generated method stub
	}
}
