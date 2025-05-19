package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.userdatabase;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserManagerService {

    private final DatabaseService databaseService;

    @Autowired
    public UserManagerService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public CatanUser getUserByEmail(String email) {
        Optional<CatanUser> optionalUser = databaseService.getUserByEmail(email);
		return optionalUser.orElse(null);
	}

    public List<CatanUser> getAllUsers() {
        return databaseService.getAllUsers();
    }

    public boolean addUser(CatanUser catanUser) {
        return databaseService.addUser(catanUser);
    }

    public List<CatanUser> addMultipleUsers(List<CatanUser> catanUserList) {
        List<CatanUser> unsuccessfulAdditions = new ArrayList<>();
        for (int i = 0; i < catanUserList.size(); i++) {
            if (!databaseService.addUser(catanUserList.get(i))) {
                unsuccessfulAdditions.add(catanUserList.get(i));
            }
        }
        return unsuccessfulAdditions;
    }

    public boolean deleteUserByEmail(String email) {
        return databaseService.deleteUserByEmail(email);
    }

    private boolean isProperEMail(String email) {
        List<String> acceptableEmailSuffixes = new ArrayList<>();
        acceptableEmailSuffixes.add(".com");

        for (int i = 0; i < acceptableEmailSuffixes.size(); i++) {
            if (email.endsWith(acceptableEmailSuffixes.get(i))) {
                return true;
            }
        }
        return false;
    }

}
