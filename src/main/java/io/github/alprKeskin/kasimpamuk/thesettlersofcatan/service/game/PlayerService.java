package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

	public Player createNewPlayer(int gameId, int slotIndex) {
		int playerId = calculatePlayerId(gameId, slotIndex);
		return new Player(gameId, playerId);
	}

	private int calculatePlayerId(int gameId, int slotIndex) {
		return 4 * gameId + slotIndex;
	}

}
