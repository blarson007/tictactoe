package com.tictactoe.component.game;

public class TurnManager {

	private static int currentTurn = 1;
	
	public static synchronized int getCurrentTurn() {
		return currentTurn;
	}
	
	public static synchronized void incrementTurn() {
		currentTurn++;
	}
}
