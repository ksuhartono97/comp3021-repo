import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import exceptions.*;

public class GameMap {
	public static char TERRAIN_EMPTY_CHAR = ' ';
	public static char TERRAIN_BLOCKED_CHAR = '#';

	private char[][] map;
	private int width;
	private int height;

	public void loadMap(String filename) throws Exception{
		// TODO 1:
		// use the finally block to ensure the file is closed
		File inputFile = new File(filename);
		Scanner reader = null;
		try {
		// 1. update the values of instance variables width and height using the given width and height
		// 2. create a new 2D char array using the given dimension and assign it to map.
		// i.e. map = new char[height][width];
		// 3. read the terrain map in the file and store in the map variable.
		// Pseudo code:
		// for y = 0 to height - 1
		//     for x = 0 to width - 1
		//     c = the x^(th) char in the y^(th) line
		//     map[y][x] = c
			reader = new Scanner(inputFile);
			this.width = reader.nextInt();
			this.height = reader.nextInt();
			this.map = new char[height][width];
			reader.nextLine();
			for(int y = 0; y < height; y++) {
				char[] lineArr = reader.nextLine().toCharArray();
				for (int x = 0; x < width; x++) {
					if(lineArr[x] != ' ' && lineArr[x] != '#') {
						throw new InvalidTerrainType("There are characters other than ' ' or '#'.");
					}
					else {
						this.map[y][x] = lineArr[x];
					}
				}
			}
		}
//		catch(FileNotFoundException|InvalidTerrainType e) {
//			System.out.println(e.getMessage());
//		}
		finally {
			// close the file
			reader.close();
		}

		// TODO 4: check for invalid formats and throw custom exceptions
	}

	public boolean isValidLocation(int locationX, int locationY) {
		return locationX >= 0 && locationY >= 0 && locationX < width && locationY < height;
	}

	public boolean isEmpty(int locationX, int locationY) {
		return map[locationY][locationX] == TERRAIN_EMPTY_CHAR;
	}

	public boolean placeUnit(Unit unit) {
		int locationX = unit.getLocationX();
		int locationY = unit.getLocationY();
		if (!isValidLocation(locationX, locationY) || !isEmpty(locationX, locationY)) {
			return false;
		} else {
			map[locationY][locationX] = unit.getId();
			return true;
		}
	}

	public boolean moveUnit(Unit unit, int deltaX, int deltaY) {
		int newLocationX = unit.getLocationX() + deltaX;
		int newLocationY = unit.getLocationY() + deltaY;
		if (!isValidLocation(newLocationX, newLocationY) || !isEmpty(newLocationX, newLocationY)) {
			return false;
		} else {
			map[unit.getLocationY()][unit.getLocationX()] = TERRAIN_EMPTY_CHAR;
			map[newLocationY][newLocationX] = unit.getId();
			unit.moveDelta(deltaX, deltaY);
			return true;
		}
	}

	public void display() {
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				System.out.print(map[y][x]);
			}
			System.out.println();
		}
	}

}
