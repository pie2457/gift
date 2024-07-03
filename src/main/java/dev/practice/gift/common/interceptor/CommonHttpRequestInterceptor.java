package dev.practice.gift.common.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Component
public class CommonHttpRequestInterceptor implements AsyncHandlerInterceptor {

	public static final String HEADER_REQUEST_UUID_KEY = "x-request-id";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String requestEventId = request.getHeader(HEADER_REQUEST_UUID_KEY);
		if (StringUtils.isEmpty(requestEventId)) {
			requestEventId = UUID.randomUUID().toString();
		}
		MDC.put(HEADER_REQUEST_UUID_KEY, requestEventId);
		return true;
	}
}

