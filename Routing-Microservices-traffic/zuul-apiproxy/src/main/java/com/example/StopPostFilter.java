package com.example;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;

public class StopPostFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		Instant stop = Instant.now(); 
		RequestContext context = getCurrentContext(); 
		Instant start = (Instant) context.get("starttime");
		
		long secondsDifference = ChronoUnit.MILLIS.between(start, stop);
		System.out.println("Call Tooks = "+secondsDifference);
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
