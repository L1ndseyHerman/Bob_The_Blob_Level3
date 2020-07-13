import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
/**
 * A dragon made out of quadralaterals that only moves diagonally!
 * Involves left- and right-side variables, and bouncing off Panel and Blocks.
 * @author linds
 *
 */
public class Dragonal 
{
	private double xDragonal, yDragonal, widthDragonal, heightDragonal, dragonalSpeedX, dragonalSpeedY;
	private Rectangle2D.Double hitbox;
	private Line2D.Double topHeadLine, rightHeadLine, bottomHeadLine, leftHeadLine;
	private Line2D.Double eyeLine;
	private Line2D.Double topBodyLine, rightBodyLine, bottomBodyLine, leftBodyLine;
	private Line2D.Double leftWingLine, topWingLine, rightWingLine;
	private Line2D.Double leftTailLine, rightTailLine;
	//	Legs go left (1) to right (4)
	private Line2D.Double leftLegOneLine, bottomLegOneLine, rightLegOneLine;
	private Line2D.Double leftLegTwoLine, bottomLegTwoLine, rightLegTwoLine;
	private Line2D.Double leftLegThreeLine, bottomLegThreeLine, rightLegThreeLine;
	private Line2D.Double leftLegFourLine, bottomLegFourLine, rightLegFourLine;
	//	For switching the side of the dragonal (left default)
	private boolean showLeftSide = true;
	
	//	Above was for the left-facing side of Dragonal. Below is the right side variables.
	private Line2D.Double topHeadLineRight, rightHeadLineRight, bottomHeadLineRight, leftHeadLineRight;
	private Line2D.Double eyeLineRight;
	private Line2D.Double topBodyLineRight, rightBodyLineRight, bottomBodyLineRight, leftBodyLineRight;
	private Line2D.Double leftWingLineRight, topWingLineRight, rightWingLineRight;
	private Line2D.Double leftTailLineRight, rightTailLineRight;
	private Line2D.Double leftLegOneLineRight, bottomLegOneLineRight, rightLegOneLineRight;
	private Line2D.Double leftLegTwoLineRight, bottomLegTwoLineRight, rightLegTwoLineRight;
	private Line2D.Double leftLegThreeLineRight, bottomLegThreeLineRight, rightLegThreeLineRight;
	private Line2D.Double leftLegFourLineRight, bottomLegFourLineRight, rightLegFourLineRight;
	
	public Dragonal(double xDragonal, double yDragonal, double dragonalSpeedX, double dragonalSpeedY)
	{
		this.xDragonal = xDragonal;
		this.yDragonal = yDragonal;
		widthDragonal = 130;
		heightDragonal = 70;
		
		this.dragonalSpeedX = dragonalSpeedX;
		this.dragonalSpeedY = dragonalSpeedY;
		
		//	Will eventually be invisible (don't draw) like League of Legends hitboxes!
		hitbox = new Rectangle2D.Double(xDragonal, yDragonal, widthDragonal, heightDragonal);

		topHeadLine = new Line2D.Double(xDragonal, yDragonal+heightDragonal/5, xDragonal+widthDragonal/5, yDragonal+heightDragonal/5);
		rightHeadLine = new Line2D.Double(xDragonal+widthDragonal/5, yDragonal+heightDragonal/5, xDragonal+widthDragonal/5, yDragonal+2*heightDragonal/5);
		bottomHeadLine = new Line2D.Double(xDragonal+widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal, yDragonal+2*heightDragonal/5);
		leftHeadLine = new Line2D.Double(xDragonal, yDragonal+heightDragonal/5, xDragonal, yDragonal+2*heightDragonal/5);
		
		eyeLine = new Line2D.Double(xDragonal+widthDragonal/10, yDragonal+5*heightDragonal/20, xDragonal+widthDragonal/10, yDragonal+3*heightDragonal/10);
		
		topBodyLine = new Line2D.Double(xDragonal+widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal+4*widthDragonal/5, yDragonal+2*heightDragonal/5);
		rightBodyLine = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal+4*widthDragonal/5, yDragonal+7*heightDragonal/10);
		bottomBodyLine = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+7*heightDragonal/10, xDragonal+widthDragonal/5, yDragonal+7*heightDragonal/10);
		leftBodyLine = new Line2D.Double(xDragonal+widthDragonal/5, yDragonal+7*heightDragonal/10, xDragonal+widthDragonal/5, yDragonal+2*heightDragonal/5);
		
		leftWingLine = new Line2D.Double(xDragonal+3*widthDragonal/10, yDragonal+2*heightDragonal/5, xDragonal+3*widthDragonal/5, yDragonal);
		topWingLine = new Line2D.Double(xDragonal+3*widthDragonal/5, yDragonal, xDragonal+4*widthDragonal/5, yDragonal+heightDragonal/5);
		rightWingLine = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+heightDragonal/5, xDragonal+2*widthDragonal/5, yDragonal+2*heightDragonal/5);
		
		leftTailLine = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal+widthDragonal, yDragonal+3*heightDragonal/10);
		rightTailLine = new Line2D.Double(xDragonal+widthDragonal, yDragonal+3*heightDragonal/10, xDragonal+4*widthDragonal/5, yDragonal+5*heightDragonal/10);
		
		leftLegOneLine = new Line2D.Double(xDragonal+widthDragonal/5, yDragonal+7*heightDragonal/10, xDragonal+3*widthDragonal/10, yDragonal+heightDragonal);
		bottomLegOneLine = new Line2D.Double(xDragonal+3*widthDragonal/10, yDragonal+heightDragonal, xDragonal+7*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegOneLine = new Line2D.Double(xDragonal+7*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+5*widthDragonal/20, yDragonal+7*heightDragonal/10);
		
		leftLegTwoLine = new Line2D.Double(xDragonal+7*widthDragonal/20, yDragonal+7*heightDragonal/10, xDragonal+9*widthDragonal/20, yDragonal+heightDragonal);
		bottomLegTwoLine = new Line2D.Double(xDragonal+9*widthDragonal/20, yDragonal+heightDragonal, xDragonal+10*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegTwoLine = new Line2D.Double(xDragonal+10*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+8*widthDragonal/20, yDragonal+7*heightDragonal/10);
		
		leftLegThreeLine = new Line2D.Double(xDragonal+10*widthDragonal/20, yDragonal+7*heightDragonal/10, xDragonal+12*widthDragonal/20, yDragonal+heightDragonal);
		bottomLegThreeLine = new Line2D.Double(xDragonal+12*widthDragonal/20, yDragonal+heightDragonal, xDragonal+13*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegThreeLine = new Line2D.Double(xDragonal+13*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+11*widthDragonal/20, yDragonal+7*heightDragonal/10);
		
		leftLegFourLine = new Line2D.Double(xDragonal+13*widthDragonal/20, yDragonal+7*heightDragonal/10, xDragonal+15*widthDragonal/20, yDragonal+heightDragonal);
		bottomLegFourLine = new Line2D.Double(xDragonal+15*widthDragonal/20, yDragonal+heightDragonal, xDragonal+16*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegFourLine = new Line2D.Double(xDragonal+16*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+14*widthDragonal/20, yDragonal+7*heightDragonal/10);

		//	Mirror Image	Get it to 1, like xDragonal = 0 + widthDragonal = 1 = 1				Opposite of 1/5 is 4/5, 1/5 + 4/5 = 1
		topHeadLineRight = new Line2D.Double(xDragonal+widthDragonal, yDragonal+heightDragonal/5, xDragonal+4*widthDragonal/5, yDragonal+heightDragonal/5);
		rightHeadLineRight = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+heightDragonal/5, xDragonal+4*widthDragonal/5, yDragonal+2*heightDragonal/5);
		bottomHeadLineRight = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal+widthDragonal, yDragonal+2*heightDragonal/5);
		leftHeadLineRight = new Line2D.Double(xDragonal+widthDragonal, yDragonal+heightDragonal/5, xDragonal+widthDragonal, yDragonal+2*heightDragonal/5);
			
		eyeLineRight = new Line2D.Double(xDragonal+9*widthDragonal/10, yDragonal+5*heightDragonal/20, xDragonal+9*widthDragonal/10, yDragonal+3*heightDragonal/10);		
			
		topBodyLineRight = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal+widthDragonal/5, yDragonal+2*heightDragonal/5);
		rightBodyLineRight = new Line2D.Double(xDragonal+widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal+widthDragonal/5, yDragonal+7*heightDragonal/10);
		bottomBodyLineRight = new Line2D.Double(xDragonal+widthDragonal/5, yDragonal+7*heightDragonal/10, xDragonal+4*widthDragonal/5, yDragonal+7*heightDragonal/10);
		leftBodyLineRight = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+7*heightDragonal/10, xDragonal+4*widthDragonal/5, yDragonal+2*heightDragonal/5);
		//												3 + 7 = 10											3 + 2 = 5
		leftWingLineRight = new Line2D.Double(xDragonal+7*widthDragonal/10, yDragonal+2*heightDragonal/5, xDragonal+2*widthDragonal/5, yDragonal);
		topWingLineRight = new Line2D.Double(xDragonal+2*widthDragonal/5, yDragonal, xDragonal+1*widthDragonal/5, yDragonal+heightDragonal/5);
		rightWingLineRight = new Line2D.Double(xDragonal+1*widthDragonal/5, yDragonal+heightDragonal/5, xDragonal+3*widthDragonal/5, yDragonal+2*heightDragonal/5);
			
		leftTailLineRight = new Line2D.Double(xDragonal+1*widthDragonal/5, yDragonal+2*heightDragonal/5, xDragonal, yDragonal+3*heightDragonal/10);
		rightTailLineRight = new Line2D.Double(xDragonal, yDragonal+3*heightDragonal/10, xDragonal+1*widthDragonal/5, yDragonal+5*heightDragonal/10);
			
		leftLegOneLineRight = new Line2D.Double(xDragonal+4*widthDragonal/5, yDragonal+7*heightDragonal/10, xDragonal+7*widthDragonal/10, yDragonal+heightDragonal);
		bottomLegOneLineRight = new Line2D.Double(xDragonal+7*widthDragonal/10, yDragonal+heightDragonal, xDragonal+13*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegOneLineRight = new Line2D.Double(xDragonal+13*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+15*widthDragonal/20, yDragonal+7*heightDragonal/10);
			
		leftLegTwoLineRight = new Line2D.Double(xDragonal+13*widthDragonal/20, yDragonal+7*heightDragonal/10, xDragonal+11*widthDragonal/20, yDragonal+heightDragonal);
		bottomLegTwoLineRight = new Line2D.Double(xDragonal+11*widthDragonal/20, yDragonal+heightDragonal, xDragonal+10*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegTwoLineRight = new Line2D.Double(xDragonal+10*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+12*widthDragonal/20, yDragonal+7*heightDragonal/10);
			
		leftLegThreeLineRight = new Line2D.Double(xDragonal+10*widthDragonal/20, yDragonal+7*heightDragonal/10, xDragonal+8*widthDragonal/20, yDragonal+heightDragonal);
		bottomLegThreeLineRight = new Line2D.Double(xDragonal+8*widthDragonal/20, yDragonal+heightDragonal, xDragonal+7*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegThreeLineRight = new Line2D.Double(xDragonal+7*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+9*widthDragonal/20, yDragonal+7*heightDragonal/10);
			
		leftLegFourLineRight = new Line2D.Double(xDragonal+7*widthDragonal/20, yDragonal+7*heightDragonal/10, xDragonal+5*widthDragonal/20, yDragonal+heightDragonal);
		bottomLegFourLineRight = new Line2D.Double(xDragonal+5*widthDragonal/20, yDragonal+heightDragonal, xDragonal+4*widthDragonal/20, yDragonal+9*heightDragonal/10);
		rightLegFourLineRight = new Line2D.Double(xDragonal+4*widthDragonal/20, yDragonal+9*heightDragonal/10, xDragonal+6*widthDragonal/20, yDragonal+7*heightDragonal/10);
	}
	
	/**
	 * Draws only the left- or right-side of the Dragonal, even though the positions for both sides
	 * are always on the move();
	 * @param g
	 */
	public void draw(Graphics2D g)
	{
		//	2 hitbox lines below just for testing, don't show when done.
		g.setColor(Color.black);
		g.draw(hitbox);
		g.setColor(Color.red);
		
		if (showLeftSide == true)
		{
			g.draw(topHeadLine);
			g.draw(rightHeadLine);
			g.draw(bottomHeadLine);
			g.draw(leftHeadLine);
		
			g.draw(eyeLine);
		
			g.draw(topBodyLine);
			g.draw(rightBodyLine);
			g.draw(bottomBodyLine);
			g.draw(leftBodyLine);
		
			g.draw(leftWingLine);
			g.draw(topWingLine);
			g.draw(rightWingLine);
		
			g.draw(leftTailLine);
			g.draw(rightTailLine);
		
			g.draw(leftLegOneLine);
			g.draw(bottomLegOneLine);
			g.draw(rightLegOneLine);
		
			g.draw(leftLegTwoLine);
			g.draw(bottomLegTwoLine);
			g.draw(rightLegTwoLine);
		
			g.draw(leftLegThreeLine);
			g.draw(bottomLegThreeLine);
			g.draw(rightLegThreeLine);
		
			g.draw(leftLegFourLine);
			g.draw(bottomLegFourLine);
			g.draw(rightLegFourLine);
		}
		
		//	If it's not left-facing, it's right-facing!
		else
		{
			g.draw(topHeadLineRight);
			g.draw(rightHeadLineRight);
			g.draw(bottomHeadLineRight);
			g.draw(leftHeadLineRight);
			
			g.draw(eyeLineRight);
			
			g.draw(topBodyLineRight);
			g.draw(rightBodyLineRight);
			g.draw(bottomBodyLineRight);
			g.draw(leftBodyLineRight);
			
			g.draw(leftWingLineRight);
			g.draw(topWingLineRight);
			g.draw(rightWingLineRight);
			
			g.draw(leftTailLineRight);
			g.draw(rightTailLineRight);
			
			g.draw(leftLegOneLineRight);
			g.draw(bottomLegOneLineRight);
			g.draw(rightLegOneLineRight);
			
			g.draw(leftLegTwoLineRight);
			g.draw(bottomLegTwoLineRight);
			g.draw(rightLegTwoLineRight);
			
			g.draw(leftLegThreeLineRight);
			g.draw(bottomLegThreeLineRight);
			g.draw(rightLegThreeLineRight);
			
			g.draw(leftLegFourLineRight);
			g.draw(bottomLegFourLineRight);
			g.draw(rightLegFourLineRight);
		}
	}
	
	
	/**
	 * Moves the left- and right-facing Dragonal sides at all times.
	 * @param deltaX
	 * @param deltaY
	 */
	public void move(double deltaX, double deltaY)
	{
		hitbox.setFrame(hitbox.getX()+deltaX, hitbox.getY()+deltaY, hitbox.getWidth(), hitbox.getHeight());
		
		topHeadLine.setLine(topHeadLine.getX1()+deltaX, topHeadLine.getY1()+deltaY, topHeadLine.getX2()+deltaX, topHeadLine.getY2()+deltaY);
		rightHeadLine.setLine(rightHeadLine.getX1()+deltaX, rightHeadLine.getY1()+deltaY, rightHeadLine.getX2()+deltaX, rightHeadLine.getY2()+deltaY);
		bottomHeadLine.setLine(bottomHeadLine.getX1()+deltaX, bottomHeadLine.getY1()+deltaY, bottomHeadLine.getX2()+deltaX, bottomHeadLine.getY2()+deltaY);
		leftHeadLine.setLine(leftHeadLine.getX1()+deltaX, leftHeadLine.getY1()+deltaY, leftHeadLine.getX2()+deltaX, leftHeadLine.getY2()+deltaY);
		
		eyeLine.setLine(eyeLine.getX1()+deltaX, eyeLine.getY1()+deltaY, eyeLine.getX2()+deltaX, eyeLine.getY2()+deltaY);
		
		topBodyLine.setLine(topBodyLine.getX1()+deltaX, topBodyLine.getY1()+deltaY, topBodyLine.getX2()+deltaX, topBodyLine.getY2()+deltaY);
		rightBodyLine.setLine(rightBodyLine.getX1()+deltaX, rightBodyLine.getY1()+deltaY, rightBodyLine.getX2()+deltaX, rightBodyLine.getY2()+deltaY);
		bottomBodyLine.setLine(bottomBodyLine.getX1()+deltaX, bottomBodyLine.getY1()+deltaY, bottomBodyLine.getX2()+deltaX, bottomBodyLine.getY2()+deltaY);
		leftBodyLine.setLine(leftBodyLine.getX1()+deltaX, leftBodyLine.getY1()+deltaY, leftBodyLine.getX2()+deltaX, leftBodyLine.getY2()+deltaY);
		
		leftWingLine.setLine(leftWingLine.getX1()+deltaX, leftWingLine.getY1()+deltaY, leftWingLine.getX2()+deltaX, leftWingLine.getY2()+deltaY);
		topWingLine.setLine(topWingLine.getX1()+deltaX, topWingLine.getY1()+deltaY, topWingLine.getX2()+deltaX, topWingLine.getY2()+deltaY);
		rightWingLine.setLine(rightWingLine.getX1()+deltaX, rightWingLine.getY1()+deltaY, rightWingLine.getX2()+deltaX, rightWingLine.getY2()+deltaY);
		
		leftTailLine.setLine(leftTailLine.getX1()+deltaX, leftTailLine.getY1()+deltaY, leftTailLine.getX2()+deltaX, leftTailLine.getY2()+deltaY);
		rightTailLine.setLine(rightTailLine.getX1()+deltaX, rightTailLine.getY1()+deltaY, rightTailLine.getX2()+deltaX, rightTailLine.getY2()+deltaY);
		
		leftLegOneLine.setLine(leftLegOneLine.getX1()+deltaX, leftLegOneLine.getY1()+deltaY, leftLegOneLine.getX2()+deltaX, leftLegOneLine.getY2()+deltaY);
		bottomLegOneLine.setLine(bottomLegOneLine.getX1()+deltaX, bottomLegOneLine.getY1()+deltaY, bottomLegOneLine.getX2()+deltaX, bottomLegOneLine.getY2()+deltaY);
		rightLegOneLine.setLine(rightLegOneLine.getX1()+deltaX, rightLegOneLine.getY1()+deltaY, rightLegOneLine.getX2()+deltaX, rightLegOneLine.getY2()+deltaY);
		
		leftLegTwoLine.setLine(leftLegTwoLine.getX1()+deltaX, leftLegTwoLine.getY1()+deltaY, leftLegTwoLine.getX2()+deltaX, leftLegTwoLine.getY2()+deltaY);
		bottomLegTwoLine.setLine(bottomLegTwoLine.getX1()+deltaX, bottomLegTwoLine.getY1()+deltaY, bottomLegTwoLine.getX2()+deltaX, bottomLegTwoLine.getY2()+deltaY);
		rightLegTwoLine.setLine(rightLegTwoLine.getX1()+deltaX, rightLegTwoLine.getY1()+deltaY, rightLegTwoLine.getX2()+deltaX, rightLegTwoLine.getY2()+deltaY);
		
		leftLegThreeLine.setLine(leftLegThreeLine.getX1()+deltaX, leftLegThreeLine.getY1()+deltaY, leftLegThreeLine.getX2()+deltaX, leftLegThreeLine.getY2()+deltaY);
		bottomLegThreeLine.setLine(bottomLegThreeLine.getX1()+deltaX, bottomLegThreeLine.getY1()+deltaY, bottomLegThreeLine.getX2()+deltaX, bottomLegThreeLine.getY2()+deltaY);
		rightLegThreeLine.setLine(rightLegThreeLine.getX1()+deltaX, rightLegThreeLine.getY1()+deltaY, rightLegThreeLine.getX2()+deltaX, rightLegThreeLine.getY2()+deltaY);
		
		leftLegFourLine.setLine(leftLegFourLine.getX1()+deltaX, leftLegFourLine.getY1()+deltaY, leftLegFourLine.getX2()+deltaX, leftLegFourLine.getY2()+deltaY);
		bottomLegFourLine.setLine(bottomLegFourLine.getX1()+deltaX, bottomLegFourLine.getY1()+deltaY, bottomLegFourLine.getX2()+deltaX, bottomLegFourLine.getY2()+deltaY);
		rightLegFourLine.setLine(rightLegFourLine.getX1()+deltaX, rightLegFourLine.getY1()+deltaY, rightLegFourLine.getX2()+deltaX, rightLegFourLine.getY2()+deltaY);

			
		topHeadLineRight.setLine(topHeadLineRight.getX1()+deltaX, topHeadLineRight.getY1()+deltaY, topHeadLineRight.getX2()+deltaX, topHeadLineRight.getY2()+deltaY);
		rightHeadLineRight.setLine(rightHeadLineRight.getX1()+deltaX, rightHeadLineRight.getY1()+deltaY, rightHeadLineRight.getX2()+deltaX, rightHeadLineRight.getY2()+deltaY);
		bottomHeadLineRight.setLine(bottomHeadLineRight.getX1()+deltaX, bottomHeadLineRight.getY1()+deltaY, bottomHeadLineRight.getX2()+deltaX, bottomHeadLineRight.getY2()+deltaY);
		leftHeadLineRight.setLine(leftHeadLineRight.getX1()+deltaX, leftHeadLineRight.getY1()+deltaY, leftHeadLineRight.getX2()+deltaX, leftHeadLineRight.getY2()+deltaY);
			
		eyeLineRight.setLine(eyeLineRight.getX1()+deltaX, eyeLineRight.getY1()+deltaY, eyeLineRight.getX2()+deltaX, eyeLineRight.getY2()+deltaY);
			
		topBodyLineRight.setLine(topBodyLineRight.getX1()+deltaX, topBodyLineRight.getY1()+deltaY, topBodyLineRight.getX2()+deltaX, topBodyLineRight.getY2()+deltaY);
		rightBodyLineRight.setLine(rightBodyLineRight.getX1()+deltaX, rightBodyLineRight.getY1()+deltaY, rightBodyLineRight.getX2()+deltaX, rightBodyLineRight.getY2()+deltaY);
		bottomBodyLineRight.setLine(bottomBodyLineRight.getX1()+deltaX, bottomBodyLineRight.getY1()+deltaY, bottomBodyLineRight.getX2()+deltaX, bottomBodyLineRight.getY2()+deltaY);
		leftBodyLineRight.setLine(leftBodyLineRight.getX1()+deltaX, leftBodyLineRight.getY1()+deltaY, leftBodyLineRight.getX2()+deltaX, leftBodyLineRight.getY2()+deltaY);
			
		leftWingLineRight.setLine(leftWingLineRight.getX1()+deltaX, leftWingLineRight.getY1()+deltaY, leftWingLineRight.getX2()+deltaX, leftWingLineRight.getY2()+deltaY);
		topWingLineRight.setLine(topWingLineRight.getX1()+deltaX, topWingLineRight.getY1()+deltaY, topWingLineRight.getX2()+deltaX, topWingLineRight.getY2()+deltaY);
		rightWingLineRight.setLine(rightWingLineRight.getX1()+deltaX, rightWingLineRight.getY1()+deltaY, rightWingLineRight.getX2()+deltaX, rightWingLineRight.getY2()+deltaY);
			
		leftTailLineRight.setLine(leftTailLineRight.getX1()+deltaX, leftTailLineRight.getY1()+deltaY, leftTailLineRight.getX2()+deltaX, leftTailLineRight.getY2()+deltaY);
		rightTailLineRight.setLine(rightTailLineRight.getX1()+deltaX, rightTailLineRight.getY1()+deltaY, rightTailLineRight.getX2()+deltaX, rightTailLineRight.getY2()+deltaY);
			
		leftLegOneLineRight.setLine(leftLegOneLineRight.getX1()+deltaX, leftLegOneLineRight.getY1()+deltaY, leftLegOneLineRight.getX2()+deltaX, leftLegOneLineRight.getY2()+deltaY);
		bottomLegOneLineRight.setLine(bottomLegOneLineRight.getX1()+deltaX, bottomLegOneLineRight.getY1()+deltaY, bottomLegOneLineRight.getX2()+deltaX, bottomLegOneLineRight.getY2()+deltaY);
		rightLegOneLineRight.setLine(rightLegOneLineRight.getX1()+deltaX, rightLegOneLineRight.getY1()+deltaY, rightLegOneLineRight.getX2()+deltaX, rightLegOneLineRight.getY2()+deltaY);
			
		leftLegTwoLineRight.setLine(leftLegTwoLineRight.getX1()+deltaX, leftLegTwoLineRight.getY1()+deltaY, leftLegTwoLineRight.getX2()+deltaX, leftLegTwoLineRight.getY2()+deltaY);
		bottomLegTwoLineRight.setLine(bottomLegTwoLineRight.getX1()+deltaX, bottomLegTwoLineRight.getY1()+deltaY, bottomLegTwoLineRight.getX2()+deltaX, bottomLegTwoLineRight.getY2()+deltaY);
		rightLegTwoLineRight.setLine(rightLegTwoLineRight.getX1()+deltaX, rightLegTwoLineRight.getY1()+deltaY, rightLegTwoLineRight.getX2()+deltaX, rightLegTwoLineRight.getY2()+deltaY);
			
		leftLegThreeLineRight.setLine(leftLegThreeLineRight.getX1()+deltaX, leftLegThreeLineRight.getY1()+deltaY, leftLegThreeLineRight.getX2()+deltaX, leftLegThreeLineRight.getY2()+deltaY);
		bottomLegThreeLineRight.setLine(bottomLegThreeLineRight.getX1()+deltaX, bottomLegThreeLineRight.getY1()+deltaY, bottomLegThreeLineRight.getX2()+deltaX, bottomLegThreeLineRight.getY2()+deltaY);
		rightLegThreeLineRight.setLine(rightLegThreeLineRight.getX1()+deltaX, rightLegThreeLineRight.getY1()+deltaY, rightLegThreeLineRight.getX2()+deltaX, rightLegThreeLineRight.getY2()+deltaY);
			
		leftLegFourLineRight.setLine(leftLegFourLineRight.getX1()+deltaX, leftLegFourLineRight.getY1()+deltaY, leftLegFourLineRight.getX2()+deltaX, leftLegFourLineRight.getY2()+deltaY);
		bottomLegFourLineRight.setLine(bottomLegFourLineRight.getX1()+deltaX, bottomLegFourLineRight.getY1()+deltaY, bottomLegFourLineRight.getX2()+deltaX, bottomLegFourLineRight.getY2()+deltaY);
		rightLegFourLineRight.setLine(rightLegFourLineRight.getX1()+deltaX, rightLegFourLineRight.getY1()+deltaY, rightLegFourLineRight.getX2()+deltaX, rightLegFourLineRight.getY2()+deltaY);
	}
	
	
	
	public double getLeftSide()
	{
		return xDragonal;
	}
	
	public double getRightSide()
	{
		return xDragonal + widthDragonal;
	}
	
	public double getTopSide()
	{
		return yDragonal;
	}
	
	public double getBottomSide()
	{
		return yDragonal + heightDragonal;
	}
	
	
	public void setX(double xDragonal)
	{
		this.xDragonal = xDragonal;
	}
	
	public void setY(double yDragonal)
	{
		this.yDragonal = yDragonal;
	}
	
	public double getX()
	{
		return xDragonal;	
	}
	
	public double getY()
	{
		return yDragonal;
	}
	
	public double getXSpeed()
	{
		return dragonalSpeedX;
	}
	
	public double getYSpeed()
	{
		return dragonalSpeedY;
	}
	
	public void setXSpeed(double dragonalSpeedX)
	{
		this.dragonalSpeedX = dragonalSpeedX;
	}
	
	public void setYSpeed(double dragonalSpeedY)
	{
		this.dragonalSpeedY = dragonalSpeedY;
	}
	
	/**
	 * Special method for Dragonals.
	 * @return
	 */
	public boolean getSide()
	{
		return showLeftSide;
	}
	/**
	 * Gets called by the part in the Panel that checks if the Dragonal is bouncing off 
	 * an edge (changing directions) Switching from moving up to down or down to up doesn't do anything,
	 * but switching from left to right or right to left should flip the side of the Dragonal being shown.
	 */
	public void setSide()
	{
		if (dragonalSpeedX<0 && dragonalSpeedY<0 || dragonalSpeedX<0 && dragonalSpeedY>0)
		{
			showLeftSide = true;
		}
		else
		{
			showLeftSide = false;
		}
	}
}
