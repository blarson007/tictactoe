package com.tictactoe.component.game;

import java.util.Set;

import com.tictactoe.component.board.BoardCell;
import com.tictactoe.component.board.Sequence;

public class TurnStatus {

	private GameState gameState = GameState.Incomplete;
	private Sequence sequence;
	private Set<BoardCell> boardCells;
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public GameState getGameState() {
		return gameState;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public Set<BoardCell> getBoardCells() {
		return boardCells;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
	
	public void setBoardCells(Set<BoardCell> boardCells) {
		this.boardCells = boardCells;
	}
}
