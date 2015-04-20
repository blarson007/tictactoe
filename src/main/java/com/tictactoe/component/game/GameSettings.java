package com.tictactoe.component.game;

public class GameSettings {

	private static PlayerType playerType;
	private static GameDifficulty gameDifficulty;
	
	private GameSettings() {  }
	
	public static void init(PlayerType playerTypeToSet, GameDifficulty gameDifficultyToSet) {
		playerType = playerTypeToSet;
		gameDifficulty = gameDifficultyToSet;
	}

	public static synchronized PlayerType getPlayerType() {
		return playerType;
	}

	public static synchronized GameDifficulty getGameDifficulty() {
		return gameDifficulty;
	}
}
