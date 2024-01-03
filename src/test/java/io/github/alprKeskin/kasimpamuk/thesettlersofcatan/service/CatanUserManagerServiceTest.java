package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CatanUserManagerServiceTest {

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
        List<CatanUser> catanUserList = createCorrectUserList();
        CatanUser catanUser = new CatanUser(147L, "unique_email@outlook.com", "1234", 50, 60, 70);

        // set behavior of the databaseService.addUser method
        mockDatabaseServiceAddUser(catanUser, catanUserList);

        boolean ACTUAL = userManagerService.addUser(catanUser);

        assertTrue(ACTUAL);
    }

    @Test
    void givenUserWithExistingEmailInDatabase_whenAddUser_thenReturnFalse() {
        List<CatanUser> catanUserList = createCorrectUserList();
        CatanUser catanUser = new CatanUser(149L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000);

        // set behavior of the databaseService.addUser method
        mockDatabaseServiceAddUser(catanUser, catanUserList);

        boolean ACTUAL = userManagerService.addUser(catanUser);

        assertFalse(ACTUAL);
    }

    @Test
    void givenCorrectUserList_whenAddUsers_thenReturnEmptyList() {
        List<CatanUser> catanUserList = createCorrectUserList();

        Map<String, List<Integer>> map = findIndexesOfUsersWithDuplicateIds(catanUserList);
        List<Integer> duplicatedUserIndexList = map.get("duplicatedUserIndexList");
        List<Integer> notDuplicatedUserIndexList = map.get("notDuplicatedUserIndexList");

        // set rules for the mock
		for (Integer integer : duplicatedUserIndexList) {
			when(databaseService.addUser(catanUserList.get(integer))).thenReturn(false);
		}
		for (Integer integer : notDuplicatedUserIndexList) {
			when(databaseService.addUser(catanUserList.get(integer))).thenReturn(true);
		}

        List<CatanUser> unsuccessfulAdditions = userManagerService.addMultipleUsers(catanUserList);

        assertTrue(unsuccessfulAdditions.isEmpty());
    }

    @Test
    void givenErrorUserListDueToDuplicatedId_whenAddUsers_thenNotReturnEmptyList() {
        List<CatanUser> catanUserList = createErrorUserListDueToDuplicatedId();

        Map<String, List<Integer>> map = findIndexesOfUsersWithDuplicateIds(catanUserList);
        List<Integer> duplicatedUserIndexList = map.get("duplicatedUserIndexList");
        List<Integer> notDuplicatedUserIndexList = map.get("notDuplicatedUserIndexList");

        // set rules for the mock
		for (Integer integer : duplicatedUserIndexList) {
			when(databaseService.addUser(catanUserList.get(integer))).thenReturn(false);
		}
		for (Integer integer : notDuplicatedUserIndexList) {
			when(databaseService.addUser(catanUserList.get(integer))).thenReturn(true);
		}

        List<CatanUser> unsuccessfulAdditions = userManagerService.addMultipleUsers(catanUserList);
        assertFalse(unsuccessfulAdditions.isEmpty());
    }

    @Test
    void givenExistingUser_whenDeleteUser_thenReturnTrue() {
        List<CatanUser> catanUserList = createCorrectUserList();
        CatanUser catanUser = new CatanUser(150L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000);

        mockDatabaseServiceDeleteUser(catanUser.getEmail(), catanUserList);

        boolean ACTUAL = userManagerService.deleteUserByEmail(catanUser.getEmail());

        assertTrue(ACTUAL);
    }

    @Test
    void givenNotExistingUser_whenDeleteUser_thenReturnFalse() {
        List<CatanUser> catanUserList = createCorrectUserList();
        CatanUser catanUser = new CatanUser(151L, "unique_email@outlook.com", "1234", 1000, 1000, 1000);

        mockDatabaseServiceDeleteUser(catanUser.getEmail(), catanUserList);

        boolean ACTUAL = userManagerService.deleteUserByEmail(catanUser.getEmail());

        assertFalse(ACTUAL);
    }


    private List<CatanUser> createCorrectUserList() {
        List<CatanUser> catanUserList = new ArrayList<>();

        catanUserList.add(new CatanUser(3L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        catanUserList.add(new CatanUser(1L, "alper_keskin@outlook.com", "123", 30, 20, 50));
        catanUserList.add(new CatanUser(2L, "kasim_pamuk@outlook.com", "123", 100, 10, 20));
        catanUserList.add(new CatanUser(4L, "johny_bravo@outlook.com", "123", 8, 5, 3));
        catanUserList.add(new CatanUser(5L, "sinan_engin@outlook.com", "123", 0, 0, 0));
        catanUserList.add(new CatanUser(6L, "elon_musk@outlook.com", "X", 999, 999, 999));

        return catanUserList;
    }

    private List<CatanUser> createErrorUserListDueToDuplicatedId() {
        List<CatanUser> catanUserList = new ArrayList<>();

        catanUserList.add(new CatanUser(3L, "hande_hoca@outlook.com", "1234", 1000, 1000, 1000));
        catanUserList.add(new CatanUser(3L, "alper_keskin@outlook.com", "123", 30, 20, 50));

        return catanUserList;
    }

    private Map<String, List<Integer>> findIndexesOfUsersWithDuplicateIds(List<CatanUser> catanUserList) {
        List<Integer> duplicatedUserIndexList = new ArrayList<>();
        List<Integer> notDuplicatedUserIndexList = new ArrayList<>();

        notDuplicatedUserIndexList.add(0);

        // find the indexes of users having duplicated id
        for (int i = 1; i < catanUserList.size(); i++) {
            Long currentUserId = catanUserList.get(i).getId();
            for (int j = i - 1; j < i; j++) {
                // if it is duplicated
                if (Objects.equals(currentUserId, catanUserList.get(j).getId())) {
                    duplicatedUserIndexList.add(i);
                }
                // if it is not duplicated
                else {
                    notDuplicatedUserIndexList.add(i);
                }
            }
        }

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("duplicatedUserIndexList", duplicatedUserIndexList);
        map.put("notDuplicatedUserIndexList", notDuplicatedUserIndexList);

        return map;
    }

    private void mockDatabaseServiceAddUser(CatanUser catanUserToBeAdded, List<CatanUser> usersInTheDatabase) {
        boolean doesEmailExistInUsers = doesEmailExistInUsers(usersInTheDatabase, catanUserToBeAdded.getEmail());

        if (doesEmailExistInUsers) {
            when(databaseService.addUser(catanUserToBeAdded)).thenReturn(false);
        }
        else {
            when(databaseService.addUser(catanUserToBeAdded)).thenReturn(true);
        }
    }

    private void mockDatabaseServiceDeleteUser(String emailOfTheUserToBeDeleted, List<CatanUser> usersInTheDatabase) {
        boolean doesEmailExistInUsers = doesEmailExistInUsers(usersInTheDatabase, emailOfTheUserToBeDeleted);

        if (doesEmailExistInUsers) {
            when(databaseService.deleteUserByEmail(emailOfTheUserToBeDeleted)).thenReturn(true);
        }
        else {
            when(databaseService.deleteUserByEmail(emailOfTheUserToBeDeleted)).thenReturn(false);
        }
    }

    private boolean doesEmailExistInUsers(List<CatanUser> catanUserList, String email) {
		for (CatanUser catanUser : catanUserList) {
			if (email.equals(catanUser.getEmail())) {
				return true;
			}
		}
        return false;
    }

}