package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request.RequestDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.GameState;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.ResponseType;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization.GameInitializationService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameManagerService {

	private final List<Game> games;

	private final GameInitializationService gameInitializationService;
	private final PlayerService playerService;

	private final GameService gameService;

	@Autowired
	public GameManagerService(GameInitializationService gameInitializationService, PlayerService playerService, GameService gameService) {
		this.gameInitializationService = gameInitializationService;
		this.playerService = playerService;
		this.gameService = gameService;
		this.games = new ArrayList<>();
	}

	public Game getGame(int gameId) {
		return this.games.get(gameId);
	}

	public Player addNewPlayer() {
		return this.gameInitializationService.addNewPlayer(this.games);
	}

	public ResponseDTO getGameInfoForPlayer(int gameId, int playerId) {
		return this.gameService.getGameInfoForPlayer(this.games.get(gameId), playerId);
	}

	public ResponseDTO turnRound(RequestDTO requestDTO) {
		Game game = this.games.get(requestDTO.getGameId());
		System.out.println("game.getPlayerIdHavingTurn(): " + game.getPlayerIdHavingTurn());
		System.out.println("requestDTO.getPlayerActionInfo().getPlayerId(): " + requestDTO.getPlayerActionInfo().getPlayerId());
		if (game.getPlayerIdHavingTurn() != requestDTO.getPlayerActionInfo().getPlayerId()) {
			throw new RuntimeException("Some player who has not the turn send a turn round request");
		}
		return this.gameService.turnRoundForGame(game, requestDTO.getPlayerActionInfo());
	}

}
