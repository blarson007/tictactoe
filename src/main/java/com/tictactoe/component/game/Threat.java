package com.tictactoe.component.game;

import java.util.HashSet;
import java.util.Set;

import com.tictactoe.component.board.BoardCell;
import com.tictactoe.component.board.CellSelection;

public class Threat {

	private Set<BoardCell> occupiedCells = new HashSet<BoardCell>();
	private BoardCell targetCell;
	
	public Threat(Set<BoardCell> threeCells, CellSelection threatType) {
		for (BoardCell cell : threeCells) {
			if (cell.getSelection() == threatType) {
				occupiedCells.add(cell);
			} else {
				targetCell = cell;
			}
		}
	}
	
	public Set<BoardCell> getOccupiedCells() {
		return occupiedCells;
	}
	
	public BoardCell getTargetCell() {
		return targetCell;
	}
}
