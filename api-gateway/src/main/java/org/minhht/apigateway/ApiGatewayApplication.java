package org.minhht.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	// @Bean
	// public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder) {
	// 	return routeLocatorBuilder.routes()
	// 			.route("login-service", r -> r.path("/api/login/**").uri("http://localhost:8082/"))
	// 			.route("product-service", r -> r.path("/api/product/**").uri("http://localhost:8081/"))
	// 			.build();

	// }
}
