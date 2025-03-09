package br.com.pedrooliveira.rocket_project.modules.company.controllers;

import br.com.pedrooliveira.rocket_project.modules.company.dto.AuthCompanyDTO;
import br.com.pedrooliveira.rocket_project.modules.company.dto.AuthCompanyResponseDTO;
import br.com.pedrooliveira.rocket_project.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            AuthCompanyResponseDTO result = this.authCompanyUseCase.execute(authCompanyDTO);

            return ResponseEntity.ok().body(result);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            ex.fillInStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
