/*
 * NOTES:
 * 		All times are in nanoseconds. They will be converted when necessary
 */

package in_game_testing_1;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import dynamic_beat_4.Music;

public class GameFrame2 extends JFrame{
	public static final int FRAME_HEIGHT = 720;
	public static final int FRAME_WIDTH = 1280;
	
/**MOSTLY CONSTANT VALUES */
	private long startTime;
	private Music music;
	private Scanner noteScanner;
	private NoteHandler noteHandler;
	private ArrayList<Long> noteTimes = new ArrayList<>();
	private ArrayList<Integer> noteLanes = new ArrayList<>();
	
/**CONTINUOUSLY CHANGING VALUES */
	private int nextNoteIndexToAdd = 0;//The index of the next note to be added to the NoteHandler
	private boolean[] keyDown = new boolean[]{false, false, false, false};
	
	//TO DO
	private int score;
	private int combo;
	private int maxCombo;
	private double rate;
	
	
	public static void main(String[] args){
		new GameFrame2("The Lost Dedicated");
	}
	
	public GameFrame2(String songTitle){
		//Loads the user preferences into the GameSettings static variables
		try{
			Scanner loadPreferences = new Scanner(new File(GameFrame2.class.getResource("../game_data/userpreferences.txt").toURI()));
			loadPreferences.useDelimiter(",");
			loadPreferences.findWithinHorizon("[KEY CONFIGURATION]", 10_000);
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//Loads all note lanes and times into the noteLanes, noteTimes ArrayLists. 
		try {
			noteScanner = new Scanner(new File(GameFrame2.class.getResource("../songs/" + songTitle + "/notechart.txt").toURI()));
			noteScanner.useDelimiter(",");
			while(noteScanner.hasNext()){
				noteLanes.add(noteScanner.nextInt() / 128);
				noteScanner.next();
				noteTimes.add(noteScanner.nextLong() * 1_000_000);
				noteScanner.nextLine();
			}
		} catch (URISyntaxException | FileNotFoundException e1) {
			e1.printStackTrace();
		} //perhaps investigate adding Scanners taking in InputStreams, etc.
		
		this.noteHandler = new NoteHandler();
		
		//setting up the look and feel of this JFrame
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("Dynamic Beat");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Rather than terminate the whole program, merely gets rid of this window
		
		
		
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){ 
				int keyValue = e.getKeyCode();
				//System.out.println(keyValue);
				
				/*if(keyValue == 32){
					spawnBullet();// How do we make it so that it's spawned only when the key is pressed down?
				}*/
			}
			public void keyReleased(KeyEvent e){
				
			}

		});
		
		//this.music.start();
		
		startTime = System.nanoTime();
		
		
		while(true){
			long currentTime = System.nanoTime() - startTime;
			addNote(currentTime);
			
			tick(currentTime);
			render();
		}
		
	}

	private void addNote(long currentTime) {
		if(nextNoteIndexToAdd < this.noteTimes.size() && noteTimes.get(nextNoteIndexToAdd) - currentTime < 2_000_000_000){
			this.noteHandler.addNote(new Note(noteLanes.get(nextNoteIndexToAdd), noteTimes.get(nextNoteIndexToAdd), noteHandler), noteLanes.get(nextNoteIndexToAdd));
			nextNoteIndexToAdd++;
		}
	}

	private void tick(long currentTime){
		this.noteHandler.tick(currentTime);
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(10);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//Drawing onto the bs graphics context what we want
		g.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		this.noteHandler.render(g);
		
		g.dispose();//'disposes of this graphics context and any system resources it might be using'
		bs.show(); 
	}
}
