package ma.enset.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
    // Configuration dynamique
//    @Bean
//    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
//        return routeLocatorBuilder.routes()
//                .route("customer-service",r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
//                .route("product-service",r->r.path("/products/**").uri("lb://PRODUCT-SERVICE"))
//                .build();
//
//    }
    // configuration dynamique
    @Bean
    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(ReactiveDiscoveryClient discoveryClient,
                                                                                DiscoveryLocatorProperties properties){

        return new DiscoveryClientRouteDefinitionLocator(discoveryClient,properties);


    }

}
