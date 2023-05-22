package vit.projects.hudeem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "record")
public class RecordEntity {
    @Id
    @GeneratedValue
    private Long id;
    private double currentWeight;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
