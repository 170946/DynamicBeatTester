package dynamic_beat_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame{
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")); //If the file name is wrong, there will be some error
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	private int mouseX, mouseY;
	
	public static void main(String[] args){
		new DynamicBeat();
	}
	
	public DynamicBeat(){
		super.setUndecorated(true);
		super.setTitle("Dynamic Beat");
		super.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
		super.setBackground(new Color(0, 0, 0, 0));//Sets the background of the frame to be black. Note that if alpha = 0, the color is totally black, alpha = 1, the color is totally transparent. Not sure why, but alpha = 0.5 seems to be the best to see actual colors
		super.setLayout(null);
		
		/** EXIT BUTTON */
		/*
		 * Some notes: adding the exitButton first does two things:
		 * 		1. The MouseAdapter for exitButton now works in the region of exitButton
		 * 		2. The exitButton now appears above the menuBar
		 */
		exitButton.setBounds(1245, 0, 30, 30); //When I make this 1245, 0, 30, 30, the mousePressed thing seems not to get triggered. I suspect that this may be because of the MouseAdapter in the menuBar JLabel. YES, when that is commented out, the exit button works again
		exitButton.setBorderPainted(false);//Makes the border unpainted, rather than the strange blue color
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //adding this line makes entering the region of the JButton change the image of the cursor to a hand. However, JUST THIS LINE is enough to make it go back to normal when the mouse exits the region
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e){
				exitButton.setIcon(exitButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				try{
					Thread.sleep(1000);
				} catch(Exception ex){
					System.out.println(ex.getMessage());
				}
				System.exit(0);
			}
		});
		this.add(exitButton);
		
		/**MENU BAR */
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {//Does this create a new .class file? Yep. This addMouseListener block created a new .class file DynamicBeat$1.class
			@Override
			public void mousePressed(MouseEvent e){
				mouseX = e.getX(); //computes the values only when the mouse is first pressed
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e){
				int x = e.getXOnScreen(); //gets the absolute horizontal x position of the event, where this is continuously recomputed
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); //prefixes this. and super. are both invalid for this method??? wtf
			}
		});
		this.add(menuBar);
		
		Music introMusic = new Music("introM.mp3", true);
		introMusic.start();
	}
	
	public void paint(Graphics g){
		screenImage = super.createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		paintComponents(g); //I guess this paints all the components of THIS JFrame onto whatever Graphics Object is passed
							//Background appears precisely when this method is commented out
							//Even if there are no Components to paint, running this method kills the background image
		this.repaint();
	}
}
