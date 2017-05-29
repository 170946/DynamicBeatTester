package in_game_testing_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ScannerTester {
	public static void main(String[] args){
		try {
			Scanner sc = new Scanner(new File(GameFrame2.class.getResource("../songs/" + "The Lost Dedicated" + "/notechart.txt").toURI()));
			sc.useDelimiter(",");
			System.out.println(sc.nextInt());
			sc.next();
			System.out.println(sc.nextInt());
			sc.nextLine();
			System.out.println(sc.nextInt());//ideally, we print 64, 1772, 320. And we do. FUCK YEAH
			
			System.out.println(sc.findWithinHorizon("99607", 1_000_000));
			System.out.println(sc.next());
		} catch (FileNotFoundException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
