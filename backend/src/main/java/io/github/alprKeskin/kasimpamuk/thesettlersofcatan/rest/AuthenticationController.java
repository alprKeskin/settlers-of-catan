package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.RegistrationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.dto.request.LoginRequest;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody RegistrationInformation registrationInformation) {
        return ResponseEntity.ok(authenticationService.registerUser(registrationInformation.getEmail(), registrationInformation.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginReq)  {
        return authenticationService.loginUser(loginReq.getEmail(), loginReq.getPassword());
    }
}
