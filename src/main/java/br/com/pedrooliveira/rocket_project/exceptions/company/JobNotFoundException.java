package br.com.pedrooliveira.rocket_project.exceptions.company;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Job not found!");
    }
}
