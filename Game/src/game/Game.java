package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
	
	/**
	 * This class represents the main game class, which runs the program.
	 * It handles the initialisation and execution of the game.
	 */
	public class Game {
		private Player player;
		private Level currentLevel;
		private List<Level> levels = new ArrayList<>();
		private Scanner scanner = new Scanner(System.in);


		/**
		 * this method makes the user choose the player's name
		 * @return name the user's name input
		 */
		public String getPlayerName() {
			String name;
			do {
				System.out.print("Enter your name: ");
				name = scanner.nextLine();
			} while(name.isEmpty());
			return name;
		}
		
		public void startGame(String firstLevelPath) {
			String name = getPlayerName();
			player = new Player(name);
			player.setScore(0);
			levels.clear();
			
			loadLevels(firstLevelPath);
			
			int currentLevelIndex = 0;
			
			while(true) {
				currentLevel = levels.get(currentLevelIndex);
				currentLevel.setLevelName(""+(currentLevelIndex+1));
				System.out.println("Current Level: " + currentLevel.getLevelName());
				System.out.println(currentLevel.toString());
				
				play();
				/*while(!currentLevel.isComplete() && player.getLives() > 0) {
				play();	
				}*/
				
				player = currentLevel.getPlayer();
				
				if(currentLevel.isComplete()) {
					currentLevelIndex++;
					
					if (currentLevelIndex < levels.size()) {
						System.out.println("Next level !");
					}
					else {
						System.out.println("You have completed all the levels!");
						System.out.println("Thank you for playing the game!");
						break;
					}
				}
				
				if(player.getLives() <= 0) {
				//	System.out.println("GAME OVER !" );
					break;
				}
			}	
		}
		

		public void play() {
			//boolean running = true;
	        while (true) {
	            // Check for win/loss conditions
	            if (currentLevel.allCoinsCollected()) {
	                System.out.println("Congratulations! You've collected all the coins!");
	          //      running = false;
	                break;
	            }
	            
	            
	            // gets repeated endlessly??
	            if (currentLevel.allLivesLost()) {
	                //System.out.println("You've lost all your lives.");
	            //    running = false;
	                break;
	            }

	            // Prompt user for input
	            System.out.println("Enter direction (UP'Z', RIGHT'D', DOWN'S', LEFT'Q'): ");
	            String directionInput = scanner.nextLine().toUpperCase();

	            Direction direction;
	            switch (directionInput) {
	                case "Z":
	                case "W":
	                    direction = Direction.NORTH;
	                    break;
	                case "D":
	                    direction = Direction.EAST;
	                    break;
	                case "S":
	                    direction = Direction.SOUTH;
	                    break;
	                case "Q":
	                case "A":
	                    direction = Direction.WEST;
	                    break;
	                default:
	                    System.out.println("Invalid direction! Please enter Z, Q, S, or D( W A S D in qwerty).");
	                    System.out.println(currentLevel.toString());
	                    continue;  // Ask for input again
	            }

	            // Move player in the chosen direction
	            currentLevel.movePlayer(direction);
	        }

	        //scanner.close(); // Close the scanner after the game ends
	    }
	
		
		private void loadLevels(String firstLevelPath) {
			try {
				int firstLevelNumber = extractLevelNumber(firstLevelPath);
				int levelNumber = firstLevelNumber;
				while(true) {
					String levelPath = "C:\\Users\\morko\\eclipse-workspace\\Game\\resources\\Level" + levelNumber + ".txt";
					
					System.out.println("Trying to load level file: " + levelPath);
					
					File levelFile = new File(levelPath);
					if(!levelFile.exists()) {
						System.out.println("File does not exist: " + levelPath);
						break;
					}
					Level level = new Level(levelPath);
					// create a new player with name , and number of lives !
					System.out.println("Why is the score not working correctly");
					System.out.println(player.getScore());
					Player cloneOfPlayer = player.clone();
					cloneOfPlayer.setScore(0);
					levels.add(level);
					
					int startingX = 1;
					int startingY = 1;
					//cloneOfPlayer.setScore(level.getNumberOfCoins() * 100);
					//System.out.println("the score of the clone is : " + player.getScore());

					cloneOfPlayer.setPosition(startingX, startingY);
					level.spawnPlayer(cloneOfPlayer, startingX, startingY);
					
					levelNumber++;

				}
			} catch(FileNotFoundException e) {
				System.out.println("Error loading level files: " + e.getMessage());
			}
		}
		
		
		private int extractLevelNumber(String levelPath) {
			String levelName = new File(levelPath).getName().replace("Level", "").replace(".txt", "");
			try {
				return Integer.parseInt(levelName);
			} catch(NumberFormatException e) {
				System.out.println("Error: invalid level format in file name.");
				return 1;
			}				
		}
	
		/**
		 * this is the main class that runs the program
		 * @param args it takes arguments  
		 * @throws FileNotFoundException
		 */
		public static void main(String[] args) throws FileNotFoundException { // main class
	        if (args.length < 1) {
	            System.err.println("Error: No level file was provided in the argument.");
	            System.err.println("Utilization: java -jar Game.jar path/to/LevelN.txt");
	            return;
	        }
			
	        Scanner scanner = new Scanner(System.in);
	        String filePath = args[0];
	        boolean playAgain;
	        
	        do {
	        	Game game = new Game();
	        	game.startGame(filePath);
	        	
	        	System.out.print("Do you want to play again ? (Y/N): ");
	        	Level.resetLevelStatus();
	        	String answer = scanner.nextLine().toUpperCase();
	        	playAgain = answer.equals("Y");
	        } while(playAgain);

	        System.out.println("Thank you for playing!");
		}
}
	
	
	
	
	
	