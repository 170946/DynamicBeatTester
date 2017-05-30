package in_game_testing_1;

import java.util.Scanner;

public class GameSettings {
	public static final int MAX_SCORE = 1_000_000;
	public static final int[] DESIRED_X = {300, 400, 500, 600};
	public static final int[] DESIRED_Y = {720, 720, 720, 720};
	
	//The velocities of each lane in pixels / s
	public static final int[] VELOCITY_X = {0, 0, 0, 0}; 
	public static final int[] VELOCITY_Y = {1000, 1000, 1000, 1000};
	
	
	
	/**The maximum possible errors in timing one could have to still register as a particular hit judgement, in ns.
	 * If the note is pressed after this error judgment, it will be counted as a miss*/
	public static final long HIT_GREAT_ERROR = 50_000_000, HIT_GOOD_ERROR = 200_000_000, HIT_MISS_ERROR = 500_000_000;
	
	/**The key configuration based on the keycodes */
	public static int[] keyConfig = new int[4];
	
	/**The location where the hit rating will be drawn */
	public static final int hitRatingX = 500, hitRatingY = 500;	
	
	/**
	 * Given a scanner passed to this method whose 
	 * @param sc
	 */
	public static void loadKeyConfig(Scanner sc) {
		sc.useDelimiter(",");
		keyConfig[0] = Integer.parseInt(sc.next().trim());
		keyConfig[1] = Integer.parseInt(sc.next().trim());
		keyConfig[2] = Integer.parseInt(sc.next().trim());
		keyConfig[3] = Integer.parseInt(sc.next().trim());
	}
	
	
}
