package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.RegistrationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private RegisterService registerService;

    /***
     * Adds the user to the Users table in the database.
     * @param registrationInformation: email and password.
     * @return: Success of the registration.
     */
    @PostMapping("/register")
    public boolean register(@RequestBody RegistrationInformation registrationInformation) {
        return registerService.registerUser(registrationInformation);
    }

}
