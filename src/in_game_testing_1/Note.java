package in_game_testing_1;

import java.awt.Graphics;

public class Note{
	private int desiredX, desiredY; //The coordinates of the top left corner of where the note is supposed to be pressed by the player
	private final long desiredTime; //The time after the song starts that the Note is supposed to be pressed by the player (ns)
	private int x, y;//The current coordinates of the Note
	private int vX, vY; //The velocity of the Note in pixels / s
	private NoteHandler noteHandler; //An Object with a list of all active Notes. Created notes will be added to notehandler
	
	/**
	 * 
	 * @param lane, the lane, (0, 1, 2, 3) that you'd like this note to be in
	 * @param desiredTime, the desired time you'd like this note to be pressed by the player after the song starts
	 * @param spawnTime, the time this note is spawned after the song started. I'm not entirely sure if this is necessary, because calling this Note's 
	 * @param noteHandler, the particular NoteHandler that should contain this note. There will be just one NoteHandler per song that you are playing
	 */
	public Note(int lane, long desiredTime, NoteHandler noteHandler){
		this.desiredX = GameSettings.DESIRED_X[lane];
		this.desiredY = GameSettings.DESIRED_Y[lane];
		this.x = -500; //makes the note not visible for now. The note will become visible after the first call of its tick() method, which should be pretty soon
		this.y = -500;
		this.vX = GameSettings.VELOCITY_X[lane];
		this.vY = GameSettings.VELOCITY_Y[lane];
		this.desiredTime = desiredTime;
		this.noteHandler = noteHandler;
		
		noteHandler.addNote(this, lane);
	}
	
	//Accessor methods
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getDesiredX(){
		return this.desiredX;
	}
	
	public int getDesiredY(){
		return this.desiredY;
	}

	public int getvY() {
		return this.vY;
	}
	
	public int getvX() {
		return this.vX;
	}
	
	
	
	/**
	 * Updates the position of this note (doesn't render) based on the currentTime you are into the song
	 * @param currentTime, the current position in the song, in ns
	 */
	public void tick(long currentTime){//updates the position of this note
		this.x = this.desiredX - (int)((vX * (this.desiredTime - currentTime)) / 1_000_000_000);
		this.y = this.desiredY - (int)((vY * (this.desiredTime - currentTime)) / 1_000_000_000);
	}
	
	public void render(Graphics g){
		g.fillRect(this.x, this.y, 50, 50);
	}

	public long getDesiredTime() {
		return this.desiredTime;
	}
}
