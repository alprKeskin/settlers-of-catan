package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.GameState;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameInitializationService {

	private final TileMapInfoInitializationService tileMapInfoInitializationService;
	private final PlayerService playerService;

	@Autowired
	public GameInitializationService(TileMapInfoInitializationService tileMapInfoInitializationService, PlayerService playerService) {
		this.tileMapInfoInitializationService = tileMapInfoInitializationService;
		this.playerService = playerService;
	}

	public Player addNewPlayer(List<Game> games) {
		Player newPlayer;
		int availableGameId = findAGameHavingSlot(games);

		// if no empty game exists
		if (availableGameId == -1) {
			Game newGame = this.createNewGame(getNextGameId(games));
			newPlayer = this.playerService.createNewPlayer(newGame.getGameId(), 0);
			newGame.addPlayer(newPlayer);
			games.add(newGame);
		}
		// if there is an empty game
		else {
			int emptySlotIndexOfGame = findEmptySlotIndexOfGame(games, availableGameId);
			newPlayer = this.playerService.createNewPlayer(availableGameId, emptySlotIndexOfGame);
			games.get(availableGameId).addPlayer(newPlayer);
		}
		return newPlayer;
	}

	private Game createNewGame(int id) {
		return new Game(id, GameState.WAITING_FOR_PLAYERS, 0, new ArrayList<>(), tileMapInfoInitializationService.createTileInfos(), null);
	}

	private int findAGameHavingSlot(final List<Game> games) {
		for (Game game : games) {
			if (game.getGameState() == GameState.WAITING_FOR_PLAYERS) return game.getGameId();
		}
		return -1;
	}

	private int findEmptySlotIndexOfGame(final List<Game> games, int gameId) {
		return games.get(gameId).getPlayers().size();
	}

	private int getNextGameId(final List<Game> games) {
		return games.size();
	}

}
