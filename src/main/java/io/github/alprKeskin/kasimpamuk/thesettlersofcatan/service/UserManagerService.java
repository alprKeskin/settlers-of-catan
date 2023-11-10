package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserManagerService {

    @Autowired
    private DatabaseService databaseService;

    /***
     * Adds the user.
     * @param user: User to be added to the database.
     * @return: True if addition is successful, otherwise false.
     */
    public boolean addUser(User user) {
        return databaseService.addUser(user);
    }

    /***
     * Adds a list of users.
     * @param userList: List of users to be added.
     * @return: List of users that cannot be added to the database.
     */
    public List<User> addUsers(List<User> userList) {
        List<User> unsuccessfulAdditions = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (!databaseService.addUser(userList.get(i))) {
                unsuccessfulAdditions.add(userList.get(i));
            }
        }
        return unsuccessfulAdditions;
    }

    public boolean deleteUser(String email) {
        return databaseService.deleteUser(email);
    }
}
