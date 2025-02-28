package br.com.pedrooliveira.rocket_project.modules.company.controllers;

import br.com.pedrooliveira.rocket_project.exceptions.IllegalArgumentException;
import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserFoundException;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Company;
import br.com.pedrooliveira.rocket_project.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/add")
    public ResponseEntity<Object> create(@Valid @RequestBody Company company) {
        try {
            Company result = this.createCompanyUseCase.execute(company);

            return ResponseEntity.ok().body(result);
        } catch (UserFoundException | IllegalArgumentException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
