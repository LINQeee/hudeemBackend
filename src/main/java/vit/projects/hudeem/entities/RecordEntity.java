package vit.projects.hudeem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "record")
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double currentWeight;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
