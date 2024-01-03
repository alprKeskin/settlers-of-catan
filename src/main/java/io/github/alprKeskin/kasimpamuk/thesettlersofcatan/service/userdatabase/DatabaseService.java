package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.userdatabase;


import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.repository.CatanUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DatabaseService {

    private final CatanUserRepository catanUserRepository;

    @Autowired
    public DatabaseService(CatanUserRepository catanUserRepository) {
        this.catanUserRepository = catanUserRepository;
    }

    public Optional<CatanUser> getUserByEmail(String email) {
        return catanUserRepository.findByEmail(email);
    }

    public List<CatanUser> getAllUsers() {
        return catanUserRepository.findAll();
    }

    public boolean addUser(CatanUser catanUser) {
        if (catanUserRepository.existsByEmail(catanUser.getEmail())) {
            log.error("A user with the same email exist in database.");
            return false;
        }
        catanUserRepository.save(catanUser);
        return true;
    }

    public boolean deleteUserByEmail(String email) {
        if (!catanUserRepository.existsByEmail(email)) {
            return false;
        }
        catanUserRepository.deleteByEmail(email);
        return true;
    }

}
