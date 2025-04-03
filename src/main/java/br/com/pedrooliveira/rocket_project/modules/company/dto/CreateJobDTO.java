package br.com.pedrooliveira.rocket_project.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Vaga para pessoa Desenvolvedora", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(example = "Vaga para pessoa Desenvolvedora", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Jr", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
    @Schema(example = "GymPass, VR, Plano de Saúde", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Node.js, TypeScript, MySQL", requiredMode = Schema.RequiredMode.REQUIRED)
    private String requirements;
}
