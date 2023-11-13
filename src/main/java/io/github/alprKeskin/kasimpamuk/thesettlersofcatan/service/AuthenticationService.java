package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.RegistrationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void registerUser(RegistrationInformation registrationInformation) {
        User user = new User(
                registrationInformation.getEmail(),
                passwordEncoder.encode(registrationInformation.getPassword()),
                0,
                0,
                0
        );

        databaseService.addUser(user);
    }

    public boolean loginUser(String email, String password) {
        Optional<User> optionalUser = databaseService.getUserByEmail(email);
        if (optionalUser.isEmpty()) throw new RuntimeException("Given email is wrong");
        User user = optionalUser.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

}
