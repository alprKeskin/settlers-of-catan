package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/database-crud")
public class DatabaseController {

    @Autowired
    private UserManagerService userManagerService;

    @PostMapping("/get-user")
    public User getUser(@RequestBody String email) {
        return userManagerService.getUserByEmail(email);
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        return userManagerService.getAllUsers();
    }

    /***
     * Adds a user
     * @param user: User to be added.
     * @return: Success of the addition.
     */
    @PostMapping("add-user")
    public boolean addUser(@RequestBody User user) {
        return userManagerService.addUser(user);
    }

    /***
     * Adds a list of users.
     * @param userList: List of users to be added.
     * @return: List of users that cannot be added to the database.
     */
    @PostMapping("add-multiple-users")
    public List<User> addMultipleUsers(@RequestBody List<User> userList) {
        return userManagerService.addMultipleUsers(userList);
    }

    /***
     * Deletes a user.
     * @param email: Email of the user to be deleted.
     * @return: Success of the deletion.
     */
    @PostMapping("delete-user")
    public boolean deleteUser(@RequestBody String email) {
        return userManagerService.deleteUserByEmail(email);
    }

}
