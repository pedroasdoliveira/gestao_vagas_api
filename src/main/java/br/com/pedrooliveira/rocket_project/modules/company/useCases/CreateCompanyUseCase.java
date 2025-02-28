package br.com.pedrooliveira.rocket_project.modules.company.useCases;

import br.com.pedrooliveira.rocket_project.exceptions.candidate.UserFoundException;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Company;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public Company execute(Company company) {
        this.companyRepository
                .findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException("O Username e/ou e-mail da Empresa já existe!");
                });

        if (company.getNationalCompany().equals(true)) {
            if (company.getCnpj() == null || company.getCnpj().isBlank()) {
                throw new IllegalArgumentException("Empresas nacionais devem informar um CNPJ válido!");
            }

            this.companyRepository
                    .findByCnpj(company.getCnpj())
                    .ifPresent((user) -> {
                        throw new UserFoundException("O CNPJ informado já existe!");
                    });
        }

        return this.companyRepository.save(company);
    }
}
