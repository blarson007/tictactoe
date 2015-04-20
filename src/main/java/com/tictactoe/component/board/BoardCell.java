package com.tictactoe.component.board;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import com.tictactoe.event.BoardClick;

public class BoardCell extends StackPane {

	private CellSelection selection = CellSelection.None;
	private CellPosition position;
	
	// Need this so the click listener can be disabled
	private BoardClick boardClick;
	
	public BoardCell(CellPosition position) {
		this.position = position;
	}
	
	public void setSelection(CellSelection selection) {
		this.selection = selection;
	}

	public CellSelection getSelection() {
		return selection;
	}
	
	public CellPosition getPosition() {
		return position;
	}
	
	public void setBoardClick(BoardClick boardClick) {
		this.boardClick = boardClick;
	}
	
	public BoardClick getBoardClick() {
		return boardClick;
	}
	
	public boolean play(int currentTurn) {
		if (selection == CellSelection.None) {
			selection = currentTurn % 2 == 0 ? CellSelection.O : CellSelection.X;
			getChildren().remove(0);
			getChildren().add(new ImageView(selection.getImage()));
			
			return true;
		}
		
		return false;
	}
	
	public BoardCell getCopy() {
		BoardCell copyCell = new BoardCell(this.position);
		copyCell.setSelection(this.selection);
		return copyCell;
	}
}
