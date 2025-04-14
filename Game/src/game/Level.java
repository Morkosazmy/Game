package game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * This class initializes a level that contains the number of rows, columns and a grid(level)
 * @author Morkos AZMY
 * @version 1.0.1
 */
public class Level{
	private String levelName;
	private int rows;
	private int columns;
	private char[][] grid;
	private Player player;
	static private int numberOfCoins = 0;
	static private int totalUsedLives = 0;

	
	
	
	/**
	 * it takes rows and columns and sets a Grid for the level with them
	 * 
	 * @param rows it takes the number of rows
	 * @param columns it takes the number of columns
	 */
	public Level(int rows, int columns){
		this.grid = new char[rows][columns];
		this.rows = rows;
		this.columns = columns;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				this.grid[i][j] = ' ';
			}
		}
	}
	//is it commiting

	
	
	/**
	 * this constructor creates a level using an external text file
	 * 
	 * @param filePath the path of the file that will be used.
	 * 
	 * @throws FileNotFoundException if the file is not found.
	 */
	public Level(String filePath) throws FileNotFoundException {
		List<String> lines;
		try {
			lines = Files.readAllLines(Path.of(filePath));
			this.rows = lines.size();
			this.columns = this.rows > 0 ? lines.get(0).length() : 0;

			for(String line : lines) {
				if(line.length() != columns) {
					throw new IllegalArgumentException("Each line of the file must have the same length !");
				}
			}

			this.grid = new char[this.rows][this.columns];
			
			for(int i = 0; i < this.rows; i++) {
				String line = lines.get(i);
				for(int j = 0; j < this.columns; j++) {
					grid[i][j] = line.charAt(j);
				}
			}
			//this.countNumberOfCoins();

		} 
		catch (IOException e) {
			System.err.println("Error: could not load error file: " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param name
	 */
	public void setLevelName(String name) {
		this.levelName = name;
	}
	
	public String getLevelName() {
		return this.levelName;
	}
	
	/**
	 * places a wall '#' in the chosen poisiton
	 * @param row the index of the row where the wall will be placed
	 * @param column the index of the column where the wall will be placed
	 */
	public void setWall(int row, int column) {
		grid[row][column] = '#';
	}
	
	/**
	 * places a wall '#' in the chosen poisiton
	 * @param row the index of the row where the wall will be placed
	 * @param column the index of the column where the wall will be placed
	 */
	public void setSpace(int row, int column) {
		grid[row][column] = ' ';
	}
	
	/**
	 * Places a Coin 'C' in the chosen position
	 * 
	 * @param row the index of the row at which the coin will be placed
	 * @param column the index of the column of which the coin will be placed
	 */
	public void setCoin(int row, int column) {
		this.grid[row][column] = '.';
		Level.numberOfCoins += 1;
	}
	
	/**
	 * Places a trap '*' in the chosen position
	 * 
	 * @param row the index of the row at which the trap will be placed
	 * @param column the index of the column at which the trap will be placed
	 */
	public void setTrap(int row, int column) {
		this.grid[row][column] = '*';
	}


	/**
	 * sets the wall around the whole level
	 */
	public void setWallsAroundGrid() {
        for (int i = 0; i < this.columns; i++) {
            setWall(0, i);
            setWall(this.rows - 1, i);        
        }

        for (int i = 0; i < this.rows; i++) {
            setWall(i, 0);
            setWall(i, this.columns - 1);
        }
    }


	/**
	 * it returns the player
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
	
	
	/**
	 * it spawns a player in the grid of the game at the given position and prints the level with the player in it
	 * 
	 * @param player the player that will spawn
	 * @param x column of the player's position
	 * @param y row of the player's position
	 * 
	 * @throws IllegalArgumentException if the player is in a wall or if he's out of the level's grid
	 */
	public void spawnPlayer(Player player, int x, int y) {
		if(x <= 0 || x >= this.grid[0].length || y <= 0 || y >= this.grid.length) {
			throw new IllegalArgumentException("The player is out of the level's grid");
		}
		
		if(this.grid[y][x] == '#') {
			throw new IllegalArgumentException("The player is in a wall '#' ");
		}
		
		this.grid[y][x] = '1';
		player.setPosition(x, y);
		this.player = player;
		System.out.println(this.toString());
	}
	
	// create a method that checks if the player won!
	/**
	 * 
	 * @return boolean, true if the player won, false if there's still other coins to be collected
	 */
	public boolean allCoinsCollected() {
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				if(this.grid[i][j] == '.') {
					return false;
				}
			}
		}
		return true;
	}
	
	public void countNumberOfCoins() {
		Level.numberOfCoins = 0;
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				if(grid[i][j] == '.') {
					Level.numberOfCoins += 1;
				}
			}
		}
	}
	
	public boolean allLivesLost() {
		if(this.player.getLives() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Moves the player's position in the chosen direction, won't move if there's a wall or if it's outside of the grid.
	 * 
	 * @param Direction The direction in which the player will move.
	 */
	public void movePlayer(Direction direction) {
		int newX = this.player.getX();
		int newY = this.player.getY();
		
		switch(direction) {
			case NORTH:
				newY--;
				break;

			case EAST:
				newX++;
				break;
				
			case SOUTH:				
				newY++;
				break;
				
			case WEST:
				newX--;
				break;
				
			default:
				System.out.println("Invalid direction, Use ('up', 'down', 'left' or 'right') to move the player");
				return;
		}

	    if (newX < 0 || newX >= this.grid[0].length || newY < 0 || newY >= this.grid.length) {
	        System.out.println("The player can't get out of the level's grid!");
			System.out.println(this.toString());
	        return; // Prevent movement outside the grid
	    }
		
		if(this.grid[newY][newX] == '#') {
			System.out.println("The player can't go through a wall !");
			System.out.println(this.toString());
			return;
		}
		
		if(this.grid[newY][newX] == '.') {
			Level.numberOfCoins += 1;
			System.out.println("You collected a coin !");
			this.player.addScore(100);
		}
		
		if(this.grid[newY][newX] == '*') {
			this.player.loseLife();
			Level.totalUsedLives++;
			System.out.println("You lost a life !");
		}
		this.grid[this.player.getY()][this.player.getX()] = ' ';
		this.player.setPosition(newX, newY);
		this.grid[newY][newX] = '1';
		
		System.out.println(this.toString());
		if(allCoinsCollected()) {
			System.out.println("VICTORY !");
		}
		if(allLivesLost()) {
			System.out.println("DEFEAT !\nGAME OVER !");
		}
	}	
	
	/*public boolean makeLost() {
		return true;
	}*/
	public boolean isComplete() {
		return allCoinsCollected() && !allLivesLost();
	}

	public int getNumberOfCoins() {
		return Level.numberOfCoins;
	}
	
	public static void resetTotalCoins() {
		Level.numberOfCoins = 0;
	}
	
	public static void resetTotalLives() {
		Level.totalUsedLives = 0;
	}
	
	public static void resetLevelStatus() {
		Level.resetTotalCoins();
		Level.resetTotalLives();
	}
	
	/**
	 * Compares this Level Object with another Level Object to see if they are equal or not if both Objects have the same number of rows
	 * and columns and are of the same class. They are considered equal.
	 * @param obj the objective that will be used in the comparison
	 * @return boolean true if they are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Level) {
			Level other = (Level) obj;
			if(this.rows == other.rows && this.columns == other.columns) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * it returns the grid of the level with the name and score of the player above it.
	 * @return result A string containing the level and the palyer's info 
	 */
	public String toString() {
		
		String result = "";
		result += "TOTAL score: " + this.getNumberOfCoins()*100 + "\n";
		result += "TOTAL of the lost lives: " + Level.totalUsedLives + "\n";
		result += "Player Info: " + this.player.toString() + "\n";
		result += "Lives: " + this.player.getLives() + "\n";
		result += this.player.getPosition() + "\n";
		result += "Number of coins: " + this.getNumberOfCoins() + "\n";
		result += "Level's Grid :\n";
		for(int i = 0; i < this.grid.length; i++) {
			for(int j = 0; j  < this.grid[i].length; j++) {
				if(j == this.grid[i].length - 1) {
				result += this.grid[i][j] + "\n";
				}
				else {
					result += this.grid[i][j];
				}
			}
		}
		return result;
	}
		
	
}
