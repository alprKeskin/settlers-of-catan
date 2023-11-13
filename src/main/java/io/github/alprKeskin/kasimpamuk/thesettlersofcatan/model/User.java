package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    private Integer highestWeekScore;
    @Column
    private Integer highestMonthScore;
    @Column
    private Integer highestAllTimeScore;

    //setters and getters
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setHighestWeekScore(Integer highestWeekScore) {
        this.highestWeekScore = highestWeekScore;
    }
    public Integer getHighestWeekScore() {
        return highestWeekScore;
    }
    public void setHighestMonthScore(Integer highestMonthScore) {
        this.highestMonthScore = highestMonthScore;
    }
    public Integer getHighestMonthScore() {
        return highestMonthScore;
    }
    public void setHighestAllTimeScore(Integer highestAllTimeScore) {
        this.highestAllTimeScore = highestAllTimeScore;
    }
    public Integer getHighestAllTimeScore() {
        return highestAllTimeScore;
    }


}