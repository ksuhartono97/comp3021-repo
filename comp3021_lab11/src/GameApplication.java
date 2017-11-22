

import javafx.application.Application;
import javafx.application.Platform;
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
import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import pa1.*;


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

    // TODO 2: Create a private GameEngine object
	private GameEngine gameEngine = new GameEngine();
    // TODO 3: Create a private GameMap object
	private GameMap gameMap;

	private Button btNewGame, btBackgroundMusic, btQuit;
	private Button btLoadMap, btLoadUnits, btStartGame;
	private Button btGamePlayQuitGame, btEndGame;
	private Button btExitToMenu, btGameOverQuitGame;
	private Canvas cvGameStart = new Canvas();
	private Canvas cvGamePlay = new Canvas();;
	private ObservableList<String> listViewUnitItems = FXCollections.observableArrayList();

    // ===
    // Section: content panes creation
    // ===

    // TODO 1a
    // Part 1: paneWelcome
	private Pane paneWelcome() {
		BorderPane pane = new BorderPane();
		VBox container = new VBox(20);

		Label lbMenuTitle = new Label("Java Warfare Game");
        btNewGame = new Button("New Game");
        btQuit = new Button("Quit");
        btBackgroundMusic = new Button("Enable Background Music");

		lbMenuTitle.getStyleClass().add("menu-title");
		btNewGame.getStyleClass().add("menu-button");
		btQuit.getStyleClass().add("menu-button");
		btBackgroundMusic.getStyleClass().add("menu-button");

		container.getChildren().addAll(lbMenuTitle, btNewGame, btBackgroundMusic, btQuit);
		container.setAlignment(Pos.CENTER);
		pane.setCenter(container);
		return pane;
	}

    // TODO 1b, TODO 2, TODO 3
    // Part 2: paneGameStart
	private Pane paneStartGame() {
		BorderPane pane = new BorderPane();
		VBox leftContainer = new VBox(20);
		leftContainer.setPadding(new Insets(10, 10, 10, 10));
		VBox rightContainer = new VBox(20);
		rightContainer.setPadding(new Insets(10, 10, 10, 10));

        btLoadMap = new Button("Load Map");
        btLoadUnits = new Button("Load Units");
		Label lbUnits = new Label("Units");
		ListView<String> lvUnits = new ListView<String>();
		lvUnits.setPrefSize(150, 200);
		lvUnits.setItems(listViewUnitItems);
        btStartGame = new Button("Start Game");

		btLoadMap.getStyleClass().add("menu-button");
		btLoadUnits.getStyleClass().add("menu-button");
		btStartGame.getStyleClass().add("menu-button");

		leftContainer.getChildren().addAll(btLoadMap, btLoadUnits, lbUnits, lvUnits, btStartGame);
		leftContainer.setAlignment(Pos.CENTER);

		rightContainer.getChildren().add(cvGameStart);
		rightContainer.setAlignment(Pos.CENTER);

		pane.setLeft(leftContainer);
		pane.setCenter(rightContainer);
//		pane.setRight(rightContainer);
		return pane;
	}

    // TODO 1c, TODO 3
    // Part 3: paneGamePlay
	private Pane paneGamePlay() {
		BorderPane pane = new BorderPane();
		VBox container = new VBox(20);

		Label lbCurrentTurn = new Label("");
		Label lbUnitProperty = new Label("");
        btGamePlayQuitGame = new Button("Quit Game");
        // TODO 1c: Add an "End Game" Button
        btEndGame = new Button("End Game");

		container.getChildren().addAll(cvGamePlay, lbCurrentTurn, lbUnitProperty, btGamePlayQuitGame, btEndGame);
		container.setAlignment(Pos.CENTER);
		pane.setCenter(container);

		return pane;
	}

    // TODO 1d
    // Part 4: paneGameOver
	private Pane paneGameOver() {
		BorderPane pane = new BorderPane();
		VBox container = new VBox(20);

		Label lbGameWinner = new Label("");
        btExitToMenu = new Button("Exit to Menu");
        btGameOverQuitGame = new Button("Quit Game");

		container.getChildren().addAll(lbGameWinner, btExitToMenu, btGameOverQuitGame);
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

            // Added in lab11
            initEventHandlers();
            putSceneOnStage(SCENE_WELCOME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===
    // Section: Initialization methods
    // ===
    /**
     * This method will be invoked after createScenes(). In this stage, all UI
     * components will be created with a non-NULL references for the UI components
     * that requires interaction (e.g. button click, or others).
     */
    private void initEventHandlers() {
        initWelcomeSceneHandler();
        initStartGameSceneHandler();
        initGamePlaySceneHandler();
        initGameOverHandler();
    }


    /**
     * This method is used to initialize the event handlers of welcome scene
     */
    private void initWelcomeSceneHandler() {
        //TODO 1a: setup the event handlers for btNewGame and btQuit
        btNewGame.setOnAction(e -> handleNewGame());
        btQuit.setOnAction(e -> handleExitGame());
    }

    /**
     * This method is used to initialize the event handlers of start game scene
     */
    private void initStartGameSceneHandler() {
        //TODO 1b
        // Setup the event handlers for btStartGame, it should call handleGameStartButton()
        btStartGame.setOnAction(e-> handleGameStartButton());

        //TODO 2: Setup event handler for btLoadUnit
        btLoadUnits.setOnAction(e->handleLoadUnit());

        //TODO 3: Setup event handler for btLoadMap
		btLoadMap.setOnAction(e->handleLoadMap());

    }

    /**
     * This method is used to initialize the event handlers of Game Play scene
     */
    private void initGamePlaySceneHandler() {
        //TODO 1c: Setup event handler for btEndGame and btGamePlayQuitGame
        btEndGame.setOnAction(e-> {
            putSceneOnStage(SCENE_GAMEOVER);
        });
        btGamePlayQuitGame.setOnAction(e->handleExitGame());
    }

    /**
     * This method is used to initialize the event handlers of Game Over scene
     */
    private void initGameOverHandler() {
        // TODO 1d: Setup event handler for btExitToMenu and btGameOverQuitGame
        btExitToMenu.setOnAction(e-> {
            putSceneOnStage(SCENE_WELCOME);
        });
        btGameOverQuitGame.setOnAction(e->handleExitGame());
    }


    // =======================
    // Section: Event handlers (Naming convention: handleXXX)
    // =======================

    //TODO 1a: the game should proceed to scene "Game Start"
    private void handleNewGame() {
        putSceneOnStage(SCENE_STARTGAME);
    }

    //TODO 1a
    private void handleExitGame() {
        // Pop up an alert asking "Do you want to exit this game?"
        Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to exit this game?", ButtonType.YES, ButtonType.NO);
        // Show the alert until receiving a click on the buttons
        alert.showAndWait();
        // Exit the application if player clicks "Yes"
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

    //TODO 1b
    // The game should proceed to the scene of "Game Play"
    private void handleGameStartButton() {
        putSceneOnStage(SCENE_GAMEPLAY);
    }

    private void handleLoadUnit() {
        // TODO 2
        // 1) Create a FileChooser object
		FileChooser fc = new FileChooser();

        // 2) Set the title of the FileChooser to "Load Unit" (use .setTitle(string))
		fc.setTitle("Load Unit");

        // 3) Restrict the extension of the file to only .txt
        //    (use .getExtensionFilters() to get the list of extensions allow)
        //	  (use .addAll(ExtensionFilter) to add a new restriction)
        //    (put a new file ExtensionFilter by new FileChooser.ExtensionFilter("Text files", "*.txt"))
		fc.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("Text Files", "*.txt")
		);

        // 4) Get the File object from the FileChooser object
        //    (use FileChooser object.showOpenDialog(stage) which returns the file chosen or null if no file is chosen)
		File selectedFile = fc.showOpenDialog(stage);

        // 5) If the file is not null, call loadPlayersAndUnits from GameEngine
        //    Update the method to read the new file format correctly
		if(selectedFile != null) {
			try {
				gameEngine.loadPlayersAndUnits(selectedFile);
			}
			catch (Exception e){
				e.printStackTrace();
//				System.err.println(e.printStackTrace());
			}
		}

        // 6) Call updateUnitListViewUI to update the unit into listview
		updateUnitListViewUI();
    }

    private void updateUnitListViewUI() {
        // TODO 2
        // It is used create the items to the listView with information of units

        // 7) Clear the ListView items (using .clear()) to make sure the list is empty
		listViewUnitItems.clear();


        // 8) Loop through players (you should create a getter method for player list in GameEnigne)
        // 9) For each player, loop his own list of unit
		for (Player player : gameEngine.getPlayers()) {
			for (Unit unit : player.getUnitList()) {
				StringBuilder sb = new StringBuilder();
				sb.append(unit.getId());
				sb.append(": ");
				sb.append(player.getName());
				sb.append("'s ");
				if(unit instanceof Archer) sb.append("Archer");
				else if(unit instanceof Infantry) sb.append("Infantry");
				else if(unit instanceof Cavalry) sb.append("Cavalry");
				else if(unit instanceof Pikeman) sb.append("Pikeman");
				listViewUnitItems.add(sb.toString());
			}
		}

        // 10) Put the id and type of unit, with its owner (player) into ObservableList, with format "id: player's name's type" (e.g. A: Red's Infantry)
        //     (Use .add(string) to add items into the ObservableList)

    }

    private void handleLoadMap() {
        // TODO 3
        // Setup the FileChooser similar to TODO2
		FileChooser fc = new FileChooser();

		fc.setTitle("Load Map");

		// 3) Restrict the extension of the file to only .txt
		//    (use .getExtensionFilters() to get the list of extensions allow)
		//	  (use .addAll(ExtensionFilter) to add a new restriction)
		//    (put a new file ExtensionFilter by new FileChooser.ExtensionFilter("Text files", "*.txt"))
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Text Files", "*.txt")
		);

		// 4) Get the File object from the FileChooser object
		//    (use FileChooser object.showOpenDialog(stage) which returns the file chosen or null if no file is chosen)
		File selectedFile = fc.showOpenDialog(stage);

		gameMap = gameEngine.getGameMap();

		if(selectedFile != null) {
			try {
				gameMap.loadTerrainMap(selectedFile);
			}
			catch (Exception e){
				e.printStackTrace();
//				System.err.println(e.printStackTrace());
			}
		}



        // Set the width and height of canvasGameStart and canvasGamePlay to canvasWidth and canvasHeight

		cvGamePlay.setWidth(gameMap.getCanvasWidth());
		cvGamePlay.setHeight(gameMap.getCanvasHeight());

		cvGameStart.setWidth(gameMap.getCanvasWidth());
		cvGameStart.setHeight(gameMap.getCanvasHeight());

		renderGameStartCanvas();
    }

    // ===
    // Section: rendering related functions
    // ===

    private void renderGameStartCanvas() {
        //TODO 3: call renderGameMapToCanvas
		renderGameMapToCanvas(cvGameStart);

    }

    private void renderGameMapToCanvas(Canvas c) {
        // TODO 3
        // To draw on canvas, you need to get the GraphicsContext object of canvas
        // (using .getGraphicsContext2D())
        // Draw a white rectangle with the same width and height of the canvas
        // (you may use .setFill(color), and .fillRect(posX,posY, width, height))
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, c.getWidth(), c.getHeight());

        // Create Image object for 3 types of lands
        // Initialize using new Image(path of the image)
		Image soil = new Image("./maps/soil.png");
		Image river = new Image("./maps/river.png");
		Image sand = new Image("./maps/sand.png");

        // Loop through the displayMap from gameMap
        // use .drawImage(Image object, position x, position y) to draw the image to the canvas
        // to calculate position x and position y, you should also consider the width and height of each cell
		gameMap.renderMap(gameEngine.getPlayers());
		char [][] map = gameMap.getDisplayMap();
		for(int y = 0; y < gameMap.getHeight(); y++) {
			for(int x = 0; x < gameMap.getWidth(); x++) {
				switch(map[y][x]) {
					case '0':
						gc.drawImage(river, x*32, y*32 );
						break;
					case '1':
						gc.drawImage(sand, x*32, y*32 );
						break;
					case '2':
						gc.drawImage(soil, x*32, y*32 );
						break;
				}
			}
		}
    }

	// TODO1: Define a main method and launch the application
	public static void main (String[] args) {
		launch(args);
	}



}

