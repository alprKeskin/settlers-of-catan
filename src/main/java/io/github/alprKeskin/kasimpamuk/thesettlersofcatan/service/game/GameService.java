package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization.GameInitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

	private final List<Game> games;

	private final GameInitializationService gameInitializationService;

	@Autowired
	public GameService(GameInitializationService gameInitializationService) {
		this.gameInitializationService = gameInitializationService;
		this.games = new ArrayList<>();
	}

	public int createNewGame() {
		Game newGame = this.gameInitializationService.createNewGame(createGameId());
		this.games.add(newGame);
		return newGame.getGameId();
	}

	public Player addNewPlayerToGame(int gameId) {
		int newPlayerId = this.games.size();
		Player newPlayer = this.gameInitializationService.createNewPlayer(newPlayerId, gameId);
		this.games.get(gameId).getPlayers().add(newPlayer);
		return newPlayer;
	}

	private int createGameId() {
		return this.games.size();
	}

}
