package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CatanUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private Integer highestWeekScore;
    private Integer highestMonthScore;
    private Integer highestAllTimeScore;

    public CatanUser(String email, String password, Integer highestWeekScore, Integer highestMonthScore, Integer highestAllTimeScore) {
        this.email = email;
        this.password = password;
        this.highestWeekScore = highestWeekScore;
        this.highestMonthScore = highestMonthScore;
        this.highestAllTimeScore = highestAllTimeScore;
    }

}