package br.com.pedrooliveira.rocket_project.modules.candidates.useCases;

import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserFoundException;
import br.com.pedrooliveira.rocket_project.modules.candidates.entities.Candidate;
import br.com.pedrooliveira.rocket_project.modules.candidates.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Candidate execute(Candidate candidate) {
        this.repository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException("Usuário já existe na base de dados!");
                });

        final String password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return this.repository.save(candidate);
    }
}
