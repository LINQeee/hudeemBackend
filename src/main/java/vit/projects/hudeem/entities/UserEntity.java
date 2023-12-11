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
    private String username;
    @Column(nullable = false)
    private String passwordHash;
    private char gender;
    private double height;
    private int age;
    private String codeHash;
    @OneToMany(mappedBy = "user")
    private List<IpEntity> ips;
    @OneToMany(mappedBy = "user")
    private List<GoalEntity> goals;
    private LocalDate expireAuthorisationDate;

    public boolean containsIp(String ip){
        return ips.stream().anyMatch(ipEntity -> ipEntity.getIp().equals(ip));
    }
}
