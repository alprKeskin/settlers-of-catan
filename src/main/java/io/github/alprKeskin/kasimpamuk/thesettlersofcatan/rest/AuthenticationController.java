package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.LoginInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.RegistrationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody RegistrationInformation registrationInformation) {
        authenticationService.registerUser(registrationInformation);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginInformation loginInformation) {
        boolean loginSuccess = authenticationService.loginUser(loginInformation.getEmail(), loginInformation.getPassword());
        if (loginSuccess) {
            return ResponseEntity.ok("Login successful");

        }
        else {
            return ResponseEntity.ok("Login not successful");
        }
    }

}
