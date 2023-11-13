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

    /***
     * Gets the user with the given email.
     * If no user with the given email exists in the database, returns null
     * @param email: Email of the user
     * @return: user with the given email
     */
    @PostMapping("/get-user")
    public User getUser(@RequestBody String email) {
        return userManagerService.getUserByEmail(email);
    }

    /***
     * Gets all users in the database
     * @return: List of users in the database
     */
    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        return userManagerService.getAllUsers();
    }

    /***
     * Adds a user
     * @param user: User to be added.
     * @return: Success of the addition.
     * @exception: Not proper email. Email should end with @outlook.com
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
