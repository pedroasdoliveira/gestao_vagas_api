package br.com.pedrooliveira.rocket_project.modules.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 254)
    private String name;

    @Column(nullable = false, length = 254)
    private String description;

    @Column(nullable = false, length = 254)
    private String level;

    @Column(nullable = false, length = 254)
    private String benefits;

    @Column(nullable = false, length = 254)
    private String requirements;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id")
    private UUID companyId;
}
