package vit.projects.hudeem.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private char gender;
    private double height;
    private double initialWeight;
    private double goalWeight;
    private double progress;
    @OneToMany(mappedBy = "user")
    private List<RecordEntity> records;
    private LocalDate startDate;
    private double perDay;
    private double perWeek;
    private LocalDate plannedDate;
    private double currentWeight;
    private double weightLost;
    private double weightLeft;

}
