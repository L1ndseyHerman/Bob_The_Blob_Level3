

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 * The Design-Phase Algebros mentioned a "Walls" class in PacHumanGame, but did not create one. I decided to rename
 * my Block class to "Walls".
 * @author linds
 *
 */
public class Walls 
{
	//	public is fine here
	public Rectangle2D.Double theWall;
	public double xWall, yWall, sizeXWall, sizeYWall;
	
	
	public Walls(double xWall, double yWall, double sizeXWall, double sizeYWall)
	{
		this.xWall = xWall;
		this.yWall = yWall;
		this.sizeXWall = sizeXWall;
		this.sizeYWall = sizeYWall;
		theWall = new Rectangle2D.Double(xWall, yWall, sizeXWall, sizeYWall);
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.blue);
		//g.fill(theWall);
		g.draw(theWall);
		
		//	To make look like a continuous shape, getting rid of the black outline for each Wall
		//g.setColor(Color.black);
		//g.draw(theBlock);
	}
	
	
	
	public double getLeftSide()
	{
		return xWall;
	}
	
	public double getRightSide()
	{
		return xWall + sizeXWall;
	}
	
	public double getTopSide()
	{
		return yWall;
	}
	
	public double getBottomSide()
	{
		return yWall + sizeYWall;
	}
}
