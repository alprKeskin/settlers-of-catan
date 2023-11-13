package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserManagerServiceTest {

    @Mock
    private DatabaseService databaseService;

    @InjectMocks
    private UserManagerService userManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenUserWithUniqueEmail_whenAddUser_thenReturnTrue() {
        List<User> userList = createCorrectUserList();
        User user = new User(147, "unique_email@outlook.com", "1234", 50, 60, 70);

        // set behavior of the databaseService.addUser method
        mockDatabaseServiceAddUser(user, userList);

        boolean ACTUAL = userManagerService.addUser(user);

        assertTrue(ACTUAL);
    }

    @Test
    void givenUserWithExistingEmailInDatabase_whenAddUser_thenReturnFalse() {
        List<User> userList = createCorrectUserList();
        User user = new User(149, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000);

        // set behavior of the databaseService.addUser method
        mockDatabaseServiceAddUser(user, userList);

        boolean ACTUAL = userManagerService.addUser(user);

        assertFalse(ACTUAL);
    }

    @Test
    void givenCorrectUserList_whenAddUsers_thenReturnEmptyList() {
        List<User> userList = createCorrectUserList();

        Map<String, List<Integer>> map = findIndexesOfUsersWithDuplicateIds(userList);
        List<Integer> duplicatedUserIndexList = map.get("duplicatedUserIndexList");
        List<Integer> notDuplicatedUserIndexList = map.get("notDuplicatedUserIndexList");

        // set rules for the mock
        for (int i = 0; i < duplicatedUserIndexList.size(); i++) {
            when(databaseService.addUser(userList.get(duplicatedUserIndexList.get(i)))).thenReturn(false);
        }
        for (int i = 0; i < notDuplicatedUserIndexList.size(); i++) {
            when(databaseService.addUser(userList.get(notDuplicatedUserIndexList.get(i)))).thenReturn(true);
        }

        List<User> unsuccessfulAdditions = userManagerService.addMultipleUsers(userList);

        assertTrue(unsuccessfulAdditions.isEmpty());
    }

    @Test
    void givenErrorUserListDueToDuplicatedId_whenAddUsers_thenNotReturnEmptyList() {
        List<User> userList = createErrorUserListDueToDuplicatedId();

        Map<String, List<Integer>> map = findIndexesOfUsersWithDuplicateIds(userList);
        List<Integer> duplicatedUserIndexList = map.get("duplicatedUserIndexList");
        List<Integer> notDuplicatedUserIndexList = map.get("notDuplicatedUserIndexList");

        // set rules for the mock
        for (int i = 0; i < duplicatedUserIndexList.size(); i++) {
            when(databaseService.addUser(userList.get(duplicatedUserIndexList.get(i)))).thenReturn(false);
        }
        for (int i = 0; i < notDuplicatedUserIndexList.size(); i++) {
            when(databaseService.addUser(userList.get(notDuplicatedUserIndexList.get(i)))).thenReturn(true);
        }

        List<User> unsuccessfulAdditions = userManagerService.addMultipleUsers(userList);
        assertFalse(unsuccessfulAdditions.isEmpty());
    }

    @Test
    void givenExistingUser_whenDeleteUser_thenReturnTrue() {
        List<User> userList = createCorrectUserList();
        User user = new User(150, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000);

        mockDatabaseServiceDeleteUser(user.getEmail(), userList);

        boolean ACTUAL = userManagerService.deleteUserByEmail(user.getEmail());

        assertTrue(ACTUAL);
    }

    @Test
    void givenNotExistingUser_whenDeleteUser_thenReturnFalse() {
        List<User> userList = createCorrectUserList();
        User user = new User(151, "unique_email@outlook.com", "1234", 1000, 1000, 1000);

        mockDatabaseServiceDeleteUser(user.getEmail(), userList);

        boolean ACTUAL = userManagerService.deleteUserByEmail(user.getEmail());

        assertFalse(ACTUAL);
    }


    private List<User> createCorrectUserList() {
        List<User> userList = new ArrayList<>();

        userList.add(new User(3, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        userList.add(new User(1, "alper_keskin@outlook.com", "123", 30, 20, 50));
        userList.add(new User(2, "kasim_pamuk@outlook.com", "123", 100, 10, 20));
        userList.add(new User(4, "johny_bravo@outlook.com", "123", 8, 5, 3));
        userList.add(new User(5, "sinan_engin@outlook.com", "123", 0, 0, 0));
        userList.add(new User(6, "elon_musk@outlook.com", "X", 999, 999, 999));

        return userList;
    }

    private List<User> createErrorUserListDueToDuplicatedId() {
        List<User> userList = new ArrayList<>();

        userList.add(new User(3, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        userList.add(new User(3, "alper_keskin@outlook.com", "123", 30, 20, 50));

        return userList;
    }

    private Map<String, List<Integer>> findIndexesOfUsersWithDuplicateIds(List<User> userList) {
        List<Integer> duplicatedUserIndexList = new ArrayList<>();
        List<Integer> notDuplicatedUserIndexList = new ArrayList<>();

        notDuplicatedUserIndexList.add(0);

        // find the indexes of users having duplicated id
        for (int i = 1; i < userList.size(); i++) {
            Integer currentUserId = userList.get(i).getId();
            for (int j = i - 1; j < i; j++) {
                // if it is duplicated
                if (Objects.equals(currentUserId, userList.get(j).getId())) {
                    duplicatedUserIndexList.add(i);
                }
                // if it is not duplicated
                else {
                    notDuplicatedUserIndexList.add(i);
                }
            }
        }

        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        map.put("duplicatedUserIndexList", duplicatedUserIndexList);
        map.put("notDuplicatedUserIndexList", notDuplicatedUserIndexList);

        return map;
    }

    private void mockDatabaseServiceAddUser(User userToBeAdded, List<User> usersInTheDatabase) {
        boolean doesEmailExistInUsers = doesEmailExistInUsers(usersInTheDatabase, userToBeAdded.getEmail());

        if (doesEmailExistInUsers) {
            when(databaseService.addUser(userToBeAdded)).thenReturn(false);
        }
        else {
            when(databaseService.addUser(userToBeAdded)).thenReturn(true);
        }
    }

    private void mockDatabaseServiceDeleteUser(String emailOfTheUserToBeDeleted, List<User> usersInTheDatabase) {
        boolean doesEmailExistInUsers = doesEmailExistInUsers(usersInTheDatabase, emailOfTheUserToBeDeleted);

        if (doesEmailExistInUsers) {
            when(databaseService.deleteUserByEmail(emailOfTheUserToBeDeleted)).thenReturn(true);
        }
        else {
            when(databaseService.deleteUserByEmail(emailOfTheUserToBeDeleted)).thenReturn(false);
        }
    }

    private boolean doesEmailExistInUsers(List<User> userList, String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

}