package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication;

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
