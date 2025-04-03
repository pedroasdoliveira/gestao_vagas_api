package br.com.pedrooliveira.rocket_project.modules.company.repositories;

import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {

    // 'contains - LIKE'
    // select * from job where description %like%
    List<Job> findByDescriptionContainingIgnoreCase(String filter);
}
