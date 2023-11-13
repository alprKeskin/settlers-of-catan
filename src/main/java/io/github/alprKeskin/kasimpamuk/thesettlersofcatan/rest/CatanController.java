/***
 * This controller stands for only service availability check for now.
 * It will be converted to a controller to be used in main logic.
 */
package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catan")
@CrossOrigin
public class CatanController {

    /***
     * Checks if the service is available.
     * It will be converted to main page endpoint in the upcoming phases
     * @return: A message indicating that the service is available.
     */
    @GetMapping("/server-control")
    public ResponseEntity<String> serverControl() {
        return ResponseEntity.ok("The server is up!");
    }

}
