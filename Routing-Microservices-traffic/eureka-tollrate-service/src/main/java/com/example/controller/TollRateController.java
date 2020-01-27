package com.example.controller;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.TollRate;

@RestController
public class TollRateController {

	@GetMapping("/tollrate/{stationId}")
	public TollRate getTollRate(@PathVariable int stationId) {

		TollRate tr;

		System.out.println("Station requested: " + stationId);

		switch (stationId) {
		case 1:
			tr = new TollRate(stationId, new BigDecimal("0.55"), Instant.now().toString());
			break;
		case 2:
			tr = new TollRate(stationId, new BigDecimal("1.05"), Instant.now().toString());
			break;
		case 3:
			tr = new TollRate(stationId, new BigDecimal("0.60"), Instant.now().toString());
			break;
		default:
			tr = new TollRate(stationId, new BigDecimal("1.00"), Instant.now().toString());
			break;
		}

		return tr;
	}
}