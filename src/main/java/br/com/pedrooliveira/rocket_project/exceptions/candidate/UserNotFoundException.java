package br.com.pedrooliveira.rocket_project.exceptions.candidate;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
      super("User not found!");
    }
}
