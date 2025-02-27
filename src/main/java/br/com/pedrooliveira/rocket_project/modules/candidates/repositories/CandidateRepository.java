package br.com.pedrooliveira.rocket_project.modules.candidates.repositories;

import br.com.pedrooliveira.rocket_project.modules.candidates.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    public Candidate findByEmail(String email);
}
