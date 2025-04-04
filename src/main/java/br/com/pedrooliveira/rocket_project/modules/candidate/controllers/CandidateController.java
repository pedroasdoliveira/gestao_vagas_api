package br.com.pedrooliveira.rocket_project.modules.candidate.controllers;

import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserFoundException;
import br.com.pedrooliveira.rocket_project.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.pedrooliveira.rocket_project.modules.candidate.entities.Candidate;
import br.com.pedrooliveira.rocket_project.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.pedrooliveira.rocket_project.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.pedrooliveira.rocket_project.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/add")
    @Operation(summary = "Cadastrar candidato", description = "Função para cadastrar o candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Candidate.class))
            }),
            @ApiResponse(responseCode = "400", description = "User already exist.")
    })
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
    @Operation(summary = "Perfil do Candidato", description = "Função para buscar as informações do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
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

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
            summary = "Listagem de vagas disponíveis para o candidato",
            description = "Lista de vagas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Job.class))
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<Job> findJobByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }
}
