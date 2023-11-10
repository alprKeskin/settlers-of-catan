package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.RegistrationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.LeaderboardService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.RegisterService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.UserManagerService;
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

    @Autowired
    private LeaderboardService leaderboardService;

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping("/server-control")
    public ResponseEntity<String> serverControl() {
        return ResponseEntity.ok("The server is up!");
    }

    /***
     * Adds the user to the Users table in the database.
     * @param registrationInformation: email and password.
     * @return: Success of the registration.
     */
    @PostMapping("/register")
    public boolean register(@RequestBody RegistrationInformation registrationInformation) {
        return registerService.registerUser(registrationInformation);
    }

    /***
     * List users ordered by their weekly scores.
     * @return: List of users ordered by weekly scores.
     */
    @GetMapping("/weekly-leaderboard")
    public List<User> getWeeklyLeaderBoard() {
        return leaderboardService.getLeaderboardOfTheWeek();
    }

    /***
     * List users ordered by their monthly scores.
     * @return: List of users ordered by monthly scores.
     */
    @GetMapping("/monthly-leaderboard")
    public List<User> getMonthlyLeaderBoard() {
        return leaderboardService.getLeaderboardOfTheMonth();
    }

    /***
     * List users ordered by their all times scores.
     * @return: List of users ordered by all times scores.
     */
    @GetMapping("/all-time-leaderboard")
    public List<User> getAllTimeLeaderBoard() {
        return leaderboardService.getLeaderboardOfAllTimes();
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
    @PostMapping("add-users")
    public List<User> addUsers(@RequestBody List<User> userList) {
        return userManagerService.addUsers(userList);
    }

    /***
     * Deletes a user.
     * @param email: Email of the user to be deleted.
     * @return: Success of the deletion.
     */
    @PostMapping("delete-user")
    public boolean deleteUser(@RequestBody String email) {
        return userManagerService.deleteUser(email);
    }
}
