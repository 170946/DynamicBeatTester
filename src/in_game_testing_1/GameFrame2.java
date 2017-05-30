/*
 * NOTES:
 * 		All times are in nanoseconds. They will be converted when necessary
 */

package in_game_testing_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private int totalNumNotes;
	private int numNotesPassed;
	
	
/**CONTINUOUSLY CHANGING VALUES */
	private int nextNoteIndexToAdd = 0;//The index of the next note to be added to the NoteHandler
	private boolean[] keyDown = new boolean[]{false, false, false, false};
	
	//TO DO
	private int score;
	private int combo;
	private int maxCombo;
	private double rate;
	private int numGreatHit;
	private int numGoodHit;
	private int numMissHit;
/**SKIN DATA */
	private Image hitGreat;
	private boolean greatDisplayed;
	private Image hitGood;
	private boolean goodDisplayed;
	private Image hitMiss;
	private boolean missDisplayed;
	private String skinName;
	private Image background;
	
	public static void main(String[] args){
		new GameFrame2("Neru - Ningen Shikkaku");
	}
	
	public GameFrame2(String songTitle){
/** LOAD USER PREFERENCES*/
		try{
			Scanner sc = new Scanner(new File(GameFrame2.class.getResource("../userpreferences.txt").toURI()));
			sc.findWithinHorizon("KEY CONFIGURATION:", 10_000);
			GameSettings.loadKeyConfig(sc);
		} catch(Exception e){
			e.printStackTrace();
		}
/** LOAD SKIN NAME*/
		try {
			Scanner sc = new Scanner(new File(GameFrame2.class.getResource("../userpreferences.txt").toURI()));
			sc.findWithinHorizon("SKIN:", 10_000);
			this.loadSkinName(sc);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
/** LOAD SKIN IMAGE DATA*/
		hitGreat = new ImageIcon(GameFrame2.class.getResource("../skins/" + skinName + "/hitgreat.png")).getImage();
		hitGood = new ImageIcon(GameFrame2.class.getResource("../skins/" + skinName + "/hitgood.png")).getImage();
		hitMiss = new ImageIcon(GameFrame2.class.getResource("../skins/" + skinName + "/hitmiss.png")).getImage();
		
/**LOAD ALL NOTES INTO INSTANCE ARRAYLISTS */
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
/** RANDOM*/
		totalNumNotes = noteTimes.size();
		this.noteHandler = new NoteHandler(this);
/** SET THE MUSIC */
		this.music = new Music(songTitle, false);
		
/** SETTING UP LOOK AND FEEL */
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(songTitle);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Rather than terminate the whole program, merely gets rid of this window
		
		this.background = new ImageIcon(GameFrame2.class.getResource("../songs/" + songTitle + "/background.jpg")).getImage();
		/*
		this.setContentPane(new JLabel(new ImageIcon(GameFrame2.class.getResource("../songs/" + songTitle + "/background.jpg"))));
		this.paintComponents(getGraphics());
		*/
/**MAKING THE GAME RESPONSIVE TO THE USER'S KEY PRESSES */	
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){ 
				int keyValue = e.getKeyCode();
				if(keyValue == GameSettings.keyConfig[0]){
					long pressTime = System.nanoTime() - startTime;
					keyDown[0] = true;//This will be used to determine the lighting effects on the JFrame
					noteHandler.handleNotePress(pressTime, 0);
				}
				else if(keyValue == GameSettings.keyConfig[1]){
					long pressTime = System.nanoTime() - startTime;
					keyDown[1] = true;//This will be used to determine the lighting effects on the JFrame
					noteHandler.handleNotePress(pressTime, 1);
				}
				else if(keyValue == GameSettings.keyConfig[2]){
					long pressTime = System.nanoTime() - startTime;
					keyDown[2] = true;//This will be used to determine the lighting effects on the JFrame
					noteHandler.handleNotePress(pressTime, 2);
				}
				else if(keyValue == GameSettings.keyConfig[3]){
					long pressTime = System.nanoTime() - startTime;
					keyDown[3] = true;//This will be used to determine the lighting effects on the JFrame
					noteHandler.handleNotePress(pressTime, 3);
				}
				
			}
			public void keyReleased(KeyEvent e){
				int keyValue = e.getKeyCode();
				if(keyValue == GameSettings.keyConfig[0]){
					keyDown[0] = false;
				}
				else if(keyValue == GameSettings.keyConfig[1]){
					keyDown[1] = false;
				}
				else if(keyValue == GameSettings.keyConfig[2]){
					keyDown[2] = false;
				}
				else if(keyValue == GameSettings.keyConfig[3]){
					keyDown[3] = false;
				}
			}

		});
		
		this.music.start();
		startTime = System.nanoTime();
		
		
		while(true){
			long currentTime = System.nanoTime() - startTime;
			addNote(currentTime);
			
			tick(currentTime);
			render();
		}
		
	}

	private void loadSkinName(Scanner sc) {
		this.skinName = sc.next().trim();
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
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//Drawing onto the bs graphics context what we want
		g.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		g.drawImage(background, 0, 0, null);
		this.noteHandler.render(g);
/** Displaying the various hit ratings*/
		if(greatDisplayed) g.drawImage(hitGreat, GameSettings.hitRatingX, GameSettings.hitRatingY, null);
		else if(goodDisplayed) g.drawImage(hitGood, GameSettings.hitRatingX, GameSettings.hitRatingY, null);
		else if(missDisplayed) g.drawImage(hitMiss, GameSettings.hitRatingX, GameSettings.hitRatingY, null);
		
		g.dispose();//'disposes of this graphics context and any system resources it might be using'
		bs.show(); 
	}

	public void handleGreat() {
		//the settings used for rendering 
		goodDisplayed = false;
		missDisplayed = false;
		greatDisplayed = true;
		
		//altering score and combo
		combo++;
		numGreatHit++;
		numNotesPassed++;
		
		score = (2 * numGreatHit + numGoodHit) * 1_000_000 / totalNumNotes;
		rate = (double)(numGreatHit + numGoodHit) * 100 / numNotesPassed;
	}

	public void handleGood() {
		goodDisplayed = true;
		missDisplayed = false;
		greatDisplayed = false;
		
		//altering score and combo
		combo++;
		numGoodHit++;
		numNotesPassed++;
		
		score = (2 * numGreatHit + numGoodHit) * 1_000_000 / totalNumNotes;
		rate = (double)(numGreatHit + numGoodHit) * 100 / numNotesPassed;
	}

	public void handleMiss() {
		goodDisplayed = false;
		missDisplayed = true;
		greatDisplayed = false;
		
		//altering score and combo
		combo = 0;
		numMissHit++;
		numNotesPassed++;
		
		score = (2 * numGreatHit + numGoodHit) * 1_000_000 / totalNumNotes;
		rate = (double)(numGreatHit + numGoodHit) * 100 / numNotesPassed;
	}
	
	
}
