package br.com.pedrooliveira.rocket_project.modules.candidates.controllers;

import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserFoundException;
import br.com.pedrooliveira.rocket_project.modules.candidates.dto.ProfileCandidateResponseDTO;
import br.com.pedrooliveira.rocket_project.modules.candidates.entities.Candidate;
import br.com.pedrooliveira.rocket_project.modules.candidates.repositories.CandidateRepository;
import br.com.pedrooliveira.rocket_project.modules.candidates.useCases.CreateCandidateUseCase;
import br.com.pedrooliveira.rocket_project.modules.candidates.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/add")
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {
        try {
            Candidate result = this.createCandidateUseCase.execute(candidate);

            return ResponseEntity.ok().body(result);
        } catch (UserFoundException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> profile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            ProfileCandidateResponseDTO profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.ok().body(profile);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
