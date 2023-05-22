package vit.projects.hudeem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private String username;
    private String password;
    private char gender;
    private double height;
    private double initialWeight;
    private double goalWeight;
    private double progress;
    @OneToMany(mappedBy = "user")
    private List<RecordEntity> records;
}
