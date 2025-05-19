package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Game;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

	public Player createNewPlayer(int gameId, int slotIndex) {
		int playerId = calculatePlayerId(gameId, slotIndex);
		return new Player(gameId, playerId);
	}

	public Color getPlayerColor(Game game, int playerId) {
		List<Player> players = game.getPlayers();
		for (Player player : players) {
			if (playerId == player.getPlayerId()) return player.getColor();
		}
		throw new RuntimeException("No player with the given id exists in the given game!");
	}

	private int calculatePlayerId(int gameId, int slotIndex) {
		return 4 * gameId + slotIndex;
	}

}
