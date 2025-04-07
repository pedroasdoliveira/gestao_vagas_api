package br.com.pedrooliveira.rocket_project.modules.candidate.useCases;

import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserNotFoundException;
import br.com.pedrooliveira.rocket_project.exceptions.company.JobNotFoundException;
import br.com.pedrooliveira.rocket_project.modules.candidate.entities.ApplyJob;
import br.com.pedrooliveira.rocket_project.modules.candidate.repositories.ApplyJobRepository;
import br.com.pedrooliveira.rocket_project.modules.candidate.repositories.CandidateRepository;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJob execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        this.jobRepository.findById(idJob)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        ApplyJob applyJob = ApplyJob.builder()
                .candidateId(idCandidate)
                .jobId(idJob).build();

        return applyJobRepository.save(applyJob);
    }
}
