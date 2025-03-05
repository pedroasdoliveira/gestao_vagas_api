package br.com.pedrooliveira.rocket_project.modules.company.useCases;

import br.com.pedrooliveira.rocket_project.modules.company.dto.AuthCompanyDTO;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Company;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        Company company = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Company username/password incorrect!");
                });

        // Verificar a senha
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withExpiresAt(Instant.now().plus(Duration.ofHours(22)))
                .withIssuer("devvagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);
    }
}
