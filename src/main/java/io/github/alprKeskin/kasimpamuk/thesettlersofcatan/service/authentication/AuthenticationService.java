package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.RegistrationInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    @Autowired
    public AuthenticationService(RegistrationService registrationService, LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }


    public boolean registerUser(String email, String password) {
        return registrationService.registerUser(email, password);
    }

    public ResponseEntity<?> loginUser(String email, String password) {
        return loginService.loginUser(email, password);
    }

}
