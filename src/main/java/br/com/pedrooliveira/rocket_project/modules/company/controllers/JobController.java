package br.com.pedrooliveira.rocket_project.modules.company.controllers;

import br.com.pedrooliveira.rocket_project.modules.company.dto.CreateJobDTO;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import br.com.pedrooliveira.rocket_project.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/add")
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
