package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "highest_week_score")
    private Integer highestWeekScore;
    @Column(name = "highest_month_score")
    private Integer highestMonthScore;
    @Column(name = "highest_all_time_score")
    private Integer highestAllTimeScore;

    public User (String email, String password, Integer highestWeekScore, Integer highestMonthScore, Integer highestAllTimeScore) {
        this.email = email;
        this.password = password;
        this.highestWeekScore = highestWeekScore;
        this.highestMonthScore = highestMonthScore;
        this.highestAllTimeScore = highestAllTimeScore;
    }

}