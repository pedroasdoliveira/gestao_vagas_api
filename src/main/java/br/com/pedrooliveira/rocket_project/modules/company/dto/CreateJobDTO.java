package br.com.pedrooliveira.rocket_project.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {

    private String name;
    private String description;
    private String level;
    private String benefits;
    private String requirements;
}
