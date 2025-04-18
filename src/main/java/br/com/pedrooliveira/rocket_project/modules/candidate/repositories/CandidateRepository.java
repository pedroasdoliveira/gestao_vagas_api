package br.com.pedrooliveira.rocket_project.modules.candidate.repositories;

import br.com.pedrooliveira.rocket_project.modules.candidate.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    public Optional<Candidate> findByUsernameOrEmail(String username, String email);

    public Optional<Candidate> findByUsername(String username);
}
