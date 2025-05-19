package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.userdatabase.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DatabaseService databaseService;

    @Autowired
    public CustomUserDetailsService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<CatanUser> optionalUser = databaseService.getUserByEmail(email);
        if (optionalUser.isEmpty()) throw new RuntimeException("No user with the given email address.");
        CatanUser catanUser = optionalUser.get();

        List<String> roles = new ArrayList<>();
        roles.add("User");

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(catanUser.getEmail())
                .password(catanUser.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
        return userDetails;
    }
}
