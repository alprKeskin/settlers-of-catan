package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CatanController {

    @GetMapping("server-control")
    public ResponseEntity<String> serverControl() {
        return ResponseEntity.ok("The server is up!");
    }

}
