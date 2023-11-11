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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private Integer highestWeekScore;
    private Integer highestMonthScore;
    private Integer highestAllTimeScore;
}
