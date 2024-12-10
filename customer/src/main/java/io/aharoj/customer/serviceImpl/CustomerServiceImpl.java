package io.aharoj.customer.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.aharoj.customer.dto.request.CustomerRequest;
import io.aharoj.customer.dto.response.CustomerResponse;
import io.aharoj.customer.exception.EmailIsAlreadyTaken;
import io.aharoj.customer.mapper.MapperUtil;
import io.aharoj.customer.model.entity.Customer;
import io.aharoj.customer.repository.CustomerRepository;
import io.aharoj.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  /**
   * NOT GOOD BECAUSE WE USE STATIC TO NOT GET AN INSTANCE
   * 
   * Autowired private MapperUtil mapperUtil;
   */

  @Override
  public CustomerResponse createCustomer(CustomerRequest request) {
    // check if email is locked in
    if (customerRepository.existsByEmail(request.getEmail())) {
      throw new EmailIsAlreadyTaken();
    }

    // request form
    Customer customer = MapperUtil.mapCustomerRequest(request);

    // store record to db
    Customer savedCustomer = customerRepository.save(customer);

    // return response to client
    CustomerResponse response = MapperUtil.mapCustomerResponse(savedCustomer);

    return response;
  }

  @Override
  public CustomerResponse getCustomerById(Long customerId) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("customer not found"));

    return MapperUtil.mapCustomerResponse(customer);
  }

  @Override
  public List<CustomerResponse> getAllCustomers() {
    List<Customer> customers = customerRepository.findAll();

    return customers.stream().map(MapperUtil::mapCustomerResponse).collect(Collectors.toList());
  }

  @Override
  public CustomerResponse updateCustomer(Long customerId, CustomerRequest request) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("customer not found"));
    // request form + update
    MapperUtil.mapCustomerUpdate(request, customer);

    // save record into db
    Customer updatedCustomer = customerRepository.save(customer);

    // return response to client
    return MapperUtil.mapCustomerResponse(updatedCustomer);
  }

  /**
   * ORIGINAL No UPDATE MAPPER here
   */
  // @Override
  // public CustomerResponse updateCustomer(Long customerId, CustomerRequest
  // request) {
  // // Fetch the customer by ID, or throw an exception if not found
  // Customer customer = customerRepository.findById(customerId)
  // .orElseThrow(() -> new RuntimeException("No customer found"));

  // // Update the customer entity fields with the request data
  // customer.setFirstName(request.getFirstName());
  // customer.setLastName(request.getLastName());
  // customer.setEmail(request.getEmail());

  // // Save the updated customer back to the database
  // Customer updatedCustomer = customerRepository.save(customer);

  // // Convert the updated customer to a CustomerResponse and return it
  // return MapperUtil.mapToCustomerResponse(updatedCustomer);
  // }

  @Override
  public void deleteCustomer(Long customerId) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("customer not found"));

    // MapperUtil.mapToCustomerResponse(customer);
    customerRepository.delete(customer);
  }
}
