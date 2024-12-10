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

  // NOT GOOD BECAUSE WE USE STATIC TO NOT GET AN INSTANCE
  // @Autowired
  // private MapperUtil mapperUtil;

  @Override
  public CustomerResponse createCustomer(CustomerRequest request) {
    if (customerRepository.existsByEmail(request.getEmail())) {
      throw new EmailIsAlreadyTaken();
    }

    // map request to entity
    Customer customer = MapperUtil.mapToCustomerRequest(request);

    // Save customer entity into the database
    Customer savedCustomer = customerRepository.save(customer);

    // Map saved customer entity to CustomerResponse DTO
    CustomerResponse response = MapperUtil.mapToCustomerResponse(savedCustomer);

    // return the response DTO
    return response;
  }

  @Override
  public CustomerResponse getCustomerById(Long customerId) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("no customer found"));

    return MapperUtil.mapToCustomerResponse(customer);
  }

  @Override
  public List<CustomerResponse> getAllCustomers() {
    List<Customer> customers = customerRepository.findAll();

    return customers.stream().map(MapperUtil::mapToCustomerResponse).collect(Collectors.toList());
  }

  /**
   * UPDATE WITH MAPPER
   */
  @Override
  public CustomerResponse updateCustomer(Long customerId, CustomerRequest request) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("No customer found"));

    // Map updates from the request to the customer entity
    MapperUtil.mapUpdatesToCustomer(request, customer);

    // Save the updated customer
    Customer updatedCustomer = customerRepository.save(customer);

    // Map to CustomerResponse and return
    return MapperUtil.mapToCustomerResponse(updatedCustomer);
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
        .orElseThrow(() -> new RuntimeException("No customer found :("));

    customerRepository.delete(customer);
  }
}
