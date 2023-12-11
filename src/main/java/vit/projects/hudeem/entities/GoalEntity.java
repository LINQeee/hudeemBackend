package vit.projects.hudeem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "goal")
public class GoalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double initialWeight;
    @Column(nullable = false)
    private double goalWeight;
    private double progress;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column(nullable = false)
    private LocalDate startDate;
    private double perDay;
    private double perWeek;
    private LocalDate plannedDate;
    @Column(nullable = false)
    private double currentWeight;
    private double weightLost;
    private double weightLeft;
    @OneToMany(mappedBy = "goal")
    private List<RecordEntity> records;
    private int perDayProgress;
    private int perWeekProgress;
}
