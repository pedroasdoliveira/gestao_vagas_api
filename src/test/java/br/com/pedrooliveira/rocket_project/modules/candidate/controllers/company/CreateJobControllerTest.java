package br.com.pedrooliveira.rocket_project.modules.candidate.controllers.company;

import br.com.pedrooliveira.rocket_project.exceptions.company.CompanyNotFoundException;
import br.com.pedrooliveira.rocket_project.modules.company.dto.CreateJobDTO;
import br.com.pedrooliveira.rocket_project.modules.company.entities.Company;
import br.com.pedrooliveira.rocket_project.modules.company.repositories.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.pedrooliveira.rocket_project.utils.TestUtils.generateToken;
import static br.com.pedrooliveira.rocket_project.utils.TestUtils.objectToJSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should be able to create a job")
    public void should_be_able_to_create_a_job() throws Exception {
        Company company = Company.builder()
                .description("Company Test")
                .email("company_test@company.com")
                .name("Test")
                .username("company_test")
                .password("12345678900")
                .website("www.test_company.com")
                .nationalCompany(false)
                .build();
        company = companyRepository.saveAndFlush(company);

        var createJobDto = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("Teste")
                .name("Teste")
                .requirements("REQUIREMENTS_TEST")
                .level("Teste")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJSON(createJobDto))
                        .header("Authorization", generateToken(company.getId(), "DEVVAGAS_@123456*")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Should not be able to create a new job if company not found")
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        var createJobDto = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("Teste")
                .name("Teste")
                .requirements("REQUIREMENTS_TEST")
                .level("Teste")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(createJobDto))
                .header("Authorization", generateToken(UUID.randomUUID(), "DEVVAGAS_@123456*")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
