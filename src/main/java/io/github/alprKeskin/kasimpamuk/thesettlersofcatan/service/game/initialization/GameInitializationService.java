package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameInitializationService {

	private final TileMapInfoInitializationService tileMapInfoInitializationService;

	@Autowired
	public GameInitializationService(TileMapInfoInitializationService tileMapInfoInitializationService) {
		this.tileMapInfoInitializationService = tileMapInfoInitializationService;
	}

	public Game createNewGame(int id) {
		return new Game(id, GameState.WAITING_FOR_PLAYERS, new ArrayList<>(), tileMapInfoInitializationService.createTileInfos());
	}

}
