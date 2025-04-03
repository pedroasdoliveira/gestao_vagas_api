package br.com.pedrooliveira.rocket_project.modules.candidates.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    private UUID id;
    @Schema(example = "Pedro")
    private String name;
    @Schema(example = "Desenvolvedor Web")
    private String description;
    @Schema(example = "pedro")
    private String username;
    @Schema(example = "pedro@gmail.com")
    private String email;
}
