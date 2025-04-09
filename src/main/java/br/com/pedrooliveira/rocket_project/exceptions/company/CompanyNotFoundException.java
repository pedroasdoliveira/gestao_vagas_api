package br.com.pedrooliveira.rocket_project.exceptions.company;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Company not Found!");
    }
}
