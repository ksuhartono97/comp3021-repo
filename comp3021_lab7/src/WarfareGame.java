import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import exceptions.*;

public class WarfareGame {
	private GameMap gameMap = new GameMap();
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private static Scanner userInputScanner = new Scanner(System.in);

	private void loadUnits(String filename) throws Exception{
		// TODO 2:
		// 1. use try-with-resources syntax to ensure the file is closed
		// 2. read the number of units
		// 3. for each line
		//    read id, type, locationX, locationY
		//    create a unit of corresponding type and add it to the ArrayList<Unit> units
		//        Hint: use the add() method of units
		//    place the unit in the game map.
		//        Hint: use the placeUnit() method of gameMap

		File inputFile = new File(filename);
		Scanner reader = null;
		try {
			reader = new Scanner(inputFile);
			int numOfUnits = reader.nextInt();
			reader.nextLine();
			for(int i = 0; i<numOfUnits; i++) {
				char id = reader.next().charAt(0);
				String type = reader.next();
				int locX = reader.nextInt();
				int locY = reader.nextInt();

				if(units.contains(new Unit(id, 1,1,1,1,1,1))) {
					throw new DuplicateUnitId("Two units have the same id.");
				}
				if (!gameMap.isValidLocation(locX, locY)) {
					throw new InvalidUnitLocation(String.format("%c is out of bounds", id));
				}

				switch (type) {
					case "Archer":
						units.add(new Archer(id, locX, locY));
						if(!gameMap.placeUnit(units.get(units.size()-1)))
							throw new InvalidUnitLocation("There are two units in the same location");
						break;
					case "Cavalry":
						units.add(new Cavalry(id, locX, locY));
						if(!gameMap.placeUnit(units.get(units.size()-1)))
							throw new InvalidUnitLocation("There are two units in the same location");
						break;
					case "Infantry":
						units.add(new Infantry(id, locX, locY));
						if(!gameMap.placeUnit(units.get(units.size()-1)))
							throw new InvalidUnitLocation("There are two units in the same location");
						break;
					case "Pikeman":
						units.add(new Pikeman(id, locX, locY));
						if(!gameMap.placeUnit(units.get(units.size()-1)))
							throw new InvalidUnitLocation("There are two units in the same location");
						break;
					default:
						throw new InvalidUnitType("A " + type + " is not a valid type of unit ");
				}
			}
		}
//		catch(FileNotFoundException|InvalidUnitType|InvalidUnitLocation|DuplicateUnitId e) {
//			System.out.println(e.getMessage());
//		}
		finally {
			// close the file
			reader.close();
		}

		// TODO 4: check for invalid formats and throw custom exceptions
	}

	private Unit getUnit(char unitId) {
		Unit unit = null;
		for (Unit u : units) {
			if (u.getId() == unitId)
				unit = u;
		}
		return unit;
	}

	private void processUnitMovement() {
		Unit unit = null;
		while (unit == null) {
			System.out.print("Enter the ID of unit: ");
			char id = userInputScanner.next().charAt(0);
			unit = getUnit(id);
		}

		boolean success = false;
		while (!success) {
			System.out.print("Enter the amount of movement (e.g. 3 4): ");
			int deltaX = userInputScanner.nextInt();
			int deltaY = userInputScanner.nextInt();
			success = gameMap.moveUnit(unit, deltaX, deltaY);
			if (!success) {
				System.err.println("Invalid Destination");
			}
		}
	}

	public static void main(String[] args) {
		WarfareGame game = new WarfareGame();
		String mapFile;
		String unitsFile;
		try {
			game.gameMap.loadMap(args[0]);
			System.out.println("Loaded map:");
			game.gameMap.display();

			game.loadUnits(args[1]);
			System.out.println("Loaded units: ");
			for (Unit unit : game.units) {
				System.out.println(unit);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}

		do {
			game.gameMap.display();
			System.out.print("Continue to move a unit? (y/n): ");
			String answer = userInputScanner.next();
			if (answer.toLowerCase().startsWith("y")) {
				game.processUnitMovement();
			} else {
				break;
			}
		} while (true);
		userInputScanner.close();
	}

}
