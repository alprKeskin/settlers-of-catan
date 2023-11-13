package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.User;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.repository.UserRepository;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CatanController {

    private final UserRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CatanController(UserRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;

    }

    @GetMapping("health")
    public String checkDatabaseConnection() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database connection is successful!";
        } catch (Exception e) {
            return "Database connection failed. Error: " + e.getMessage();
        }
    }
    @GetMapping("server-control")
    public ResponseEntity<String> serverControl() {
        return ResponseEntity.ok("The server is up!");
    }

    @GetMapping("/secured")
    public ResponseEntity<String> secured() {
        return ResponseEntity.ok("Hello from secured!");
    }


}

