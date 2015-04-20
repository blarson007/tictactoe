package com.tictactoe.component.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import com.tictactoe.component.game.GameState;
import com.tictactoe.component.game.TurnStatus;
import com.tictactoe.event.BoardClick;

public class Board {

	private BoardCell[][] boardCells;
	
	public Board() {
		boardCells = new BoardCell[3][3];
	}
	
	public BoardCell[][] getBoardCells() {
		return boardCells;
	}
	
	public void initializeBoard(GridPane parentPane) {
		int size = 3;
		
		for (int row = 0; row < size; row++) {
		    for (int col = 0; col < size; col++) {
		    	BoardCell boardCell = new BoardCell(new CellPosition(col, row));
		    	
		    	boardCell.getChildren().add(new ImageView(boardCell.getSelection().getImage()));
		    	boardCell.setCenterShape(true);
		    	boardCell.getStyleClass().add(boardCell.getPosition().getStyleClass());
		    	
		    	BoardClick boardClick = new BoardClick(this);
		    	boardCell.addEventHandler(MouseEvent.MOUSE_CLICKED, boardClick);
		    	boardCell.setBoardClick(boardClick);
		    	
		    	boardCells[col][row] = boardCell;
		    	
		    	parentPane.add(boardCell, col, row);
		    }
		}
	}
	
	public TurnStatus getTurnStatus(CellPosition position, CellSelection selection) {
		TurnStatus turnStatus = new TurnStatus();
		
		// Test each direction
		for (Sequence sequence : Sequence.values()) {
			Set<BoardCell> cells = getCellsAt(position, sequence);
			
			if (cellsAreSameType(cells, selection)) {
				turnStatus.setBoardCells(cells);
				turnStatus.setGameState(selection == CellSelection.X ? GameState.X_Win : GameState.O_Win);
				turnStatus.setSequence(sequence);
				
				return turnStatus;
			}
		}
		
		if (isCatGame()) {
			turnStatus.setGameState(GameState.Cat_Game);
		} else {
			turnStatus.setGameState(GameState.Incomplete);
		}
		
		return turnStatus;
	}
	
	public void disableMouseClick() {
		int size = 3;
		
		for (int row = 0; row < size; row++) {
		    for (int col = 0; col < size; col++) {
		    	// Remove the click event handler
		    	boardCells[col][row].removeEventHandler(MouseEvent.MOUSE_CLICKED, boardCells[col][row].getBoardClick());
		    }
		}
	}
	
	public Set<BoardCell> getCellsAt(CellPosition position, Sequence sequence) {
		Set<BoardCell> cells = new HashSet<BoardCell>();
		if (sequence == Sequence.Horizontal) {
			int index = position.getyAxis();
			
			cells.add(boardCells[index][0]);
			cells.add(boardCells[index][1]);
			cells.add(boardCells[index][2]);
		} else if (sequence == Sequence.Vertical) {
			int index = position.getxAxis();
			
			cells.add(boardCells[0][index]);
			cells.add(boardCells[1][index]);
			cells.add(boardCells[2][index]);
		} else if (sequence == Sequence.DiaganolAscending) {
			if (position.fallsOnAscendingDiaganol()) {
				cells.add(boardCells[2][0]);
				cells.add(boardCells[1][1]);
				cells.add(boardCells[0][2]);
			}
		} else if (sequence == Sequence.DiaganolDescending) {
			if (position.fallsOnDescendingDiaganol()) {
				cells.add(boardCells[0][0]);
				cells.add(boardCells[1][1]);
				cells.add(boardCells[2][2]);
			}
		}
		return cells;
	}
	
	private boolean cellsAreSameType(Set<BoardCell> cells, CellSelection selection) {
		if (cells.size() < 3) {
			return false;
		}
		
		for (BoardCell cell : cells) {
			if (cell.getSelection() != selection) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isCatGame() {
		int size = 3;
		for (int row = 0; row < size; row++) {
		    for (int col = 0; col < size; col++) {
		    	if (boardCells[col][row].getSelection() == CellSelection.None) {
		    		return false;
		    	}
		    }
		}
		return true;
	}
	
	// TODO: All functions for getting squares, random squares should go in a util class
	public BoardCell getCenterSquare() {
		return boardCells[1][1];
	}
	
	public BoardCell getRandomCorner() {
		Map<Integer, BoardCell> cornerSquareMap = new HashMap<Integer, BoardCell>();
		
		int count = 1;
		for (BoardCell cell : getCornerSquares()) {
			cornerSquareMap.put(count++, cell);
		}
		
		int random = (int)(Math.random() * 4 + 1);
		
		return cornerSquareMap.get(random);
	}
	
	public List<BoardCell> getCornersRandomized() {
		List<BoardCell> cells = getCornerSquares();
		
		Collections.shuffle(cells);
		
		return cells;
	}
	
	public List<BoardCell> getSidesRandomized() {
		List<BoardCell> cells = getSideSquares();
		
		Collections.shuffle(cells);
		
		return cells;
	}
	
	private List<BoardCell> getCornerSquares() {
		List<BoardCell> cells = new ArrayList<BoardCell>();
		
		cells.add(boardCells[0][0]);
		cells.add(boardCells[0][2]);
		cells.add(boardCells[2][0]);
		cells.add(boardCells[2][2]);
		
		return cells;
	}
	
	private List<BoardCell> getSideSquares() {
		List<BoardCell> cells = new ArrayList<BoardCell>();
		
		cells.add(boardCells[0][1]);
		cells.add(boardCells[1][0]);
		cells.add(boardCells[2][1]);
		cells.add(boardCells[1][2]);
		
		return cells;
	}
	
	public Board getCopy() {
		Board newBoard = new Board();
		
		int size = 3;
		
		for (int row = 0; row < size; row++) {
		    for (int col = 0; col < size; col++) {
		    	newBoard.getBoardCells()[col][row] = boardCells[col][row];
		    }
		}
		
		return newBoard;
	}
}
