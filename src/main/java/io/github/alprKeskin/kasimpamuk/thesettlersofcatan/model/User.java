package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Users")
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

    public User (String email, String password, Integer highestWeekScore, Integer highestMonthScore, Integer highestAllTimeScore) {
        this.email = email;
        this.password = password;
        this.highestWeekScore = highestWeekScore;
        this.highestMonthScore = highestMonthScore;
        this.highestAllTimeScore = highestAllTimeScore;
    }

}