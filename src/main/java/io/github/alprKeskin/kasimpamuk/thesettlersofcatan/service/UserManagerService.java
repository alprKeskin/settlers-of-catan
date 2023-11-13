package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserManagerService {

    private static Logger logger = LoggerFactory.getLogger(Slf4j.class);

    @Autowired
    private DatabaseService databaseService;

    public User getUserByEmail(String email) {
        Optional<User> optionalUser = databaseService.getUserByEmail(email);
        return optionalUser.orElse(null);
    }

    public List<User> getAllUsers() {
        return databaseService.getAllUsers();
    }

    public boolean addUser(User user) {
        return databaseService.addUser(user);
    }

    public List<User> addMultipleUsers(List<User> userList) {
        List<User> unsuccessfulAdditions = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (!databaseService.addUser(userList.get(i))) {
                unsuccessfulAdditions.add(userList.get(i));
            }
        }
        return unsuccessfulAdditions;
    }

    public boolean deleteUserByEmail(String email) {
        return databaseService.deleteUserByEmail(email);
    }

    private boolean isProperEMail(String email) {
        List<String> acceptableEmailSuffixes = new ArrayList<>();
        acceptableEmailSuffixes.add(".com");

        for (int i = 0; i < acceptableEmailSuffixes.size(); i++) {
            if (email.endsWith(acceptableEmailSuffixes.get(i))) {
                return true;
            }
        }
        return false;
    }

}
