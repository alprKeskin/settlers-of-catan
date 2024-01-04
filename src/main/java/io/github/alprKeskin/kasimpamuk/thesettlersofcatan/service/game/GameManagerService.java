package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.GameState;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization.GameInitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameManagerService {

	private final List<Game> games;

	private final GameInitializationService gameInitializationService;
	private final PlayerService playerService;

	@Autowired
	public GameManagerService(GameInitializationService gameInitializationService, PlayerService playerService) {
		this.gameInitializationService = gameInitializationService;
		this.playerService = playerService;
		this.games = new ArrayList<>();
	}

	public Game getGame(int gameId) {
		return this.games.get(gameId);
	}

	public Player addNewPlayer() {
		Player newPlayer;
		int availableGameId = findAGameHavingSlot();

		// if no empty game exists
		if (availableGameId == -1) {
			Game newGame = this.gameInitializationService.createNewGame(getNextGameId());
			newPlayer = this.playerService.createNewPlayer(newGame.getGameId(), 0);
			newGame.addPlayer(newPlayer);
			this.games.add(newGame);
		}
		// if there is an empty game
		else {
			int emptySlotIndexOfGame = findEmptySlotIndexOfGame(availableGameId);
			newPlayer = this.playerService.createNewPlayer(availableGameId, emptySlotIndexOfGame);
			this.games.get(availableGameId).addPlayer(newPlayer);
		}
		return newPlayer;
	}

	private int getNextGameId() {
		return this.games.size();
	}

	private int findAGameHavingSlot() {
		for (Game game : this.games) {
			if (game.getGameState() == GameState.WAITING_FOR_PLAYERS) return game.getGameId();
		}
		return -1;
	}

	private int findEmptySlotIndexOfGame(int gameId) {
		return this.games.get(gameId).getPlayers().size();
	}

}
