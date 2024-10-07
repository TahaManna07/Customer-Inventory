package ma.enset.billingservice;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.feign.CustomerRestClient;
import ma.enset.billingservice.feign.ProductItemRestClient;
import ma.enset.billingservice.models.Customer;
import ma.enset.billingservice.models.Product;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient){
        return args -> {


            Customer customer = customerRestClient.getCustomerById(1L);
            Bill bill1 = billRepository.save(Bill.builder()
                    .billingDate(new Date())
                    .productItems(null)
                    .customerId(customer.getId())
                    .customer(null)
                    .build());
            PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
            productPagedModel.forEach(p->{
                ProductItem productItem = new ProductItem();
                productItem.setProductId(p.getId());

                productItem.setPrice(p.getPrice());
                productItem.setQuantity(new Random().nextInt(150));
                productItem.setBill(bill1);
                productItemRepository.save(productItem);
            });


        };
    }
}
