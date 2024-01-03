package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/database")
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
    public ResponseEntity<CatanUser> getUser(@RequestBody String email) {
        return ResponseEntity.ok(userManagerService.getUserByEmail(email));
    }

    /***
     * Gets all users in the database
     * @return: List of users in the database
     */
    @GetMapping("/get-all-users")
    public ResponseEntity<List<CatanUser>> getAllUsers() {
        return ResponseEntity.ok(userManagerService.getAllUsers());
    }

    /***
     * Adds a user
     * @param catanUser: User to be added.
     * @return: Success of the addition.
     * @exception: Not proper email. Email should end with @outlook.com
     */
    @PostMapping("add-user")
    public ResponseEntity<Boolean> addUser(@RequestBody CatanUser catanUser) {
        return ResponseEntity.ok(userManagerService.addUser(catanUser));
    }

    /***
     * Adds a list of users.
     * @param catanUserList: List of users to be added.
     * @return: List of users that cannot be added to the database.
     */
    @PostMapping("add-multiple-users")
    public ResponseEntity<List<CatanUser>> addMultipleUsers(@RequestBody List<CatanUser> catanUserList) {
        return ResponseEntity.ok(userManagerService.addMultipleUsers(catanUserList));
    }

    /***
     * Deletes a user.
     * @param email: Email of the user to be deleted.
     * @return: Success of the deletion.
     */
    @PostMapping("delete-user")
    public ResponseEntity<Boolean> deleteUser(@RequestBody String email) {
        return ResponseEntity.ok(userManagerService.deleteUserByEmail(email));
    }

}
