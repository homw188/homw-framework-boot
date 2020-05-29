package com.homw.ms.gateway.filter;

import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.homw.ms.gateway.config.MsGatewayProperties;

import reactor.core.publisher.Mono;

/**
 * @description gateway authorization filter
 * @author Hom
 * @version 1.0
 * @since 2020-05-29
 */
@Component
public class GatewayAuthFilter implements GlobalFilter, Ordered {

	private static final Logger logger = LoggerFactory.getLogger(GatewayAuthFilter.class);

	@Resource
	private MsGatewayProperties msGatewayProperties;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = exchange.getRequest().getPath().value();
		boolean match = msGatewayProperties.getAuth().getExcludePaths().stream()
				.anyMatch(p -> Pattern.compile(p.replace("*", "\\w")).matcher(path).find());
		if (!match) {
			String appKey = request.getHeaders().getFirst("app_key");
			if (StringUtils.isEmpty(appKey)
					|| !StringUtils.equalsIgnoreCase(appKey, msGatewayProperties.getAuth().getAppKey())) {
				logger.info("illegal access, app_key=" + appKey);
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
				return response.setComplete();
			}
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
