package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.TerrainType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.TerrainType.*;

public class GameInitializationUtil {

	public static ArrayList<Integer> getTileIds() {
		return new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18));
	}

	public static ArrayList<Integer> createNumberList() {
		ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(2, 12));
		numberList.addAll(List.of(3, 4, 5, 6, 8, 9, 10, 11));
		numberList.addAll(List.of(3, 4, 5, 6, 8, 9, 10, 11));

		Collections.shuffle(numberList);
		return numberList;
	}

	public static ArrayList<TerrainType> createTerrainList() {
		ArrayList<TerrainType> terrains = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			terrains.add(HILL);
			terrains.add(MOUNTAIN);
		}
		for (int i = 0; i < 4; i++) {
			terrains.add(FOREST);
			terrains.add(FIELD);
			terrains.add(PASTURE);
		}
		Collections.shuffle(terrains);
		return terrains;
	}



}
