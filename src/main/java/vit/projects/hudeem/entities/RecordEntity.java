package vit.projects.hudeem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "record")
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double currentWeight;

    @Column(unique = true, nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private GoalEntity goal;
}
