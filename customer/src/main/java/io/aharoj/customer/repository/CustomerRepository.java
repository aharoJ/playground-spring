package io.aharoj.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.aharoj.customer.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  // here we can have custum made Q's

  /**
   * Check if a customer exists by email
   */
  Boolean existsByEmail(String email);
}
