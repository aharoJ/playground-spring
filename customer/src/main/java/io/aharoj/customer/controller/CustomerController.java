package io.aharoj.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.aharoj.customer.dto.request.CustomerRequest;
import io.aharoj.customer.dto.response.CustomerResponse;
import io.aharoj.customer.service.CustomerService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping
  public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
    CustomerResponse response = customerService.createCustomer(request);

    return ResponseEntity.status(201).body(response);
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId) {

    CustomerResponse response = customerService.getCustomerById(customerId);

    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    List<CustomerResponse> customers = customerService.getAllCustomers();

    return ResponseEntity.ok(customers);
  }

  @PutMapping("/{customerId}")
  public ResponseEntity<CustomerResponse> updateCustomer(
      @PathVariable Long customerId, @Valid @RequestBody CustomerRequest request) {
    CustomerResponse response = customerService.updateCustomer(customerId, request);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
    customerService.deleteCustomer(customerId);

    return ResponseEntity.ok("Customer profile deleted successfully.");
  }

}
