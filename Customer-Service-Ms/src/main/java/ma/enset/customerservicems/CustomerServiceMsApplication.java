package ma.enset.customerservicems;

import ma.enset.customerservicems.entities.Customer;
import ma.enset.customerservicems.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceMsApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {

            Customer customer = Customer.builder()
                    .email("Taha@enset.ma")
                    .name("Taha")
                    .build();
            Customer customer1 = Customer.builder()
                    .email("Souhail@enset.ma")
                    .name("souhail")
                    .build();
            Customer customer2 = Customer.builder()
                    .email("Manna@enset.ma")
                    .name("Manna")
                    .build();
            customerRepository.save(customer);
            customerRepository.save(customer1);
            customerRepository.save(customer2);

            customerRepository.findAll().forEach(c->{
                System.out.println(c.toString());
            });

        };
    }

}
