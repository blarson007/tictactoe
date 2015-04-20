package com.tictactoe.frontend;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.tictactoe.component.game.GameDifficulty;
import com.tictactoe.component.game.GameSettings;
import com.tictactoe.component.game.PlayerType;

public class TitleScreen extends Application {
	
	private PlayerType opponentType = null;
	private GameDifficulty gameDifficulty = null;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = new GridPane();
		
		Scene scene = new Scene(root, 230, 100);
		scene.getStylesheets().add("styles/board.css");
		
		final Label difficultyLabel = new Label("Difficulty:");
		
		final ComboBox<GameDifficulty> difficultyComboBox = new ComboBox<GameDifficulty>();
		difficultyComboBox.getItems().addAll(GameDifficulty.values());
		difficultyComboBox.valueProperty().addListener(new ChangeListener<GameDifficulty>() {
			@Override
			public void changed(ObservableValue<? extends GameDifficulty> observable, GameDifficulty oldValue, GameDifficulty newValue) {
				gameDifficulty = newValue;
			}
		});
		
		final ComboBox<PlayerType> opponentComboBox = new ComboBox<PlayerType>();
		opponentComboBox.getItems().addAll(PlayerType.values());
		opponentComboBox.valueProperty().addListener(new ChangeListener<PlayerType>() {
			@Override
			public void changed(ObservableValue<? extends PlayerType> observable, PlayerType oldValue, PlayerType newValue) {
				opponentType = newValue;
				showHideGameDifficulty(newValue, root, difficultyLabel, difficultyComboBox);
			}    
	    });
		
		root.setVgap(4);
		root.setHgap(10);
		root.setPadding(new Insets(5, 5, 5, 5));
		root.add(new Label("Opponent type:"), 0, 0);
		root.add(opponentComboBox, 0, 1);
		
		Button button = new Button("Start!");
		button.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	// TODO: Validate that opponent type and possibly game difficulty have been set
		    	GameSettings.init(opponentType, gameDifficulty);
				new BoardView().start(stage);
		    }
		});
		
		root.add(button, 0, 2);
		
		stage.setTitle("Tic Tac Toe");
		stage.setScene(scene);
		stage.show();
	}
	
	private void showHideGameDifficulty(PlayerType newValue, GridPane root, Label difficultyLabel, ComboBox<GameDifficulty> difficultyComboBox) {
		if (newValue == PlayerType.Computer) {
			root.add(difficultyLabel, 1, 0);
			root.add(difficultyComboBox, 1, 1);
		} else {
			root.getChildren().remove(difficultyLabel);
			root.getChildren().remove(difficultyComboBox);
		}
	}
}
