package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.game.initialization;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.TileInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.TerrainType;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util.GameInitializationUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TileMapInfoInitializationService {

	public List<TileInfo> createTileInfos() {
		List<TileInfo> tileInfos = new ArrayList<>();

		ArrayList<Integer> tileIds = GameInitializationUtil.getTileIds();
		ArrayList<Integer> tileNumbers = GameInitializationUtil.createNumberList();
		ArrayList<TerrainType> terrainTypes = GameInitializationUtil.createTerrainList();

		for (int i = 0; i < 18; i++) {
			tileInfos.add(new TileInfo(tileIds.get(i), tileNumbers.get(i), terrainTypes.get(i)));
		}

		return tileInfos;
	}

}
