/*
 * NOTES:
 * 		All times are in nanoseconds. They will be converted when necessary
 */

package in_game_testing_1;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import dynamic_beat_4.Music;

public class GameFrame extends JFrame{
	public static final int FRAME_HEIGHT = 720;
	public static final int FRAME_WIDTH = 1280;
	
	public long startTime;
	public Music music = new Music("Mighty Love Selected.mp3", false);
	
	private Handler handler;
	
	public static void main(String[] args){
		new GameFrame(null);
	}
	
	public GameFrame(Music music){
		if(music != null) this.music = music;
		
		handler = new Handler();
		
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("Dynamic Beat");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Rather than terminate the whole program, merely gets rid of this window
		
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){ 
				int keyValue = e.getKeyCode();
				//System.out.println(keyValue);
				
				if(keyValue == 32){
					spawnBullet();// How do we make it so that it's spawned only when the key is pressed down?
				}
			}

		});
		
		this.music.start();
		
		startTime = System.nanoTime();
		long prevTime = startTime;
		while(true){
			long now = System.nanoTime();
			
			tick(now);
			//handler.render(this.getGraphics());
			render();
			prevTime = now;
			
		}
		
	}

	public void spawnBullet() {
		new Bullet(0, 0, 400, 400, handler);
	}
	
	public void paint(Graphics g){
		//paintComponents(g);
		//repaint();
	}
	
	public void tick(long currentTime){
		handler.tick(currentTime);
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(10);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//Drawing onto the bs graphics context what we want
		g.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		handler.render(g);
		
		g.dispose();//'disposes of this graphics context and any system resources it might be using'
		bs.show(); 
	}
}
