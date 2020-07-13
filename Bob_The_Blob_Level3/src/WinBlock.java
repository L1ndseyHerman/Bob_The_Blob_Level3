import java.awt.Color;
import java.awt.Graphics2D;
/**
 * The green Block that lets Bob win the level.
 * @author linds
 *
 */
public class WinBlock extends Block 
{
	public WinBlock(double xBlock, double yBlock)
	{
		super(xBlock, yBlock);
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.green);
		g.fill(theBlock);
		
		g.setColor(Color.black);
		g.draw(theBlock);
	}
}
