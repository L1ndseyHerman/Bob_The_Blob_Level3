import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * Has dif constructors depending on if just 1 line or speech or 2, might make a 3 soon. 
 * DON'T USE THE LETTER CLASS ANYMORE!!
 * @author linds
 *
 */
public class TextBox 
{
	
	private double xBox, yBox, widthBox, heightBox;
	private double triangleSize;
	private Line2D.Double triangleLineUp, triangleLineDown, leftSideTop, leftSideBottom;
	private Line2D.Double topSide, bottomSide, rightSide;
	private String theWords, theWordsTwo;
	
	/**
	 * This constructor is for just one line of Textbox text
	 * @param xBox
	 * @param yBox
	 * @param widthBox
	 * @param heightBox
	 * @param theWords
	 */
	public TextBox(double xBox, double yBox, double widthBox, double heightBox, String theWords)
	{
		this.xBox = xBox;
		this.yBox = yBox; 
		this.widthBox = widthBox;
		this.heightBox = heightBox;
		this.theWords = theWords;
		
		triangleSize = 10;
		
		triangleLineUp = new Line2D.Double(xBox, yBox, xBox+triangleSize, yBox-triangleSize*0.5);
		triangleLineDown = new Line2D.Double(xBox, yBox, xBox+triangleSize, yBox+triangleSize*0.5);
		leftSideBottom = new Line2D.Double(xBox+triangleSize, yBox+triangleSize*0.5, xBox+triangleSize, yBox+triangleSize*0.5+3*heightBox/4);
		leftSideTop = new Line2D.Double(xBox+triangleSize, yBox-triangleSize*0.5, xBox+triangleSize, yBox-triangleSize*0.5-heightBox/4);
		topSide = new Line2D.Double(xBox+triangleSize, yBox-triangleSize*0.5-heightBox/4, xBox+triangleSize+widthBox, yBox-triangleSize*0.5-heightBox/4);
		bottomSide = new Line2D.Double(xBox+triangleSize, yBox+triangleSize*0.5+3*heightBox/4, xBox+triangleSize+widthBox, yBox+triangleSize*0.5+3*heightBox/4);
		rightSide = new Line2D.Double(xBox+triangleSize+widthBox, yBox+triangleSize*0.5+3*heightBox/4, xBox+triangleSize+widthBox, yBox-triangleSize*0.5-heightBox/4);
	}
	
	/**
	 * This constructor is for two lines of text 
	 * @param xBox
	 * @param yBox
	 * @param widthBox
	 * @param heightBox
	 * @param theWords
	 * @param theWordsTwo
	 */
	public TextBox(double xBox, double yBox, double widthBox, double heightBox, String theWords, String theWordsTwo)
	{
		this.xBox = xBox;
		this.yBox = yBox; 
		this.widthBox = widthBox;
		this.heightBox = heightBox;
		this.theWords = theWords;
		this.theWordsTwo = theWordsTwo;
		
		triangleSize = 10;
		
		triangleLineUp = new Line2D.Double(xBox, yBox, xBox+triangleSize, yBox-triangleSize*0.5);
		triangleLineDown = new Line2D.Double(xBox, yBox, xBox+triangleSize, yBox+triangleSize*0.5);
		leftSideBottom = new Line2D.Double(xBox+triangleSize, yBox+triangleSize*0.5, xBox+triangleSize, yBox+triangleSize*0.5+3*heightBox/4);
		leftSideTop = new Line2D.Double(xBox+triangleSize, yBox-triangleSize*0.5, xBox+triangleSize, yBox-triangleSize*0.5-heightBox/4);
		topSide = new Line2D.Double(xBox+triangleSize, yBox-triangleSize*0.5-heightBox/4, xBox+triangleSize+widthBox, yBox-triangleSize*0.5-heightBox/4);
		bottomSide = new Line2D.Double(xBox+triangleSize, yBox+triangleSize*0.5+3*heightBox/4, xBox+triangleSize+widthBox, yBox+triangleSize*0.5+3*heightBox/4);
		rightSide = new Line2D.Double(xBox+triangleSize+widthBox, yBox+triangleSize*0.5+3*heightBox/4, xBox+triangleSize+widthBox, yBox-triangleSize*0.5-heightBox/4);
	}
	
	public double getLeftSide()
	{
		return xBox+triangleSize;
	}
	
	public double getTopSide()
	{
		return yBox-triangleSize*0.5-heightBox/4;
	}
	/**
	 * The only draw method needed. Checks to see what constructor is being used, draws accordingly.
	 * @param g
	 */
	public void draw(Graphics2D g)
	{
		g.setColor(Color.black);
		g.draw(triangleLineUp);
		g.draw(triangleLineDown);
		g.draw(leftSideTop);
		g.draw(leftSideBottom);
		g.draw(topSide);
		g.draw(bottomSide);
		g.draw(rightSide);
		
		g.drawString(theWords, (int)(xBox+triangleSize + 10), (int)(yBox-triangleSize*0.5-heightBox/4 + 20));
		
		if (theWordsTwo != null)
		{
			g.drawString(theWordsTwo, (int)(xBox+triangleSize + 10), (int)(yBox-triangleSize*0.5-heightBox/4 + 40));
		}
	}
}
