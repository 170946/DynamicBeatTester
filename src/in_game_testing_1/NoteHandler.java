package in_game_testing_1;

import java.awt.Graphics;
import java.util.ArrayList;

public class NoteHandler {
	private ArrayList<Note> activeNotes0 = new ArrayList<>(); 
	private ArrayList<Note> activeNotes1 = new ArrayList<>();
	private ArrayList<Note> activeNotes2 = new ArrayList<>();
	private ArrayList<Note> activeNotes3 = new ArrayList<>();
	
	private GameFrame2 gameFrame2;
	
	public NoteHandler(GameFrame2 gf2){
		gameFrame2 = gf2;
	}
	
	public void tick(long currentTime){//checks whether a Note should be removed from this NoteHandler, then ticks every Note in this NoteHandler's List of Notes
		for(int i = activeNotes0.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes0.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > (GameSettings.HIT_MISS_ERROR * currentNote.getvY()) / 1_000_000_000){
				activeNotes0.remove(i);
				//this.gameFrame2.handleMiss();
			}
			else{
				currentNote.tick(currentTime);
			}
		}
		for(int i = activeNotes1.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes1.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > (GameSettings.HIT_MISS_ERROR * currentNote.getvY()) / 1_000_000_000){
				activeNotes1.remove(i);
				//this.gameFrame2.handleMiss();
			}
			else{
				currentNote.tick(currentTime);
			}
		}
		for(int i = activeNotes2.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes2.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > (GameSettings.HIT_MISS_ERROR * currentNote.getvY()) / 1_000_000_000){
				activeNotes2.remove(i);
				//this.gameFrame2.handleMiss();
			}
			else{
				currentNote.tick(currentTime);
			}
		}
		for(int i = activeNotes3.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes3.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > (GameSettings.HIT_MISS_ERROR * currentNote.getvY()) / 1_000_000_000){
				activeNotes3.remove(i);
				//this.gameFrame2.handleMiss();
			}
			else{
				currentNote.tick(currentTime);
			}
		}
	
	}
	
	public void render(Graphics g){
		for(int i = activeNotes0.size() - 1; i >= 0; i--){
			activeNotes0.get(i).render(g);
		}
		for(int i = activeNotes1.size() - 1; i >= 0; i--){
			activeNotes1.get(i).render(g);
		}
		for(int i = activeNotes2.size() - 1; i >= 0; i--){
			activeNotes2.get(i).render(g);
		}
		for(int i = activeNotes3.size() - 1; i >= 0; i--){
			activeNotes3.get(i).render(g);
		}
	}

	public void addNote(Note note, int lane) {
		if(lane == 0){
			activeNotes0.add(note);
		}
		else if(lane == 1){
			activeNotes1.add(note);
		}
		else if(lane == 2){
			activeNotes2.add(note);
		}
		else if(lane == 3){
			activeNotes3.add(note);
		}
		
	}

	public void handleNotePress(long pressTime, int lane) {
		if(lane == 0){
			if(activeNotes0.size() == 0) return;
			Note noteToPossiblyDelete = activeNotes0.get(0); //You will always be adding newer notes to the top; the note in question should always be the one at the "bottom" of this ArrayList
			long hitError = Math.abs(noteToPossiblyDelete.getDesiredTime() - pressTime);
			
			if(hitError < GameSettings.HIT_GREAT_ERROR){
				activeNotes0.remove(0);
				this.gameFrame2.handleGreat();
			}
			else if(hitError < GameSettings.HIT_GOOD_ERROR){
				this.gameFrame2.handleGood();
				activeNotes0.remove(0);
			}
			else if(hitError < GameSettings.HIT_MISS_ERROR){
				this.gameFrame2.handleMiss();
				activeNotes0.remove(0);
			}
			
		}
		if(lane == 1){
			if(activeNotes1.size() == 0) return;
			Note noteToPossiblyDelete = activeNotes1.get(0); //You will always be adding newer notes to the top; the note in question should always be the one at the "bottom" of this ArrayList
			long hitError = Math.abs(noteToPossiblyDelete.getDesiredTime() - pressTime);
			
			if(hitError < GameSettings.HIT_GREAT_ERROR){
				activeNotes1.remove(0);
				this.gameFrame2.handleGreat();
			}
			else if(hitError < GameSettings.HIT_GOOD_ERROR){
				this.gameFrame2.handleGood();
				activeNotes1.remove(0);
			}
			else if(hitError < GameSettings.HIT_MISS_ERROR){
				this.gameFrame2.handleMiss();
				activeNotes1.remove(0);
			}
			
		}
		if(lane == 2){
			if(activeNotes2.size() == 0) return;
			Note noteToPossiblyDelete = activeNotes2.get(0); //You will always be adding newer notes to the top; the note in question should always be the one at the "bottom" of this ArrayList
			long hitError = Math.abs(noteToPossiblyDelete.getDesiredTime() - pressTime);
			
			if(hitError < GameSettings.HIT_GREAT_ERROR){
				activeNotes2.remove(0);
				this.gameFrame2.handleGreat();
			}
			else if(hitError < GameSettings.HIT_GOOD_ERROR){
				this.gameFrame2.handleGood();
				activeNotes2.remove(0);
			}
			else if(hitError < GameSettings.HIT_MISS_ERROR){
				this.gameFrame2.handleMiss();
				activeNotes2.remove(0);
			}
			
		}
		if(lane == 3){
			if(activeNotes3.size() == 0) return;
			Note noteToPossiblyDelete = activeNotes3.get(0); //You will always be adding newer notes to the top; the note in question should always be the one at the "bottom" of this ArrayList
			long hitError = Math.abs(noteToPossiblyDelete.getDesiredTime() - pressTime);
			
			if(hitError < GameSettings.HIT_GREAT_ERROR){
				activeNotes3.remove(0);
				this.gameFrame2.handleGreat();
			}
			else if(hitError < GameSettings.HIT_GOOD_ERROR){
				this.gameFrame2.handleGood();
				activeNotes3.remove(0);
			}
			else if(hitError < GameSettings.HIT_MISS_ERROR){
				this.gameFrame2.handleMiss();
				activeNotes3.remove(0);
			}
			
		}
		
	}
}
