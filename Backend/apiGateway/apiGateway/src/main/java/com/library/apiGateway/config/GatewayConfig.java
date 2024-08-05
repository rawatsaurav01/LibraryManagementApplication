package com.library.apiGateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.library.apiGateway.filter.JWTValidationFilter;

@Configuration
public class GatewayConfig {

	
	@Autowired
	private JWTValidationFilter jwtValidationFilter;

	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r ->
				r.path("/books")
				.uri("lb://BOOK-SERVICE"))
				
				.route(r ->
				r.path("/books/**")
				.filters(f->f.filters(jwtValidationFilter))
				.uri("lb://BOOK-SERVICE"))
				
				.route(r->r.path("/login")
						.uri("lb://AUTHORISATION-SERVICE"))
				
				.route(r->r.path("/user/**")
						.uri("lb://USER-SERVICE"))
				
				
				
				.route(r ->
				r.path("/wishlist/**")
				.filters(f->f.filters(jwtValidationFilter))
				.uri("lb://WISHLIST-SERVICE"))
				
				.build();
				
				
		
		

	}
}
