package com.ex_mongo_2.demo_2.POJO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

@Component
public class WeatherResponse {

    private Current current;

    public Current getCurrent() {
		return current;
	}

	public void setCurrent(Current current) {
		this.current = current;
	}

    public static class Current {
        private String temp_f;

		public String getTemp_f() {
			return temp_f;
		}

		public void setTemp_f(String temp_f) {
			this.temp_f = temp_f;
		}
    }
}

