package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationInformation {
    private String email;
    private String password;
}
