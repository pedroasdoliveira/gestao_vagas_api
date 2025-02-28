package br.com.pedrooliveira.rocket_project.modules.company.useCases;

import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public Job execute(Job job) {
        return this.jobRepository.save(job);
    }
}
