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
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Pattern(regexp = "\\S+", message = "O username não deve conter espaço")
    @Column(nullable = false, length = 255)
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

    public Candidate(UUID id, String name, String username, String email, String password, String description, String curriculum) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.description = description;
        this.curriculum = curriculum;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Candidate that = (Candidate) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCurriculum(), that.getCurriculum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUsername(), getEmail(), getPassword(), getDescription(), getCurriculum());
    }

    @Override
    public String toString() {
        return "CandidateEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", curriculum='" + curriculum + '\'' +
                '}';
    }
}
