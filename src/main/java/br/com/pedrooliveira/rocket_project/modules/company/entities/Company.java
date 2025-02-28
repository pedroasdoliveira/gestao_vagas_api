package br.com.pedrooliveira.rocket_project.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "company")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 254)
    private String name;

    @Column(length = 20, unique = true)
    private String cnpj;

    @Pattern(regexp = "\\S+", message = "O username não deve conter espaço")
    @Column(nullable = false, length = 254, unique = true)
    private String username;

    @Email(message = "O e-mail deve ser válido")
    @Column(nullable = false, length = 254, unique = true)
    private String email;

    @Length(min = 10, max = 60, message = "A senha deve conter entre 10 e 60 caracteres")
    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 254)
    private String website;

    @Column(length = 254)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
