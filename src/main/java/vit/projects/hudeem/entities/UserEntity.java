package vit.projects.hudeem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String email;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private char gender;
    @Column(nullable = false)
    private double height;
    @Column(nullable = false)
    private int age;
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
    private String codeHash;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<IpEntity> ips;
    private LocalDate expireAuthorisationDate;
}
