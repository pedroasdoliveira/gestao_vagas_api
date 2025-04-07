package br.com.pedrooliveira.rocket_project.modules.candidate.repositories;

import br.com.pedrooliveira.rocket_project.modules.candidate.entities.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, UUID> {
}
