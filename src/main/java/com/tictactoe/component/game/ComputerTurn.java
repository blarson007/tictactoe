package com.tictactoe.component.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tictactoe.component.board.Board;
import com.tictactoe.component.board.BoardCell;
import com.tictactoe.component.board.CellSelection;
import com.tictactoe.component.board.Sequence;

public class ComputerTurn {

	private Board board;
	private BoardCell previousMove;
	private int currentTurn;
	
	public ComputerTurn(Board board, BoardCell previousMove, int currentTurn) {
		this.board = board;
		this.previousMove = previousMove;
		this.currentTurn = currentTurn;
	}
	
	public void playTurn() {
		if (isOpeningMove()) {
			boolean played = playCenterSquareIfAvailable();
			
			if (!played) {
				// If the first move for X was in the center, let's play any corner square
				playAnyCornerSquare();
			}
		} else {
			List<Threat> threats = findThreats();
			
			if (threats.isEmpty()) {
				// TODO: This isn't a good strategy for winning, but it's a start
				// TODO: Play ahead one move (one X, one Y) to see if a threat exists either for the human or computer next turn
				boolean played = playAnyCornerSquare();
				
				if (!played) {
					playAnySideSquare();
				}
			} else {
				threats.get(0).getTargetCell().play(currentTurn);
			}
		}
		
		TurnManager.incrementTurn();
	}
	
	private boolean isOpeningMove() {
		return currentTurn < 3;
	}
	
	/**
	 * A defensive tactic by the computer would be to play the center
	 * square if it is available
	 * 
	 * @return True if we were able to play in the center square, otherwise false
	 */
	private boolean playCenterSquareIfAvailable() {
		BoardCell boardCell = board.getCenterSquare();
		
		if (boardCell.play(currentTurn)) {
			return true;
		}
		return false;
	}
	
	private boolean playAnyCornerSquare() {
		List<BoardCell> cells = board.getCornersRandomized();
		
		for (BoardCell boardCell : cells) {
			if (boardCell.play(currentTurn)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean playAnySideSquare() {
		List<BoardCell> cells = board.getSidesRandomized();
		
		for (BoardCell boardCell : cells) {
			if (boardCell.play(currentTurn)) {
				return true;
			}
		}
		
		return false;
	}
	
	private List<Threat> findThreats() {
		
		List<Threat> threats = new ArrayList<Threat>();
		
		for (Sequence sequence : Sequence.values()) {
			Set<BoardCell> cells = board.getCellsAt(previousMove.getPosition(), sequence);
		
			// Look for two cells with the same selection out of three.
			// As long as the third one is a blank cell
			int xCount = 0;
			int oCount = 0;
			
			for (BoardCell cell : cells) {
				if (cell.getSelection() == CellSelection.O) {
					oCount++;
				} else if (cell.getSelection() == CellSelection.X) {
					xCount++;
				}
			}
			
			if (xCount > 0 && oCount > 0) {
				// Both X and O are represented.  Can't be a threat.
				continue;
			} else if (xCount == 2 || oCount == 2) {
				CellSelection threatType = xCount == 2 ? CellSelection.X : CellSelection.O;
				threats.add(new Threat(cells, threatType));
			}
		}	
		
		return threats;
	}
	
	private Board playAheadOneTurn(Board currentBoard, int turnNumber, BoardCell currentAttempt) {
		Board board = currentBoard.getCopy();
		
		BoardCell move = currentAttempt.getCopy();
		move.play(turnNumber);
		
		return board;
	}
}
