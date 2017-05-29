package in_game_testing_1;

import java.awt.Graphics;
/**
 * A 
 * @author s170946
 *
 */
public class Bullet extends MovingObject{
	private int spawnX, spawnY;
	
	
	
	public Bullet(int xPosition, int yPosition, int xVelocity, int yVelocity, Handler handler) {
		super(xPosition, yPosition, xVelocity, yVelocity, handler);
		spawnX = xPosition;
		spawnY = yPosition;
	}

	@Override
	public void tick(long currentTime) {
		long timeDifferenceInMs = (currentTime - this.creationTime) / 1_000_000;
		this.x = this.spawnX + (int)((timeDifferenceInMs * this.vX) / 1000);
		this.y = this.spawnY + (int)((timeDifferenceInMs * this.vY) / 1000);
	}

	@Override
	public void render(Graphics g) {
		g.fillRect(x, y, 30, 30);
		
	}



}
