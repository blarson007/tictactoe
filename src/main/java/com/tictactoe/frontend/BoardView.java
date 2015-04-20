package com.tictactoe.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.tictactoe.component.board.Board;

public class BoardView extends Application {
	
	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();
		Board board = new Board();
		
		board.initializeBoard(root);
		
		Scene scene = new Scene(root, 158, 158);
		scene.getStylesheets().add("styles/board.css");
		
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
	}
}
