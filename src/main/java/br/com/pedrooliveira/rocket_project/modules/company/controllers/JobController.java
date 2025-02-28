package br.com.pedrooliveira.rocket_project.modules.company.controllers;

import br.com.pedrooliveira.rocket_project.modules.company.entities.Job;
import br.com.pedrooliveira.rocket_project.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/add")
    public ResponseEntity<Object> create(@Valid @RequestBody Job job) {
        try {
            Job result = this.createJobUseCase.execute(job);

            return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
            ex.fillInStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
