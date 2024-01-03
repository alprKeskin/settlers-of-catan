package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Game {

	private int gameId;
	private List<Player> players;
	private List<TileInfo> tileInfos;

}
