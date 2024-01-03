package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameInitializationService {

	private final TileMapInfoInitializationService tileMapInfoInitializationService;
	private final PlayerService playerService;

	@Autowired
	public GameInitializationService(TileMapInfoInitializationService tileMapInfoInitializationService, PlayerService playerService) {
		this.tileMapInfoInitializationService = tileMapInfoInitializationService;
		this.playerService = playerService;
	}

	public Game createNewGame(int id) {
		return new Game(id, null, tileMapInfoInitializationService.createTileInfos());
	}

	public Player createNewPlayer(int playerId, int gameId) {
		return this.playerService.createNewPlayer(playerId, gameId);
	}

}
