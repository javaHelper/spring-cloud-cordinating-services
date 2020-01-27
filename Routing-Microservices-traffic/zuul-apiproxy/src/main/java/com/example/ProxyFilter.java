package com.example;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;

public class ProxyFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		boolean isMobile = false;
		RequestContext context = getCurrentContext();
		String param = context.getRequest().getParameter("source");
		if(param != null && param.equals("mobile")) {
			isMobile = true;
		}
		return isMobile;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("calling a filter");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
}
