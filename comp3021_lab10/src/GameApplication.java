

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class GameApplication extends Application {

	// ====
	// Section: Attributes of GameApplication
	// ====

	// Attributes: Scene and Stage
	private static final int SCENE_NUM = 4;
	private static final int SCENE_WELCOME = 0;
	private static final int SCENE_STARTGAME = 1;
	private static final int SCENE_GAMEPLAY = 2;
	private static final int SCENE_GAMEOVER = 3;
	private static final String[] SCENE_TITLES = { "Welcome", "Start Game", "Game Play", "Game Over" };
	private Stage stage = null;
	private Scene[] scenes = null;


	// TODO2: Create a pane for "Welcome"
	// You need to have BorderPane, VBox, Labal and 3 Buttons
	private Pane paneWelcome() {
		BorderPane pane = new BorderPane();
		VBox container = new VBox(20);

		Label lbMenuTitle = new Label("Java Warfare Game");
		Button btNewGame = new Button("New Game");
		Button btQuit = new Button("Quit");
		Button btBackgroundMusic = new Button("Enable Background Music");

		lbMenuTitle.getStyleClass().add("menu-title");
		btNewGame.getStyleClass().add("menu-button");
		btQuit.getStyleClass().add("menu-button");
		btBackgroundMusic.getStyleClass().add("menu-button");

		container.getChildren().addAll(lbMenuTitle, btNewGame, btBackgroundMusic, btQuit);
		container.setAlignment(Pos.CENTER);
		pane.setCenter(container);
		return pane;
	}

	// TODO3: Create a pane for "GameStart"
	// You need to have a BorderPane with one VBox on the left and one VBox on the center
	// In VBox on the left, 3 Buttons, 1 Label, 1 ListView<String> are required
	// In VBox on the center, 1 Canvas is required
	// Suggestion: Set the size of ListView to width:150 height:200
	// Suggestion: Set the size of Canvas to width:640 height:480
	private Pane paneStartGame() {
		BorderPane pane = new BorderPane();
		VBox leftContainer = new VBox(20);
		leftContainer.setPadding(new Insets(10, 10, 10, 10));
		VBox rightContainer = new VBox(20);
		rightContainer.setPadding(new Insets(10, 10, 10, 10));

		Button btLoadMap = new Button("Load Map");
		Button btLoadUnits = new Button("Load Units");
		Label lbUnits = new Label("Units");
		ListView<String> lvUnits = new ListView<String>();
		lvUnits.setPrefSize(150, 200);
		Button btStartGame = new Button("Start Game");

		btLoadMap.getStyleClass().add("menu-button");
		btLoadUnits.getStyleClass().add("menu-button");
		btStartGame.getStyleClass().add("menu-button");

		leftContainer.getChildren().addAll(btLoadMap, btLoadUnits, lbUnits, lvUnits, btStartGame);
		leftContainer.setAlignment(Pos.CENTER);

		Canvas cvGame = new Canvas();
		cvGame.setHeight(480);
		cvGame.setWidth(640);
		rightContainer.getChildren().add(cvGame);
		rightContainer.setAlignment(Pos.CENTER);

		pane.setLeft(leftContainer);
		pane.setRight(rightContainer);
		return pane;
	}
 
	// TODO4: Create a pane for "GamePlay"
	// You need to have a BorderPane, Canvas, Label of Current Turn, Label of GamePlay Info and a button
	// Label of Current Turn is on top of the other Label, it shows the current turn (e.g. Red/Blue)
	// Label of GamePlayInfo shows the property of the unit
	// The content of the Label will be updated later
	// Set the content of the Label to an empty string for now
	private Pane paneGamePlay() {
		BorderPane pane = new BorderPane();
		VBox container = new VBox(20);

		Canvas cvPlay = new Canvas();
		cvPlay.setHeight(480);
		cvPlay.setWidth(640);
		Label lbCurrentTurn = new Label("");
		Label lbUnitProperty = new Label("");
		Button btQuitGame = new Button("Quit Game");

		container.getChildren().addAll(cvPlay, lbCurrentTurn, lbUnitProperty, btQuitGame);
		container.setAlignment(Pos.CENTER);
		pane.setCenter(container);

		return pane;
	}

	// TODO5: Create a pane for "GameOver"
	// You need to have a BorderPane, VBox, 1 Label and 2 Buttons
	// Initiate the Label with empty string (i.e. "") for this moment
	// It is used to display who is the winner later
	private Pane paneGameOver() {
		BorderPane pane = new BorderPane();
		VBox container = new VBox(20);

		Label lbGameWinner = new Label("");
		Button btExitToMenu = new Button("Exit to Menu");
		Button btQuitGame = new Button("Quit Game");

		container.getChildren().addAll(lbGameWinner, btExitToMenu, btQuitGame);
		container.setAlignment(Pos.CENTER);
		pane.setCenter(container);

		return pane;
	}


	/**
	 * This method will be used to create the UI components for this application
	 */
	private void initScenes() {

		scenes = new Scene[SCENE_NUM];
		scenes[SCENE_WELCOME] = new Scene(paneWelcome(), 400, 500);
		scenes[SCENE_STARTGAME] = new Scene(paneStartGame(), 1000, 600);
		scenes[SCENE_GAMEPLAY] = new Scene(paneGamePlay(), 800, 600);
		scenes[SCENE_GAMEOVER] = new Scene(paneGameOver(), 800, 600);

		for (Scene s : scenes) {
			if (s != null)
				s.getStylesheets().add("styles.css"); // share stylesheet for all scenes
		}

	}

	
	/**
	 * This method is used to pick anyone of the scene on the stage. It handles the
	 * hide and show order. In this application, only one active scene should be
	 * displayed on stage.
	 * 
	 * @param sceneID
	 *            - The sceneID defined above (see SCENE_XXX)
	 */
	private void putSceneOnStage(int sceneID) {

		// ensure the sceneID is valid
		if (sceneID < 0 || sceneID >= SCENE_NUM)
			return;

		stage.hide();
		// TODO6: 
		stage.setScene(scenes[sceneID]);
		stage.setTitle(SCENE_TITLES[sceneID]);
		stage.show();
	}

	@Override
	/**
	 * The entry point for this JavaFx application. It handles the scene creation.
	 * Once all scenes are created, a welcome scene will be displayed on the stage.
	 */
	public void start(Stage primaryStage) {
		try {
			// initialize attributes
			stage = primaryStage;
			initScenes();
			putSceneOnStage(SCENE_GAMEOVER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO1: Define a main method and launch the application
	public static void main (String[] args) {
		launch(args);
	}



}

