package game;

//import game.Level.Directions;

//import java.util.logging.Level;

/**
* this class initiates a player that has two values, a name String and a score integer and the player class has a static value that keeps
* count of the number of players !
* @author Morkos AZMY
* @version 1.0.1
*/
public class Player{

	final String name; // player's name
	private int score; // player's score
	private int x = 1;		//Player's x axis position
	private int y = 1;		//Player's y axis position
	private int lives = 3;
	static private int numberOfPlayers = 0;

	
	/**
	 * Default constructor for the {@link Player} class
	 * Initialises a player with default values while naming them JoueurN and it increments the number of players by 1
	 */
	public Player() {
		this("Joueur" + (Player.numberOfPlayers+1) );
	}
	
	/**
	 * 
	 * @param name the name of the player
	 * @param newLives his current lives !
	 */
	public Player(String name, int remainingLives, int score) {
		this.name = name;
		this.lives = remainingLives;
		this.score = score;
		this.x = 1;
		this.y = 1;
	}
	
	public Player(String name, int lives, int x, int y, int score) {
		this.name = name;
		this.lives = lives;
		this.x = x;
		this.y = y;
		this.score = score;
		Player.numberOfPlayers++;
	}
	
	/**
	 * constructs a new instance of {@link Player} class with the specified name, while the score is zero upon initialization
	 * and it increments the number of players by 1 (numberOfPlayer + 1)
	 * @param name sets the name for the player
	 * 
	 */
	public Player(String name){ //Constructor
		this.name = name;
		this.score = 0;
		Player.numberOfPlayers++;
	}	

	
	/**
	 * sets the value of <code>score</code> field 
	 * @param score sets the score of the player
	 */
	public void setScore(int score) { //@setter
		if (score < 0) {
			this.score = 0;
		}
		else {
			this.score = score;
		}
	}
		
	
	/**
	 * this integer is added to the current 
	 * @param x adds the integer x to the current score 
	 * 
	 */
	public void addScore(int x) {	// this method adds the recent score by x and applies the setScore setter on it 
		setScore(this.score + x); 	// verifying that it is not a negative number 
	}
	
		
	/**
	 * it returns the score of the player
	 * @return score
	 */
	public int getScore() { //@getter
		return this.score;
	}
	
	
	/**
	 * it returns the name of the player
	 * @return the name of the player
	 */
	public String getName() {  //@getter
		return this.name;
	}
	
	
	/**
	 * it returns the number of players that have been created
	 * @return the number of players
	 */
	public static int getNumberOfPlayers() {
		return Player.numberOfPlayers;
	}
	
	
	/**
	 * returns position y 
	 * @return y Player's y position
	 */
	public int getY() {
		return y;
	}
	
	
	/**
	 * sets position y
	 * @param y Player's y position
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
	/**
	 * returns position x
	 * @return x Player's x position
	 */
	public int getX() {
		return x;
	}
	
	
	/**
	 * sets positions x
	 * @param x Player's x position
	 */
	public void setX(int x){
		this.x = x;
	}
	
	
	/**
	 * sets both x axis and y axis player position
	 * @param x columns position
	 * @param y row position
	 */
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
		
	
	/**
	 * it returns the position of the player (x,y)
	 * @return String containing the (x,y) position of the player
	 */
	public String getPosition() {
		return "Current Position: (" + this.getY() +" , " + this.getX() + ")";
	}
	
	/**
	 * this method decreases the number of lives by one
	 */
	public void loseLife() {
		if(this.lives > 0) {
			this.lives -= 1;
		}		
	}
	
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getLives() {
		return lives;
	}
		

	public Player clone() {
		return new Player(this.name, this.lives, x, y, this.score);
	}
	

	/**
	 * it returns a String that contains the name and the score in the following form: nom : score pt(s)
	 * @return player's name and score
	 */
	public String toString() {
		String message = this.getName() + " : " + this.getScore() + " pt";
		
		return this.getScore() > 1 || this.getScore() == 0 ? message + "s" : message;
	}

	
	/**
	 * this method verifies if both object are of the same class and also verifies if they have the same name.
	 * @param obj the object that will be used in the comparison
	 * @return boolean true if the object equals the other, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player other = (Player) obj;
			if (this.getName().toLowerCase().equals(other.getName().toLowerCase())){
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	
}