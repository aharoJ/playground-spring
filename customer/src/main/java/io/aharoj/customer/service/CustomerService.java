package io.aharoj.customer.service;

import java.util.List;

import io.aharoj.customer.dto.request.CustomerRequest;
import io.aharoj.customer.dto.response.CustomerResponse;

/**
 * CustomerService
 */
public interface CustomerService {

  /**
   * Create a customer
   *
   * @param request | the customer registration request DTO
   * @return the customer response DTO
   */
  CustomerResponse createCustomer(CustomerRequest request);

  /**
   * Get customer by id
   *
   * @param customerId will retrieve the customers profile
   * @return the customer response DTO
   *
   */
  CustomerResponse getCustomerById(Long customerId);

  /**
   * Get all customers by ID
   *
   */
  List<CustomerResponse> getAllCustomers();

  /**
   * update customer
   *
   * @param customerId, request | get the id to Update
   * @return the customer response DTO
   */
  CustomerResponse updateCustomer(Long customerId, CustomerRequest request);

  void deleteCustomer(Long customerId);
}
