package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

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

}
