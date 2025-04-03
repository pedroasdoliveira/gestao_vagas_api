package br.com.pedrooliveira.rocket_project.modules.company.controllers;

import br.com.pedrooliveira.rocket_project.modules.company.dto.CreateJobDTO;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import br.com.pedrooliveira.rocket_project.modules.company.useCases.CreateJobUseCase;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
@Tag(name = "Vagas", description = "Informações das vagas")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/add")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(
            summary = "Cadastro de vagas",
            description = "Função para cadastras as vagas no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Job.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO job, HttpServletRequest request) {
        try {
            Object companyId = request.getAttribute("company_id");

            var jobEntity = Job.builder()
                    .name(job.getName())
                    .description(job.getDescription())
                    .benefits(job.getBenefits())
                    .level(job.getLevel())
                    .requirements(job.getRequirements())
                    .companyId(UUID.fromString(companyId.toString()))
                    .build();

            Job result = this.createJobUseCase.execute(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
            ex.fillInStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
