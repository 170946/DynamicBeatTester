package in_game_testing_1;

import java.awt.Graphics;
import java.util.ArrayList;

public class NoteHandler {
	private ArrayList<Note> activeNotes0 = new ArrayList<>(); 
	private ArrayList<Note> activeNotes1 = new ArrayList<>();
	private ArrayList<Note> activeNotes2 = new ArrayList<>();
	private ArrayList<Note> activeNotes3 = new ArrayList<>();
	
	
	public void tick(long currentTime){//checks whether a Note should be removed from this NoteHandler, then ticks every Note in this NoteHandler's List of Notes
		for(int i = activeNotes0.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes0.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > GameSettings.HIT_GOOD_ERROR * currentNote.getvY() / 1_000){
				activeNotes0.remove(i);
			}
			else{
				currentNote.tick(currentTime);
			}
		}
		for(int i = activeNotes1.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes1.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > GameSettings.HIT_GOOD_ERROR * currentNote.getvY() / 1_000){
				activeNotes1.remove(i);
			}
			else{
				currentNote.tick(currentTime);
			}
		}
		for(int i = activeNotes2.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes2.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > GameSettings.HIT_GOOD_ERROR * currentNote.getvY() / 1_000){
				activeNotes2.remove(i);
			}
			else{
				currentNote.tick(currentTime);
			}
		}
		for(int i = activeNotes3.size() - 1; i >= 0; i--){
			Note currentNote = activeNotes3.get(i);
			if(currentNote.getY() - currentNote.getDesiredY() > GameSettings.HIT_GOOD_ERROR * currentNote.getvY() / 1_000){
				activeNotes3.remove(i);
			}
			else{
				currentNote.tick(currentTime);
			}
		}
	
	}
	
	public void render(Graphics g){
		for(Note n: activeNotes0){
			n.render(g);
		}
		for(Note n: activeNotes1){
			n.render(g);
		}
		for(Note n: activeNotes2){
			n.render(g);
		}
		for(Note n: activeNotes3){
			n.render(g);
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
}
