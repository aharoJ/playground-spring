package io.aharoj.customer.dto.request;

import jakarta.validation.constraints.*;

/**
 * we validate or not here
 * ie. @notnull
 */
public class CustomerRequest {
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private String email;

  public CustomerRequest() {
  }

  public CustomerRequest(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
