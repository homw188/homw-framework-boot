package com.homw.ms.gateway.bean;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * @description provide swagger resources
 * @author Hom
 * @version 1.0
 * @since 2020-05-29
 */
@Primary
@Component
public class SwaggerProvider implements SwaggerResourcesProvider {

	public static final String API_URI = "/v2/api-docs";

	@Resource
	private RouteLocator routeLocator;
	@Resource
	private GatewayProperties gatewayProperties;

	@Override
	public List<SwaggerResource> get() {
		List<String> routes = Lists.newArrayList();
		routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

		List<SwaggerResource> resources = Lists.newArrayList();
		gatewayProperties.getRoutes().stream().filter(routeDef -> routes.contains(routeDef.getId()))
				.forEach(routeDef -> routeDef.getPredicates().stream()
						.filter(predicateDef -> ("Path").equalsIgnoreCase(predicateDef.getName()))
						.forEach(predicateDef -> resources.add(buildResource(routeDef.getId(), predicateDef.getArgs()
								.get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI)))));
		return resources;
	}

	private SwaggerResource buildResource(String name, String location) {
		SwaggerResource resource = new SwaggerResource();
		resource.setName(name);
		resource.setLocation(location);
		resource.setSwaggerVersion("2.0");
		return resource;
	}

}
