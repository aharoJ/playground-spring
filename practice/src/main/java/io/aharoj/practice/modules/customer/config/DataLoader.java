package io.aharoj.practice.modules.customer.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.aharoj.practice.modules.customer.model.entity.Customer;
import io.aharoj.practice.modules.customer.repository.CustomerRepository;

@Component
public class DataLoader {

  @Bean
  public CommandLineRunner loadData(CustomerRepository customerRepository) {
    return args -> {
      customerRepository.save(new Customer("Bob", "Johnson", "bob.johnson@example.com", "password123"));
      customerRepository.save(new Customer("Charlie", "Brown", "charlie.brown@example.com", "password123"));
      customerRepository.save(new Customer("Diana", "Prince", "diana.prince@example.com", "password123"));
      customerRepository.save(new Customer("Eve", "White", "eve.white@example.com", "password123"));
      customerRepository.save(new Customer("Frank", "Black", "frank.black@example.com", "password123"));

      System.out.println("Sample customers loaded into the database!");
    };
  }
}
