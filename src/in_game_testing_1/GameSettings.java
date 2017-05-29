package in_game_testing_1;

public class GameSettings {
	public static final int MAX_SCORE = 1_000_000;
	public static final int[] DESIRED_X = {0, 100, 200, 300};
	public static final int[] DESIRED_Y = {720, 720, 720, 720};
	
	//The velocities of each lane in pixels / s
	public static final int[] VELOCITY_X = {0, 0, 0, 0}; 
	public static final int[] VELOCITY_Y = {1000, 1000, 1000, 1000};
	
	//The maximum possible errors in timing one could have to still register as a particular hit judgement
	public static final int HIT_GREAT_ERROR = 50;
	public static final int HIT_GOOD_ERROR = 200;//If the note is pressed after this error judgment, it will be counted as a miss
	
	//User preferences loaded from userpreference.txt
	
}
