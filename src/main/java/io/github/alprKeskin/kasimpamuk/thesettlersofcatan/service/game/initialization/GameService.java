package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.GameState;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.ResponseType;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	private final PlayerService playerService;

	public GameService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public ResponseDTO getGameInfoForPlayer(Game game, int playerId) {
		ResponseDTO responseDTO;

		if (game.getGameState() == GameState.WAITING_FOR_PLAYERS) {
			responseDTO = new ResponseDTO(ResponseType.WAIT, game.getLastAction());
		}
		else if (game.getGameState() == GameState.INITIALIZATION) {
			Color colorOfThePlayer = this.playerService.getPlayerColor(game, playerId);
			if (colorOfThePlayer == Color.YELLOW) {
				game.setGameState(GameState.STARTED);
				responseDTO = new ResponseDTO(ResponseType.YOUR_TURN, game.getLastAction());
			}
			else {
				responseDTO = new ResponseDTO(ResponseType.WAIT, game.getLastAction());
			}
		}
		// Game State = STARTED
		else {
			// check if it is the turn of this player
			if (game.getPlayerIdHavingTurn() == playerId) {
				responseDTO = new ResponseDTO(ResponseType.YOUR_TURN, game.getLastAction());
			}
			else {
				responseDTO = new ResponseDTO(ResponseType.WAIT, game.getLastAction());
			}
		}
		return responseDTO;
	}

	public ResponseDTO turnRoundForGame(Game game, PlayerActionInfo playerActionInfo) {
		ResponseDTO responseDTO;
		throwExceptionIfGameHasNotStarted(game.getGameState());
		game.setLastAction(playerActionInfo);
		game.giveRoundToNextPlayer();
		responseDTO = new ResponseDTO(ResponseType.WAIT, game.getLastAction());
		return responseDTO;
	}

	private void throwExceptionIfGameHasNotStarted(GameState gameState) {
		if (gameState == GameState.WAITING_FOR_PLAYERS) throw new RuntimeException("Game is waiting for players but turn round request came!");
		else if (gameState == GameState.INITIALIZATION) throw new RuntimeException("Game is in initialization phase but turn round request came!");
		return;
	}

}
