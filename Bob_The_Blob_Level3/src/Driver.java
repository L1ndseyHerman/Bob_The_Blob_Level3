import java.awt.Color;

import javax.swing.JFrame;

/**
 * 
 * @author linds
 *Estimated time Dragonal: 9 hrs. Total Time: 14.5 hrs.
 *Done!
 *
 *CORE GAMEPLAY CHANGES FROM PREVIOUS LEVELS:
 *1: Went from using the Letter class for Bob's words to toString(); If only I had remembered Java's built-in letters instead
 *	of spending 5+ hours creating my own for Level1!
 *2: Figured out the algorithm for making Bob move to the center of the screen for beatLevel and gameOver no matter where he was 
 *	on the screen. His speech-bubble going outside the Panel when winning Level2 annoyed me enough to figure it out.
 */
public class Driver 
{

	public static void main(String[] args) 
	{
		//	Changing from 1350 to 1000, because the min laptop screen width is 1024px, but that's a weird number, so 1000 it is!
		//	Making a color variable
		Panel panel = new Panel(1000, 500, new Color(200, 200, 255));
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
