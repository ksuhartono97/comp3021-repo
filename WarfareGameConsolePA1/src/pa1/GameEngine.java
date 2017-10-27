package pa1;

import exceptions.DuplicatePlayerNameException;
import exceptions.DuplicateUnitIdException;
import exceptions.InvalidUnitTypeException;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class GameEngine 
{
	private GameMap gameMap = new GameMap();
	private ArrayList<Player> players = new ArrayList<>();
	private static Scanner userInputScanner = new Scanner(System.in);
	
	// TODO: Load the Players and Units based on specified input textfile, with correct formatting.
	// Throw an IOException if errors are found.
	// Use try-with-resources.
	// Call checkStartingLocation() from GameMap when initializing Unit Starting Locations. Hint: this method already throws the IOExceptions.
	// Call updateUnitLocationOnMap() when Units passed all error-checking and added to game.
	// Hint: ArrayList has the contains() method, which uses the equals() method to check if an object is already inside the ArrayList.
	// 1) Duplicate Player Names are not allowed.
	// 2) Duplicate Unit ID is not allowed.
	// 3) Check if Unit Type is invalid. Hint: Java supports switch-case on a String.
	private void loadPlayersAndUnits(String filename) throws IOException
	{
		File inputFile = new File(filename);
		try (Scanner reader = new Scanner(inputFile)) {
			int numOfPlayer = reader.nextInt();
			for(int i = 0; i < numOfPlayer; i++) {
				reader.nextLine();
				String playerName = reader.next();
				if(players.contains(new Player(playerName))) throw new DuplicatePlayerNameException("Duplicate Player Name: " + playerName  + "is not allowed.");

				int playerNumberOfUnits = reader.nextInt();

				Player newPlayer = new Player(playerName);

				for(int j = 0; j<playerNumberOfUnits; j++) {
					reader.nextLine();
					char id = reader.next().charAt(0);
					String type = reader.next();
					int locX = reader.nextInt();
					int locY = reader.nextInt();

					if(newPlayer.getUnitList().contains(new Archer(id, 1,1))) {
						throw new DuplicateUnitIdException("Duplicate Unit ID: " + id + " is not allowed.");
					}

					gameMap.checkStartingLocation(id, locX, locY);

					Unit newUnit;

					switch (type) {
						case "Archer":
							newUnit = new Archer(id, locX, locY);
							break;
						case "Cavalry":
							newUnit = new Cavalry(id, locX, locY);
							break;
						case "Infantry":
							newUnit = new Infantry(id, locX, locY);
							break;
						case "Pikeman":
							newUnit = new Pikeman(id, locX, locY);
							break;
						default:
							throw new InvalidUnitTypeException("A " + type + " is not a valid type of unit ");
					}

					newPlayer.addUnit(newUnit);
					gameMap.updateUnitLocationOnMap(GameMap.TERRAIN_OUT_OF_BOUNDS, GameMap.TERRAIN_OUT_OF_BOUNDS, newUnit.getLocationX(), newUnit.getLocationY());
				}
				players.add(newPlayer);
			}
		}
	}
	
	private void processPlayerTurns()
	{
		for (Player player:players)
		{
			if (player.hasUnitsRemaining())
			{				
				System.out.println(player.getName() + " Turn:");
				player.readyAllUnits();
				
				while (player.hasReadyUnits())
				{
					gameMap.renderMap(players);
					displayPlayers();
					
					Unit unit = selectUnit(player);
					
					while (true)
					{
						System.out.print("Heal, Move&Attack [H,M]: ");
						char choice = (userInputScanner.next()).charAt(0);
						
						if (choice == 'H')
						{
							unit.heal();
							unit.endTurn();
							break;
						}
						else if (choice == 'M')
						{
							processUnitMovementPhase(unit);
							processUnitAttackPhase(player, unit);
							unit.endTurn();
							break;
						}
						else
						{
							System.out.println("Invalid command.");
						}
					}
				}
			}
			
			// More newlines between each player's turns.
			System.out.print("\n\n\n");
		}
	}
	
	private Unit selectUnit(Player currentPlayer)
	{
		String readyUnits = "";
		for (Unit unit:currentPlayer.getUnitList())
		{
			if (unit.isAlive() && unit.isReady())
			{
				readyUnits += unit.getId();
			}
		}
		
		while (true)
		{
			System.out.print("Select Unit [" + readyUnits + "]: ");
			char inputId = (userInputScanner.next()).charAt(0);
		
			if (readyUnits.contains(Character.toString(inputId)))
			{
				return currentPlayer.getUnitById(inputId);
			}
			else
			{
				System.out.println("Invalid selection.");
			}
		} 
	}
	
	private void processUnitMovementPhase(Unit currentUnit)
	{
		int deltaX, deltaY;
		
		do
		{
			System.out.print("Enter Movement X Y Amount (0 0 to stay): ");
			deltaX = userInputScanner.nextInt();
			deltaY = userInputScanner.nextInt();
		
			if ((deltaX == 0) && (deltaY == 0))
			{
				return;
			}
		}
		while (!gameMap.isValidPath(currentUnit.getLocationX(), currentUnit.getLocationY(), currentUnit.getLocationX() + deltaX, currentUnit.getLocationY() + deltaY, currentUnit.getMovementRange()));
		
		gameMap.updateUnitLocationOnMap(currentUnit.getLocationX(), currentUnit.getLocationY(), currentUnit.getLocationX() + deltaX, currentUnit.getLocationY() + deltaY);
		currentUnit.moveDelta(deltaX, deltaY);
		
		gameMap.renderMap(players);
		displayPlayers();
	}
	
	private void processUnitAttackPhase(Player currentPlayer, Unit currentUnit)
	{
		// Get list of Enemy Units in range.
		ArrayList<Unit> enemyUnitsInRange = new ArrayList<>(); 
		String enemyUnitsInRangeById = "";
		for (Player player:players)
		{
			if (!player.equals(currentPlayer))
			{
				for (Unit unit:player.getUnitList())
				{
					if (unit.isAlive())
					{
						if ((Math.abs(unit.getLocationX() - currentUnit.getLocationX()) + Math.abs(unit.getLocationY() - currentUnit.getLocationY())) <= currentUnit.getAttackRange())
						{
							enemyUnitsInRange.add(unit);
							enemyUnitsInRangeById += unit.getId();
						}
					}
				}
			}
		}
		
		// No Enemy Units in Attack Range.
		if (enemyUnitsInRangeById.equals(""))
		{
			System.out.println("No Enemy Units in Attack Range. Ending Turn for Unit " + currentUnit.getId() + "");
			return;
		}
		
		while (true)
		{
			System.out.print("Choose Attack Target [" + enemyUnitsInRangeById + " , / to Not Attack]: ");
			char enemyUnitId = (userInputScanner.next()).charAt(0);
			
			if (enemyUnitsInRangeById.contains(Character.toString(enemyUnitId)))
			{
				for (Unit enemyUnit:enemyUnitsInRange)
				{
					if (enemyUnit.getId() == enemyUnitId)
					{
						currentUnit.attackUnit(enemyUnit);
						
						// If the attacker or defender died, remove from Game Map.
						if (!currentUnit.isAlive())
						{
							gameMap.updateUnitLocationOnMap(currentUnit.getLocationX(), currentUnit.getLocationY(), GameMap.TERRAIN_OUT_OF_BOUNDS, GameMap.TERRAIN_OUT_OF_BOUNDS);
						}
						
						if (!enemyUnit.isAlive())
						{
							gameMap.updateUnitLocationOnMap(enemyUnit.getLocationX(), enemyUnit.getLocationY(), GameMap.TERRAIN_OUT_OF_BOUNDS, GameMap.TERRAIN_OUT_OF_BOUNDS);
						}
						
						return;
					}
				}
			}
			else if (enemyUnitId == '/')
			{
				return;
			}
			else
			{
				System.out.println("Invalid Attack Target.");
			}
		}
	}
	
	private void displayPlayers() 
	{
		for (Player player:players) 
		{
			System.out.println(player.getName() + ":");
			
			for (Unit unit:player.getUnitList())
			{
				System.out.println("\t" + unit);
			}
			
			System.out.println();
		}
	}
	
	// TODO: If only 1 player has Units Remaining, then the game has ended.
	private boolean isGameOver() 
	{
		int alivePlayers = 0;
		for (Player pl : players) {
			if(pl.hasUnitsRemaining()) alivePlayers++;
		}
		return alivePlayers <= 1;
	}

	private void displayGameOver() 
	{
		gameMap.renderMap(players);
		displayPlayers();
		System.out.println("GAME OVER\n");
		
		for (Player player:players) 
		{
			if (player.hasUnitsRemaining())
			{
				System.out.println(player.getName() + " WON.\n");
			}
			else
			{
				System.out.println(player.getName() + " LOST.\n");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.out.println("args[0] is textfile for GameMap, args[1] is textfile for Players&Units.");
			return;
		}
		
		GameEngine game = new GameEngine();
		
		// Load the GameMap, Players, and Units, from user-input textfiles.
		try
		{
			game.gameMap.loadTerrainMap(args[0]);
			game.loadPlayersAndUnits(args[1]);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
		
		// Run the game.
		while (!game.isGameOver()) 
		{
			game.processPlayerTurns();
		}
		
		// Game Over.
		game.displayGameOver();
		
		// Close userInput.
		userInputScanner.close();
	}
}
