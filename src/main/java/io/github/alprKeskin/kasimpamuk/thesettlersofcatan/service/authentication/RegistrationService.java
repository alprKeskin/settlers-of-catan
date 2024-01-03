package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

	private final DatabaseService databaseService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public RegistrationService(DatabaseService databaseService, PasswordEncoder passwordEncoder) {
		this.databaseService = databaseService;
		this.passwordEncoder = passwordEncoder;
	}

	public boolean registerUser(String email, String password) {
		return databaseService.addUser(createNewUser(email, password));
	}

	private CatanUser createNewUser(String email, String password) {
		return new CatanUser(email, passwordEncoder.encode(password), 0, 0, 0);
	}

}
