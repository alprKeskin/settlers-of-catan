package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;


import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean doesUserExist(Long id) {
        return userRepository.existsById(id);
    }

    public boolean doesUserExist(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public boolean deleteUser(Long id) {
        if (!doesUserExist(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    public boolean deleteUser(String email) {
        if (!doesUserExist(email)) {
            return false;
        }
        userRepository.deleteByEmail(email);
        return true;
    }

    public boolean addUser(User user) {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

}
