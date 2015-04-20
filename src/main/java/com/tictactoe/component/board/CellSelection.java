package com.tictactoe.component.board;

import javafx.scene.image.Image;

public enum CellSelection {
	X("images/x_image.png"),
	O("images/o_image.png"),
	None("images/no_image.png");
	
	CellSelection(String filePath) {
		image = new Image(getClass().getClassLoader().getResourceAsStream(filePath));
	}
	
	private Image image;
	
	public Image getImage() {
		return image;
	}
}
