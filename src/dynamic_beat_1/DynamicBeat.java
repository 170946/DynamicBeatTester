package dynamic_beat_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image introBackground;
	
	public static void main(String[] args){
		System.out.println(new DynamicBeat().introBackground);
	}
	
	public DynamicBeat(){
		super.setTitle("Dynamic Beat");
		super.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
		
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
	}
	
	public void paint(Graphics g){
		screenImage = super.createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint();
	}
}
