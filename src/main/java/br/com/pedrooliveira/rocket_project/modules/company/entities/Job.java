package br.com.pedrooliveira.rocket_project.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 254)
    @Schema(example = "Vaga para Desenvolvedor(a)")
    private String name;

    @Column(nullable = false)
    @Schema(example = "Vaga voltada para Desenvolvimento Back-end")
    private String description;

    @NotBlank(message = "O campo de Hierarquia(lavel) da vaga deve ser informado!")
    @Column(nullable = false, length = 254)
    @Schema(example = "Junior")
    private String level;

    @Column(nullable = false, length = 254)
    @Schema(example = "GymPass, VR")
    private String benefits;

    @Column(nullable = false, length = 254)
    private String requirements;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;
}
