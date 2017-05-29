package dynamic_beat_4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public static void main(String[] args){
		Music m = new Music("IntroM.mp3", true);
		m.start();
		/*
		try {
			m.player.play();//Two calls of the play method don't do anything
			Thread.sleep(1000);
			m.player.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
	public Music(String name, boolean isLoop){
		try{
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI()); //WTF IS A URI
																				 //apparently, getResource returns a URL Object
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); //wtf are these two things????
			player = new Player(bis);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime(){
		if(player == null) return 0;
		return player.getPosition();
	}
	
	public void close(){
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	
	@Override
	public void run(){
		try{
			do{
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); //wtf are these two things????
				player = new Player(bis);
			} while(isLoop);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
