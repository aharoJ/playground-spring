package io.aharoj.customer.mapper;

import org.springframework.stereotype.Component;

import io.aharoj.customer.dto.request.CustomerRequest;
import io.aharoj.customer.dto.response.CustomerResponse;
import io.aharoj.customer.model.entity.Customer;

/**
 * @Component:
 *             Tells Spring to consider the annotated class as a bean that can
 *             be injected into other classes.
 */
@Component
public class MapperUtil {
  // Customer Mapper
  public static CustomerResponse mapCustomerResponse(Customer customer) {
    CustomerResponse response = new CustomerResponse();
    response.setFullName(customer.getFirstName() + " " + customer.getLastName());
    response.setEmail(customer.getEmail());

    return response;
  }

  public static Customer mapCustomerRequest(CustomerRequest request) {
    Customer customer = new Customer();
    customer.setFirstName(request.getFirstName());
    customer.setLastName(request.getLastName());
    customer.setEmail(request.getEmail());

    return customer;
  }

  // MUST BE VOID
  public static void mapCustomerUpdate(CustomerRequest request, Customer customer) {
    customer.setFirstName(request.getFirstName());
    customer.setLastName(request.getLastName());
    customer.setEmail(request.getEmail());
  }
}
