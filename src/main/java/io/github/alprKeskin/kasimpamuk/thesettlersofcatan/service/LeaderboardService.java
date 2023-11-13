package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.enumeration.TimeInterval;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class LeaderboardService {

    @Autowired
    private DatabaseService databaseService;

    public List<User> getLeaderboardOfTheWeek() {
        return getLeaderboard(TimeInterval.WEEK);
    }

    public List<User> getLeaderboardOfTheMonth() {
        return getLeaderboard(TimeInterval.MONTH);
    }

    public List<User> getLeaderboardOfAllTimes() {
        return getLeaderboard(TimeInterval.ALL_TIME);
    }

    private List<User> getLeaderboard(TimeInterval timeInterval) {
        return getUsersOrderedByScores(timeInterval);
    }

    private List<User> getUsersOrderedByScores(TimeInterval timeInterval) {
        List<User> users = databaseService.getAllUsers();
        return sortUsersByHighestAllTimesScore(users, timeInterval);
    }

    private List<User> sortUsersByHighestAllTimesScore(List<User> users, TimeInterval timeInterval) {
        if (timeInterval == TimeInterval.WEEK) {
            users.sort(Comparator.comparing(User::getHighestWeekScore));
        }
        else if (timeInterval == TimeInterval.MONTH) {
            users.sort(Comparator.comparing(User::getHighestMonthScore));
        }
        else {
            users.sort(Comparator.comparing(User::getHighestAllTimeScore));
        }
        return reverseLeaderboard(users);
    }

    private List<User> reverseLeaderboard(List<User> users) {
        Collections.reverse(users);
        return users;
    }

}
