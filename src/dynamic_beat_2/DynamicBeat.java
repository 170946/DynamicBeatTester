package dynamic_beat_2;

import java.awt.Color;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	
	public DynamicBeat(){
		super.setTitle("Dynamic Beat");
		super.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
	}
}
