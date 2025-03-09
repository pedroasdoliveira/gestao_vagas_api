package br.com.pedrooliveira.rocket_project.modules.candidates.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

//@Data
@Entity
@Table(name = "candidate")
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Pattern(regexp = "\\S+", message = "O username não deve conter espaço")
    @Column(nullable = false, length = 255, unique = true)
    private String username;

    @Email(message = "O e-mail deve ser válido")
    @Column(unique = true, nullable = false)
    private String email;

    @Length(min = 10, max = 60, message = "A senha deve conter entre 10 e 60 caracteres")
    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 255)
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
