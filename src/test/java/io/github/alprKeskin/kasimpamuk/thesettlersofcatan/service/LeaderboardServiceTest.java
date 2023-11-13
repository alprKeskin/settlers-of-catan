package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class LeaderboardServiceTest {

    @Mock
    private DatabaseService databaseService;

    @InjectMocks
    private LeaderboardService leaderboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenSmallMockUsers_whenGetLeaderboardOfTheWeek_thenReturnLeaderboard() {
        // mock the data in the database
        List<User> users = createSmallMockUsers();
        when(databaseService.getAllUsers()).thenReturn(users);

        List<User> EXPECTED = new ArrayList<>();
        EXPECTED.add(new User(2L, "kasim@outlook.com", "123", 100, 10, 20));
        EXPECTED.add(new User(1L, "alper@outlook.com", "123", 30, 20, 50));
        EXPECTED.add(new User(3L, "john@outlook.com", "123", 20, 50, 5));

        List<User> ACTUAL = leaderboardService.getLeaderboardOfTheWeek();

        Assertions.assertIterableEquals(EXPECTED, ACTUAL);
    }

    @Test
    void givenLargeMockUsers_whenGetLeaderboardOfTheWeek_thenReturnLeaderboard() {
        // mock the data in the database
        List<User> users = createLargeMockUsers();
        when(databaseService.getAllUsers()).thenReturn(users);

        List<User> EXPECTED = new ArrayList<>();
        EXPECTED.add(new User(3L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        EXPECTED.add(new User(6L, "elon_musk@outlook.com", "X", 999, 999, 999));
        EXPECTED.add(new User(2L, "kasim_pamuk@outlook.com", "123", 100, 10, 20));
        EXPECTED.add(new User(1L, "alper_keskin@outlook.com", "123", 30, 20, 50));
        EXPECTED.add(new User(4L, "johny_bravo@outlook.com", "123", 8, 5, 3));
        EXPECTED.add(new User(5L, "sinan_engin@outlook.com", "123", 0, 0, 0));

        List<User> ACTUAL = leaderboardService.getLeaderboardOfTheWeek();

        Assertions.assertIterableEquals(EXPECTED, ACTUAL);
    }

    @Test
    void givenSmallMockUsers_whenGetLeaderboardOfTheMonth_thenReturnLeaderboard() {
        // mock the data in the database
        List<User> users = createSmallMockUsers();
        when(databaseService.getAllUsers()).thenReturn(users);

        List<User> EXPECTED = new ArrayList<>();
        EXPECTED.add(new User(3L, "john@outlook.com", "123", 20, 50, 5));
        EXPECTED.add(new User(1L, "alper@outlook.com", "123", 30, 20, 50));
        EXPECTED.add(new User(2L, "kasim@outlook.com", "123", 100, 10, 20));

        List<User> ACTUAL = leaderboardService.getLeaderboardOfTheMonth();

        Assertions.assertIterableEquals(EXPECTED, ACTUAL);
    }

    @Test
    void givenLargeMockUsers_whenGetLeaderboardOfTheMonth_thenReturnLeaderboard() {
        // mock the data in the database
        List<User> users = createLargeMockUsers();
        when(databaseService.getAllUsers()).thenReturn(users);

        List<User> EXPECTED = new ArrayList<>();
        EXPECTED.add(new User(3L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        EXPECTED.add(new User(6L, "elon_musk@outlook.com", "X", 999, 999, 999));
        EXPECTED.add(new User(1L, "alper_keskin@outlook.com", "123", 30, 20, 50));
        EXPECTED.add(new User(2L, "kasim_pamuk@outlook.com", "123", 100, 10, 20));
        EXPECTED.add(new User(4L, "johny_bravo@outlook.com", "123", 8, 5, 3));
        EXPECTED.add(new User(5L, "sinan_engin@outlook.com", "123", 0, 0, 0));

        List<User> ACTUAL = leaderboardService.getLeaderboardOfTheMonth();

        Assertions.assertIterableEquals(EXPECTED, ACTUAL);
    }

    @Test
    void givenSmallMockUsers_whenGetLeaderboardOfAllTimes_thenReturnLeaderboard() {
        // mock the data in the database
        List<User> users = createSmallMockUsers();
        when(databaseService.getAllUsers()).thenReturn(users);

        List<User> EXPECTED = new ArrayList<>();
        EXPECTED.add(new User(1L, "alper@outlook.com", "123", 30, 20, 50));
        EXPECTED.add(new User(2L, "kasim@outlook.com", "123", 100, 10, 20));
        EXPECTED.add(new User(3L, "john@outlook.com", "123", 20, 50, 5));

        List<User> ACTUAL = leaderboardService.getLeaderboardOfAllTimes();

        Assertions.assertIterableEquals(EXPECTED, ACTUAL);
    }

    @Test
    void givenLargeMockUsers_whenGetLeaderboardOfAllTimes_thenReturnLeaderboard() {
        // mock the data in the database
        List<User> users = createLargeMockUsers();
        when(databaseService.getAllUsers()).thenReturn(users);

        List<User> EXPECTED = new ArrayList<>();
        EXPECTED.add(new User(3L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        EXPECTED.add(new User(6L, "elon_musk@outlook.com", "X", 999, 999, 999));
        EXPECTED.add(new User(1L, "alper_keskin@outlook.com", "123", 30, 20, 50));
        EXPECTED.add(new User(2L, "kasim_pamuk@outlook.com", "123", 100, 10, 20));
        EXPECTED.add(new User(4L, "johny_bravo@outlook.com", "123", 8, 5, 3));
        EXPECTED.add(new User(5L, "sinan_engin@outlook.com", "123", 0, 0, 0));

        List<User> ACTUAL = leaderboardService.getLeaderboardOfAllTimes();

        Assertions.assertIterableEquals(EXPECTED, ACTUAL);
    }

    private List<User> createSmallMockUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(1L, "alper@outlook.com", "123", 30, 20, 50));
        users.add(new User(2L, "kasim@outlook.com", "123", 100, 10, 20));
        users.add(new User(3L, "john@outlook.com", "123", 20, 50, 5));

        return users;
    }

    private List<User> createLargeMockUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(3L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        users.add(new User(1L, "alper_keskin@outlook.com", "123", 30, 20, 50));
        users.add(new User(2L, "kasim_pamuk@outlook.com", "123", 100, 10, 20));
        users.add(new User(4L, "johny_bravo@outlook.com", "123", 8, 5, 3));
        users.add(new User(5L, "sinan_engin@outlook.com", "123", 0, 0, 0));
        users.add(new User(6L, "elon_musk@outlook.com", "X", 999, 999, 999));

        return users;
    }

}