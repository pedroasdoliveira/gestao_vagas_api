package br.com.pedrooliveira.rocket_project.modules.candidates.controllers;

import br.com.pedrooliveira.rocket_project.modules.candidates.dto.AuthCandidateRequestDTO;
import br.com.pedrooliveira.rocket_project.modules.candidates.dto.AuthCandidateResponseDTO;
import br.com.pedrooliveira.rocket_project.modules.candidates.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidate")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            AuthCandidateResponseDTO token = this.authCandidateUseCase.execute(authCandidateRequestDTO);

            return ResponseEntity.ok().body(token);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}
