package ma.enset.inventoryservicems;

import ma.enset.inventoryservicems.entities.Product;
import ma.enset.inventoryservicems.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceMsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        return args -> {
            Product product = Product.builder()
                    .name("DELL")
                    .price(1500)
                    .quantity(15)
                    .build();
            Product product1 = Product.builder()
                    .name("HP")
                    .price(6300)
                    .quantity(16)
                    .build();
            Product product2 = Product.builder()
                    .name("LENOVO")
                    .price(6500)
                    .quantity(19)
                    .build();
            productRepository.save(product);
            productRepository.save(product1);
            productRepository.save(product2);
        };

    }

}
