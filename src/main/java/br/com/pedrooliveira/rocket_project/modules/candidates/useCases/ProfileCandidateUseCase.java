package br.com.pedrooliveira.rocket_project.modules.candidates.useCases;

import br.com.pedrooliveira.rocket_project.modules.candidates.dto.ProfileCandidateResponseDTO;
import br.com.pedrooliveira.rocket_project.modules.candidates.entities.Candidate;
import br.com.pedrooliveira.rocket_project.modules.candidates.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        Candidate candidate = this.candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found.");
                });

        return ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .build();

    }
}
