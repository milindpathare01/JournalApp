package com.ex_mongo_2.demo_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ex_mongo_2.demo_2.POJO.WeatherResponse;
import com.ex_mongo_2.demo_2.appChache.app_cache;


@Service
public class wheatherService {
	
	
@Value("${api_key}")
private String apikey ;
//	
//private static final String API = "http://api.weatherapi.com/v1/current.json?key=MY_KEY&q=CITY&aqi=no";
//	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private app_cache cache;

	public WeatherResponse getWheather(String city) {

	    String finalAPI = cache.appcache.get("api_key")
	            .replace("<CITY>", city)
	            .replace("<MY_KEY>", apikey);

	    ResponseEntity<WeatherResponse> response =
	            restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

	    return response.getBody();
	}
	

}
