package br.com.pedrooliveira.rocket_project.modules.candidate.useCases;

import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserNotFoundException;
import br.com.pedrooliveira.rocket_project.exceptions.company.JobNotFoundException;
import br.com.pedrooliveira.rocket_project.modules.candidate.entities.ApplyJob;
import br.com.pedrooliveira.rocket_project.modules.candidate.entities.Candidate;
import br.com.pedrooliveira.rocket_project.modules.candidate.repositories.ApplyJobRepository;
import br.com.pedrooliveira.rocket_project.modules.candidate.repositories.CandidateRepository;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        UUID idCandidate = UUID.randomUUID();

        var candidate = new Candidate();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));
        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void should_be_able_to_create_a_new_apply_job() {
        UUID idCandidate = UUID.randomUUID();
        UUID idJob = UUID.randomUUID();

        ApplyJob applyJob = ApplyJob.builder()
                .candidateId(idCandidate)
                .jobId(idJob).build();
        ApplyJob applyJobCreated = ApplyJob.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new Candidate()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new Job()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
