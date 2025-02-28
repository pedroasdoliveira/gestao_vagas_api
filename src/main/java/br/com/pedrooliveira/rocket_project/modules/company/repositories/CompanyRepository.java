package br.com.pedrooliveira.rocket_project.modules.company.repositories;

import br.com.pedrooliveira.rocket_project.modules.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    public Optional<Company> findByUsernameOrEmail(String username, String email);

    public Optional<Company> findByCnpj(String cnpj);
}
