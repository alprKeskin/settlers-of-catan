package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.RegistrationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.DatabaseService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.LeaderboardService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.RegisterService;
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
    private DatabaseService databaseService; // Will be removed

    @GetMapping("/server-control")
    public ResponseEntity<String> serverControl() {
        return ResponseEntity.ok("The server is up!");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationInformation registrationInformation) {
        boolean isRegistrationSuccessful = registerService.registerUser(registrationInformation);
        if (isRegistrationSuccessful) return ResponseEntity.ok("Registration is successful.");
        return ResponseEntity.ok("Registration is not successful.");
    }

    @GetMapping("/secured")
    public ResponseEntity<String> secured() {
        return ResponseEntity.ok("Hello from secured!");
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
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        return ResponseEntity.ok(databaseService.addUser(user));
    }
}
