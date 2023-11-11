package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;


import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DatabaseService {

    private static Logger logger = LoggerFactory.getLogger(Slf4j.class);

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        if (!doesUserExistByEmail(email)) {
            logger.error("User with the given email does not exist in the database");
            return Optional.empty();
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            logger.error("User with the given email has been found, but cannot be retrieved for some reason");
            return Optional.empty();
        }
        else {
            return Optional.of(user);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            logger.error("A user with the same email exist in database.");
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean deleteUserByEmail(String email) {
        if (!doesUserExistByEmail(email)) {
            return false;
        }
        userRepository.deleteByEmail(email);
        return true;
    }

    private boolean doesUserExistByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

}
