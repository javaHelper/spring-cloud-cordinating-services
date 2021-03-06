package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.model.TollRate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class DashboardController {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/dashboard")
	@HystrixCommand(fallbackMethod="getTollRateBackup") 
	public String GetTollRate(@RequestParam int stationId, Model m) {
		
		TollRate tr = restTemplate.getForObject("http://toll-service/tollrate/" + stationId, TollRate.class);
		System.out.println("stationId: " + stationId);
		m.addAttribute("rate", tr.getCurrentRate());
		return "dashboard";
	}
	
	public String getTollRateBackup(@RequestParam int stationId, Model m) { 
		System.out.println("Fallback operation called");
		m.addAttribute("rate", "1.00");
		return "dashboard";
	}
}
