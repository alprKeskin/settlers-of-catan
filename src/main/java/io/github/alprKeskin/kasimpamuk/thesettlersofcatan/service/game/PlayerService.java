package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

	public Player createNewPlayer(int playerId, int gameId) {
		return new Player(playerId);
	}

}
