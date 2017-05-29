package in_game_testing_1;

import java.awt.Graphics;
import java.util.ArrayList;

//this class contains a list of all objects currently active in the game
public class Handler {

	private ArrayList<MovingObject> MovingObjectList = new ArrayList<>();

	public void tick(long currentTime) {
		for(MovingObject o: MovingObjectList){
			o.tick(currentTime);
		}
		
	}
	
	public void render(Graphics g){
		for(MovingObject o: MovingObjectList){
			o.render(g);
		}
	}

	public void addObject(MovingObject movingObject) {
		MovingObjectList.add(movingObject);
		
	}
	
}
