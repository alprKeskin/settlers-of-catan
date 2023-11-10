package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.RegistrationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CatanController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("server-control")
    public ResponseEntity<String> serverControl() {
        return ResponseEntity.ok("The server is up!");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegistrationInformation registrationInformation) {
        boolean isRegistrationSuccessful = registerService.registerUser(registrationInformation);
        if (isRegistrationSuccessful) return ResponseEntity.ok("Registration is successful.");
        return ResponseEntity.ok("Registration is not successful.");
    }

}
