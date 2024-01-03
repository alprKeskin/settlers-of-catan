package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.userdatabase.gamestatistics;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.enumeration.TimeInterval;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.userdatabase.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class LeaderboardService {

    private final DatabaseService databaseService;

    @Autowired
    public LeaderboardService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<CatanUser> getLeaderboardOfTheWeek() {
        return getLeaderboard(TimeInterval.WEEK);
    }

    public List<CatanUser> getLeaderboardOfTheMonth() {
        return getLeaderboard(TimeInterval.MONTH);
    }

    public List<CatanUser> getLeaderboardOfAllTimes() {
        return getLeaderboard(TimeInterval.ALL_TIME);
    }

    private List<CatanUser> getLeaderboard(TimeInterval timeInterval) {
        return getUsersOrderedByScores(timeInterval);
    }

    private List<CatanUser> getUsersOrderedByScores(TimeInterval timeInterval) {
        List<CatanUser> catanUsers = databaseService.getAllUsers();
        return sortUsersByHighestAllTimesScore(catanUsers, timeInterval);
    }

    private List<CatanUser> sortUsersByHighestAllTimesScore(List<CatanUser> catanUsers, TimeInterval timeInterval) {
        if (timeInterval == TimeInterval.WEEK) {
            catanUsers.sort(Comparator.comparing(CatanUser::getHighestWeekScore));
        }
        else if (timeInterval == TimeInterval.MONTH) {
            catanUsers.sort(Comparator.comparing(CatanUser::getHighestMonthScore));
        }
        else {
            catanUsers.sort(Comparator.comparing(CatanUser::getHighestAllTimeScore));
        }
        return reverseLeaderboard(catanUsers);
    }

    private List<CatanUser> reverseLeaderboard(List<CatanUser> catanUsers) {
        Collections.reverse(catanUsers);
        return catanUsers;
    }

}
