package in_game_testing_1;

import java.awt.Graphics;

import javax.swing.JLabel;

public abstract class MovingObject{
	protected int x, y;//The current position
	protected int vX, vY;//The velocity of this MovingObject in pixels / s
	protected long creationTime;//The result of a System.nanoTime() call
	
	
	public MovingObject(int xPosition, int yPosition, int xVelocity, int yVelocity, Handler handler){
		handler.addObject(this);
		x = xPosition;
		y = yPosition;
		vX = xVelocity;
		vY = yVelocity;
		creationTime = System.nanoTime();
	}
	/**
	 * Updates the position of this MovingObject based on the currentTime
	 * @param currentTime, the time with which we calculate this MovingObject's position
	 */
	public abstract void tick(long currentTime);
	
	/**Paints this object in Graphics context g (I'm not sure what a Graphics context is... but the name sounds right)
	 * @param g, the graphics context 
	 */
	public abstract void render(Graphics g);
}
