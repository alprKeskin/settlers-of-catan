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

    @PostMapping("/register")
    public boolean register(@RequestBody RegistrationInformation registrationInformation) {
        return registerService.registerUser(registrationInformation);
    }

    @GetMapping("/weekly-leaderboard")
    public List<User> getWeeklyLeaderBoard() {
        return leaderboardService.getLeaderboardOfTheWeek();
    }

    @GetMapping("/monthly-leaderboard")
    public List<User> getMonthlyLeaderBoard() {
        return leaderboardService.getLeaderboardOfTheMonth();
    }

    @GetMapping("/all-time-leaderboard")
    public List<User> getAllTimeLeaderBoard() {
        return leaderboardService.getLeaderboardOfAllTimes();
    }

    @PostMapping("add-user")
    public boolean addUser(@RequestBody User user) {
        return userManagerService.addUser(user);
    }

    @PostMapping("add-users")
    public ResponseEntity<String> addUsers(@RequestBody List<User> userList) {
        List<User> unsuccessfulUserAdditions = userManagerService.addUsers(userList);
        if (unsuccessfulUserAdditions.isEmpty()) {
            return ResponseEntity.ok("Users are added successfully.");
        }
        return ResponseEntity.ok("Following users could not be added: " + unsuccessfulUserAdditions.toString());
    }

    @PostMapping("delete-user")
    public boolean deleteUser(@RequestBody String email) {
        return userManagerService.deleteUser(email);
    }
}
