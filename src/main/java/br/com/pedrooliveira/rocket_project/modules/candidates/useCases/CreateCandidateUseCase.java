package br.com.pedrooliveira.rocket_project.modules.candidates.useCases;

import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserFoundException;
import br.com.pedrooliveira.rocket_project.modules.candidates.entities.Candidate;
import br.com.pedrooliveira.rocket_project.modules.candidates.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    public Candidate execute(Candidate candidate) {
        this.repository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException("Usuário já existe na base de dados!");
                });

        return this.repository.save(candidate);
    }
}
