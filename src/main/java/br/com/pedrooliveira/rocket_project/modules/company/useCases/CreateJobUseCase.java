package br.com.pedrooliveira.rocket_project.modules.company.useCases;

import br.com.pedrooliveira.rocket_project.exceptions.company.CompanyNotFoundException;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.CompanyRepository;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Job execute(Job job) {
        companyRepository.findById(job.getCompanyId()).orElseThrow(CompanyNotFoundException::new);

        return this.jobRepository.save(job);
    }
}
