package com.tictactoe.event;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import com.tictactoe.component.board.Board;
import com.tictactoe.component.board.BoardCell;
import com.tictactoe.component.board.CellSelection;
import com.tictactoe.component.game.ComputerTurn;
import com.tictactoe.component.game.GameSettings;
import com.tictactoe.component.game.GameState;
import com.tictactoe.component.game.PlayerType;
import com.tictactoe.component.game.TurnManager;
import com.tictactoe.component.game.TurnStatus;

public class BoardClick implements EventHandler<MouseEvent> {

	private Board board;
	
	public BoardClick(Board board) {
		this.board = board;
	}
	
	@Override
	public void handle(MouseEvent event) {
		BoardCell boardCell = (BoardCell) event.getSource();
		if (boardCell.getSelection() == CellSelection.None) {
			int currentTurn = TurnManager.getCurrentTurn();
			
			boardCell.play(currentTurn);
			
			TurnStatus turnStatus = board.getTurnStatus(boardCell.getPosition(), boardCell.getSelection());
			if (turnStatus.getGameState() == GameState.Incomplete) {
				TurnManager.incrementTurn();
				
				if (GameSettings.getPlayerType() == PlayerType.Computer) {
					ComputerTurn computerTurn = new ComputerTurn(board, boardCell, TurnManager.getCurrentTurn());
					
					computerTurn.playTurn();
				}
			} else {
				// Turn off event handler on all cells.  The game is over.
				System.out.println("Game over. Game state is " + turnStatus.getGameState());
				board.disableMouseClick();
			}
		}
	}
}
