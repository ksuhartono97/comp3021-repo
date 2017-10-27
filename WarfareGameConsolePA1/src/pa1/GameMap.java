package pa1;

import exceptions.InvalidTerrainTypeException;
import exceptions.InvalidUnitLocationException;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

// Origin (0, 0) is at top-left corner; X-axis increases from left-to-right; Y-axis increases from top-to-bottom.
public class GameMap 
{
	public static int TERRAIN_OUT_OF_BOUNDS = -1;
	public static int TERRAIN_EMPTY = 0;
	public static int TERRAIN_BLOCKED = 1;
	public static int TERRAIN_OCCUPIED = 2;
	
	public static char TERRAIN_EMPTY_CHAR = ' ';
	public static char TERRAIN_BLOCKED_CHAR = '#';
	
	private int height, width;
	private int[][] terrainMap;
	private char[][] displayMap;
	
	// TODO: Load the terrainMap based on specified input textfile, with correct formatting.
	// Throw an IOException if the input textfile format is wrong.
	// Use try-with-resources.
	public void loadTerrainMap(String filename) throws IOException
	{
		File inputFile = new File(filename);
		try (Scanner reader = new Scanner(inputFile)){

			this.width = reader.nextInt();
			this.height = reader.nextInt();
			this.terrainMap = new int[height][width];
			reader.nextLine();

			for(int y = 0; y < height; y++) {
				char[] lineArr = reader.nextLine().toCharArray();
				for (int x = 0; x < width; x++) {
					if(lineArr[x] != ' ' && lineArr[x] != '#') {
						throw new InvalidTerrainTypeException("Invalid Terrain Type: " + lineArr[x]);
					}
					else {
						switch (lineArr[x]) {
							case ' ':
								this.terrainMap[y][x] = TERRAIN_EMPTY;
								break;
							case '#':
								this.terrainMap[y][x] = TERRAIN_BLOCKED;
								break;
						}
					}
				}
			}
		}
	}
	
	// TODO: Call this method from GameEngine when placing Units onto their Starting Locations.
	// Throw an IOException whenever there's a problem with the Starting Location.
	// 1) Outside of Game Map Boundary.
	// 2) Blocked by Terrain.
	// 3) Occupied by another Unit.
	public void checkStartingLocation(char id, int locationX, int locationY) throws IOException
	{
		if(locationX <= 0 && locationY <= 0 && locationX > width && locationY > height)
			throw new InvalidUnitLocationException("Unit " + id + " Starting Location outside of Game Map Boundary.");
		else if(this.terrainMap[locationY][locationX] == TERRAIN_BLOCKED)
			throw new InvalidUnitLocationException("Unit " + id + " Starting Location Blocked by Terrain.");
		else if(this.terrainMap[locationY][locationX] == TERRAIN_OCCUPIED)
			throw new InvalidUnitLocationException("Unit " + id + " Starting Location Occupied by another Unit.");
	}
	
	// Use (TERRAIN_OUT_OF_BOUNDS, TERRAIN_OUT_OF_BOUNDS) for adding/removing Units from the Map.
	// (Old, Old) == (TERRAIN_OUT_OF_BOUNDS, TERRAIN_OUT_OF_BOUNDS) for Adding New Units.
	// (New, New) == (TERRAIN_OUT_OF_BOUNDS, TERRAIN_OUT_OF_BOUNDS) for Removing Dead Units.
	public void updateUnitLocationOnMap(int locationXOld, int locationYOld, int locationXNew, int locationYNew)
	{
		if ((locationXOld >= 0) && (locationYOld >= 0))
		{
			terrainMap[locationYOld][locationXOld] = TERRAIN_EMPTY;
		}
		
		if ((locationXNew >= 0) && (locationYNew >= 0))
		{
			terrainMap[locationYNew][locationXNew] = TERRAIN_OCCUPIED;
		}
	}
	
	// TODO: Check the validity of Target Location when Units are Moving.
	// 1) Outside Game Map Boundary.
	// 2) Outside of Movement Range.
	// 3) Blocked by Terrain.
	// 4) Occupied by another Unit.
	// BONUS) Path in-between is blocked by Terrain/Unit(s), and not enough Movement Range to go around (instead of teleporting through).
	public boolean isValidPath(int locationXOld, int locationYOld, int locationXNew, int locationYNew, int movementRange)
	{
		int movementDistance = Math.abs(locationXOld - locationXNew) + Math.abs(locationYOld - locationYNew);

//		int bfsX = locationXOld;
//		int bfsY = locationYOld;
//		ArrayList<Integer> xLocationQueue = new ArrayList<>();
//		ArrayList<Integer> yLocationQueue = new ArrayList<>();
//
//		xLocationQueue.add(bfsX);
//		xLocationQueue.add(bfsX+1);
//		xLocationQueue.add(bfsX);
//		xLocationQueue.add(bfsX-1);
//		yLocationQueue.add(bfsY+1);
//		yLocationQueue.add(bfsY);
//		yLocationQueue.add(bfsY-1);
//		yLocationQueue.add(bfsY);


		if(locationXNew <= 0 && locationYNew <= 0 && locationXNew > width && locationYNew > height) {
			System.err.println("Target Location outside of Game Map Boundary.");
			return false;
		}
		else if(movementDistance > movementRange) {
			System.err.println("Outside of Movement Range.");
			return false;
		}
		else if(this.terrainMap[locationYNew][locationXNew] == TERRAIN_BLOCKED) {
			System.err.println("Target Location blocked by Terrain.");
			return false;
		}
		else if(this.terrainMap[locationYNew][locationXNew] == TERRAIN_OCCUPIED) {
			System.err.println("Target Location occupied by another Unit.");
			return false;
		}
		return true;
	}
	
	// TODO: Display the GameMap.
	public void renderMap(ArrayList<Player> players)
	{
		displayMap = new char[height][width];
		// TODO: Render terrainMap onto displayMap.
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				char mapCharacter;
				switch (terrainMap[y][x]) {
					case 1:
						mapCharacter = TERRAIN_BLOCKED_CHAR;
						break;
					default:
						mapCharacter = TERRAIN_EMPTY_CHAR;
						break;
				}
				displayMap[y][x] = mapCharacter;
			}
		}
		
		// TODO: Render Units onto displayMap.
		for (Player pl : players ) {
			for (Unit un : pl.getUnitList()) {
				if(un.isAlive()) {
					displayMap[un.getLocationY()][un.getLocationX()] = un.getId();
				}
			}
		}
		
		// TODO: Output the Map. Leave a newline before and after the displayed Map.
		System.out.println();
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				System.out.print(displayMap[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
