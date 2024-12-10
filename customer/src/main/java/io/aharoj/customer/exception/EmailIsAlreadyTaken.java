package io.aharoj.customer.exception;

public class EmailIsAlreadyTaken extends RuntimeException {
  public EmailIsAlreadyTaken() {
    super("Email is already taken");
  }

  public EmailIsAlreadyTaken(String message) {
    super(message);
  }

  public EmailIsAlreadyTaken(String message, Throwable cause) {
    super(message);
  }

}
