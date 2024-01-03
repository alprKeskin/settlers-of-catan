package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.authentication;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.dto.response.ErrorResponse;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.dto.response.LoginResponse;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@Autowired
	public LoginService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	public ResponseEntity<?> loginUser(String email, String password) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			String name = authentication.getName();
			CatanUser catanUser = new CatanUser(name,"", 0, 0, 0);
			String token = jwtUtil.createToken(catanUser);
			LoginResponse loginResponse = new LoginResponse(name,token);

			return ResponseEntity.ok(loginResponse);
		} catch (BadCredentialsException e){
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e){
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

}
