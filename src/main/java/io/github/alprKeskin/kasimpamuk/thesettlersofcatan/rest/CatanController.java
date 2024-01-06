package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.rest;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.TileInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request.RequestDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.InitialResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.*;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.GameManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catan")
@CrossOrigin
public class CatanController {

    private final GameManagerService gameManagerService;

    @Autowired
    public CatanController(GameManagerService gameManagerService) {
        this.gameManagerService = gameManagerService;
    }

    /***
     * Checks if the service is available.
     * It will be converted to main page endpoint in the upcoming phases
     * @return: A message indicating that the service is available.
     */
    @GetMapping("/server-control")
    public ResponseEntity<String> serverControl() {
        return ResponseEntity.ok("The server is up!");
    }

    @GetMapping("/initial-game-data")
    public ResponseEntity<InitialResponseDTO> initialGameData() {
        Player addedPlayer = this.gameManagerService.addNewPlayer();

        InitialResponseDTO initialResponseDTO = new InitialResponseDTO(
                addedPlayer.getGameId(),
                addedPlayer.getPlayerId(),
                addedPlayer.getColor(),
                this.gameManagerService.getGame(addedPlayer.getGameId()).getTileInfos()
        );
        return ResponseEntity.ok(initialResponseDTO);
    }

    @PostMapping("/get-game-data")
    public ResponseEntity<ResponseDTO> getGameData(@RequestBody RequestDTO requestDTO) {
        RequestType requestType = requestDTO.getRequestType();
        int gameId = requestDTO.getGameId();
        int playerId = requestDTO.getPlayerActionInfo().getPlayerId();
        if (requestType == RequestType.GET_INFO) {
            return ResponseEntity.ok(this.gameManagerService.getGameInfoForPlayer(gameId, playerId));
        }
        else if (requestType == RequestType.TURN_ROUND) {
            return ResponseEntity.ok(this.gameManagerService.turnRound(requestDTO));
        }
        return null; // TODO: code...
    }

    @GetMapping("/test-get")
    public ResponseEntity<InitialResponseDTO> testGet() {
        InitialResponseDTO initialResponseDTO = new InitialResponseDTO(
                12,
                55,
                Color.YELLOW,
                List.of(new TileInfo(1, 10, TerrainType.HILL), new TileInfo(2, 20, TerrainType.FOREST))
        );

        return ResponseEntity.ok(initialResponseDTO);
    }

    @PostMapping("/test-post")
    public ResponseEntity<ResponseDTO> testPost(@RequestBody RequestDTO requestDTO) {
        System.out.println("Incoming RequestDTO: " + requestDTO.toString());

        ResponseDTO responseDTO = new ResponseDTO(
                ResponseType.WAIT,
                new PlayerActionInfo(1, Color.RED, 5, 6, List.of(1, 2), List.of(3, 4, 5))
        );


        return ResponseEntity.ok(responseDTO);
    }

}
