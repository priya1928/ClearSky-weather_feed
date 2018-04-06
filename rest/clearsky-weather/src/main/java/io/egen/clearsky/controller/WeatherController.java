package io.egen.clearsky.controller;

import io.egen.clearsky.entity.Weather;
import io.egen.clearsky.service.WeatherService;
import io.egen.clearsky.constants.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;







import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//@Controller					//more specific thatn component but works similarly
//@ResponseBody				//watever controller returns,send that in response body


@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
@RestController				//instead of using Controller n ResponseBody
@RequestMapping(value = URI.USERS)
@Api(tags = "Clearsky")
public class WeatherController {
	
	private WeatherService service;
	
	public WeatherController(WeatherService service){
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Find All Cities", notes = "Returns a list of cities whose weather have been reported")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<String> findAllCities(){
		return service.findAllCities();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{city}")
	@ApiOperation(value = "Find Latest Weather Report", notes = "Returns a latest weather report for the city requested")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findForCity(@PathVariable("city") String city){
		return service.findForCity(city);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{city}/{property}")
	@ApiOperation(value = "Find Specific Property of Weather Report", notes = "Returns the requested property from latest weather report for the city requested")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String getLatestPropertyForCity(@PathVariable("city") String city,@PathVariable("property") String property){
		return service.getLatestPropertyForCity(city, property);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hourly/{city}")
	@ApiOperation(value = "Find Hourly Average Temperature", notes = "Returns a list of hourly avg temperatures for the city requested")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<String> getHourlyAvg(@PathVariable("city") String city){
		return service.getHourlyAvg(city);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/daily/{city}")
	@ApiOperation(value = "Find Daily Average Temperature", notes = "Returns a list of daily avg temperatures for the city requested")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<String> getDailyAvg(@PathVariable("city") String city){
		return service.getDailyAvg(city);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a Weather Report", notes = "Create an entry in weather table as well as in the wind table with unique UUIDs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather create(@RequestBody Weather weather){
		//System.out.print(weather.);
		return service.create(weather);
	}

	@RequestMapping(method = RequestMethod.PUT, value = URI.city)
	public Weather update(@PathVariable("city") String city, @RequestBody Weather weather){
		return service.update(city, weather);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = URI.city)
	public void delete(@PathVariable("city") String city){
		service.delete(city);
	}
}
