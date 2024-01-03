package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.TerrainType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameInitializationUtilTest {

	private final int tileNumber = 18;

	@Test
	void getIds() {
		ArrayList<Integer> ids = GameInitializationUtil.getTileIds();
		assertEquals(tileNumber, ids.size());
	}

	@Test
	void createNumberList() {
		ArrayList<Integer> numberList = GameInitializationUtil.createNumberList();
		assertEquals(tileNumber, numberList.size());
	}

	@Test
	void createTerrainList() {
		ArrayList<TerrainType> terrainTypes = GameInitializationUtil.createTerrainList();
		assertEquals(tileNumber, terrainTypes.size());
	}

}