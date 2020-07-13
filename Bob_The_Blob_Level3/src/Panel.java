import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;


public class Panel extends JPanel implements ActionListener, KeyListener, MouseInputListener
	{
		//	A Timer that gets called every x milliseconds on the MyPanel
		Timer t = new Timer(5, this);
		
		//	Need two separate booleans for the up arrow key: jumping and falling.
		boolean jumpingUp = false, fallingDown = false, movingLeft = false, movingRight = false;
		
		//	The two ways of ending the game
		boolean beatLevel = false, gameOver = false;
		
		//	Adding the story before the level!
		boolean storyTime = true;
		
		//	A location in the middle-ish of the screen for storyTime.
		Bob bobTheBlob = new Bob(400, 210);
		
		Dragonal onlyDragonal = new Dragonal(870, 430, -3, -3);
		
		//	Will be the gap in both the x- and y-direction between letters.
		double letterGap = 5;
		//	Moving this here now.
		double letterSize = 2;
		
		Block theBlocks[] = new Block[25];
		
		//	30 less than max
		WinBlock theWinBlock = new WinBlock(970, 470);
		
		//	Needs to be one greater than Bob+Block, so 71, not 70 even though 70 sounds right
		double jumpHeight = 71, currentHeight = 460;		

		
		//	For Story Mode
		int clickCounter = 0;
		
		public Panel(int width, int height, Color bgColor)
		{
			//	Every. MyPanel. Ever.
			setPreferredSize(new Dimension(width, height));
			setBackground(bgColor);
			//	IMPT!! PUT IN ALL PROGRAMS TO MAKE THE MOUSE STUFF WORK!!
			this.addMouseListener(this);
			//	ALSO THIS!!
			this.addMouseMotionListener(this);
			//	Starts the Timer
			t.start();
			//	The 3 things below are needed for keyboard input.
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			

			//	Arrays of like Blocks go here.
			//	First Hiding Blocks
			theBlocks[0] = new Block(0, 430);
			theBlocks[1] = new Block(30, 430);
			theBlocks[2] = new Block(60, 430);
			theBlocks[3] = new Block(90, 430);
			theBlocks[4] = new Block(120, 430);
			//	Second Hiding Blocks
			theBlocks[5] = new Block(600, 430);
			theBlocks[6] = new Block(630, 430);
			theBlocks[7] = new Block(660, 430);
			theBlocks[8] = new Block(690, 430);
			theBlocks[9] = new Block(720, 430);
			//	The ones above them
			theBlocks[10] = new Block(750, 360);
			theBlocks[11] = new Block(720, 360);
			theBlocks[12] = new Block(690, 360);
			theBlocks[13] = new Block(660, 360);
			theBlocks[14] = new Block(630, 360);
			//	Whoops, and the wall on the right to prevent just going to the WinBlock
			theBlocks[15] = new Block(790, 470);
			theBlocks[16] = new Block(790, 440);
			theBlocks[17] = new Block(790, 410);
			theBlocks[18] = new Block(790, 380);
			//	Jump on this
			theBlocks[19] = new Block(560, 330);
			//	Wall
			theBlocks[20] = new Block(660, 330);
			//	Jump on
			theBlocks[21] = new Block(660, 300);
			//	Wall
			theBlocks[22] = new Block(690, 330);
			theBlocks[23] = new Block(690, 300);
			//	Jump on
			theBlocks[24] = new Block(690, 270);

		}
		/**
		 * 4 drawing possibilities: storyTime, gameOver, beatLevel, or none (regular level mode).
		 * storyTime may have multiple scenes that change when you click the mouse a specific
		 * number of times. 
		 */
		protected void paintComponent(Graphics arg0)
		{
			super.paintComponent(arg0);
			Graphics2D g = (Graphics2D)arg0;
			if (storyTime == true)
			{
				if (clickCounter==0)
				{
					//	If statements for clickCounter go here.
					bobTheBlob.setEmotion("meh");
					bobTheBlob.draw(g);
					
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 350, 40, "Great. Not only am I still not home,", "but the most fearsome enemy of all is up ahead: a Dragonal.");
					bobSpeechBubble.draw(g);
				}
				else if (clickCounter==1)
				{
					bobTheBlob.setEmotion("neutralHappy");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox(bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 270, 40, "It's a good thing my words look better,", "because you'll want to hear what I have to say!");
					bobSpeechBubble.draw(g);
				}
				else if (clickCounter==2)
				{
					bobTheBlob.setEmotion("surprised");
					bobTheBlob.draw(g);
					//	NEW NEW NEW !!!!!!!!!!!!!!!!!!!!!!!!!!!	
					onlyDragonal.draw(g);
					TextBox bobSpeechBubble = new TextBox(bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 290, 40, "Dragonals are dragons made out of quadrilaterals", "that only move diagonally.");
					bobSpeechBubble.draw(g);
				}
				else if (clickCounter==3)
				{
					bobTheBlob.setEmotion("neutralHappy");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox(bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 240, 40, "They move really quickly,", "but at least they can't go through Blocks!");
					bobSpeechBubble.draw(g);
				}
			}
			
			
			if (beatLevel == false && gameOver == false && storyTime == false)
			{
				//	Draw the level until gameOver or beatLevel.
				bobTheBlob.setEmotion("neutralHappy");

				for (int index=0; index<theBlocks.length; index++)
				{
					theBlocks[index].draw(g);
				}
				
				theWinBlock.draw(g);
				bobTheBlob.draw(g);
				onlyDragonal.draw(g);
			}
			if (beatLevel == true)
			{
				//	1 is for setting the x and y-values, 2 is for the move method that works differently.
				//	This somehow centers Bob in the middle of the screen if you beat the level, I forget what I was doing here.
				double xChange1, xChange2;
				double yChange1, yChange2;
				//	These if statements check if Bob is to the left or right of where I want him to be (x==400).
				if (bobTheBlob.getX()>400)
				{
					xChange1 = bobTheBlob.getX()-(bobTheBlob.getX()-400);
					xChange2 = 400-bobTheBlob.getX();
				}
				else 
				{
					xChange1 = bobTheBlob.getX()+400-bobTheBlob.getX();
					xChange2 = 400-bobTheBlob.getX();
				}
				//	These check if he's above or below y==210
				if (bobTheBlob.getY()>210)
				{
					yChange1 = bobTheBlob.getY()-(bobTheBlob.getY()-210);
					yChange2 = 210-bobTheBlob.getY();
				}
				else 
				{
					yChange1 = bobTheBlob.getY()+210-bobTheBlob.getY();
					yChange2 = 210-bobTheBlob.getY();
				}
				bobTheBlob.setX(xChange1);
				bobTheBlob.setY(yChange1);
				bobTheBlob.move(xChange2, yChange2);
				
				
				bobTheBlob.setEmotion("animeEcstatic");
				bobTheBlob.draw(g);
				TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 200, 20, "You win bonus confusing stuff!");
				bobSpeechBubble.draw(g);
			}
			if (gameOver == true)
			{
				//	1 is for setting the x and y-values, 2 is for the move method that works differently.
				//	This somehow centers Bob in the middle of the screen if you get a gameOver, I forget what I was doing here.
				double xChange1, xChange2;
				double yChange1, yChange2;
				//	These if statements check if Bob is to the left or right of where I want him to be (x==400).
				if (bobTheBlob.getX()>400)
				{
					xChange1 = bobTheBlob.getX()-(bobTheBlob.getX()-400);
					xChange2 = 400-bobTheBlob.getX();
				}
				else 
				{
					xChange1 = bobTheBlob.getX()+400-bobTheBlob.getX();
					xChange2 = 400-bobTheBlob.getX();
				}
				//	These check if he's above or below y==210
				if (bobTheBlob.getY()>210)
				{
					yChange1 = bobTheBlob.getY()-(bobTheBlob.getY()-210);
					yChange2 = 210-bobTheBlob.getY();
				}
				else 
				{
					yChange1 = bobTheBlob.getY()+210-bobTheBlob.getY();
					yChange2 = 210-bobTheBlob.getY();
				}
				bobTheBlob.setX(xChange1);
				bobTheBlob.setY(yChange1);
				bobTheBlob.move(xChange2, yChange2);
				
				bobTheBlob.setEmotion("surprised");
				bobTheBlob.draw(g);
				TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 90, 20, "Game Over!");
				bobSpeechBubble.draw(g);
			}
		}
		
		@Override
		/**
		 * The method that gets called whenever the Timer goes off. 
		 * Only does stuff when in regular level mode.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{ 
			
			
			if (storyTime == false && gameOver == false && beatLevel == false)
			{
				moveEnemies();
			
			
				checkEnemies();
			
			
				checkWinBlock();
			
			
				repaint();
			
			
				checkBlocksUp();
			

				checkBlocksLeft();
			
			
				checkBlocksRight();
			}
			
		}
		

		/**
		 * This method only moves the enemies. The actual checking of whether or not Bob
		 * is touching the enemy happens in the checkEnemies().
		 */
		public void moveEnemies()
		{
			onlyDragonal.move(onlyDragonal.getXSpeed(), onlyDragonal.getYSpeed());
			onlyDragonal.setX(onlyDragonal.getX() + onlyDragonal.getXSpeed());
			onlyDragonal.setY(onlyDragonal.getY() + onlyDragonal.getYSpeed());
			
			//	Checks if the Dragonal should bounce off the Panel walls in the x-direction
			if (onlyDragonal.getRightSide() + onlyDragonal.getXSpeed() >= 1000 || onlyDragonal.getLeftSide() + onlyDragonal.getXSpeed() <= 0)
			{
				onlyDragonal.setXSpeed(onlyDragonal.getXSpeed() * -1);
				onlyDragonal.setSide();
			}
			//	Panel walls y-direction
			if (onlyDragonal.getBottomSide() + onlyDragonal.getYSpeed() >= 500 || onlyDragonal.getTopSide() + onlyDragonal.getYSpeed() <= 0)
			{
				onlyDragonal.setYSpeed(onlyDragonal.getYSpeed() * -1);
				onlyDragonal.setSide();
			}
			
			
			//	Now for the Block bounces:
			for (int index=0; index<theBlocks.length; index++)
			{
				//	Change from like <= to < for direction they aren't switching so they don't overlap
				//	(like < top for lr).
				
				if ((onlyDragonal.getRightSide()+onlyDragonal.getXSpeed()>=theBlocks[index].getLeftSide() && onlyDragonal.getRightSide()+onlyDragonal.getXSpeed()<=theBlocks[index].getRightSide() && onlyDragonal.getBottomSide()+onlyDragonal.getYSpeed()>theBlocks[index].getTopSide() && onlyDragonal.getTopSide()+onlyDragonal.getYSpeed()<theBlocks[index].getBottomSide()) || (onlyDragonal.getLeftSide()+onlyDragonal.getXSpeed()<=theBlocks[index].getRightSide() && onlyDragonal.getLeftSide()+onlyDragonal.getXSpeed()>=theBlocks[index].getLeftSide() && onlyDragonal.getBottomSide()+onlyDragonal.getYSpeed()>theBlocks[index].getTopSide() && onlyDragonal.getTopSide()+onlyDragonal.getYSpeed()<theBlocks[index].getBottomSide()))
				{
					//System.out.println("Hit block left or right");
					onlyDragonal.setXSpeed(onlyDragonal.getXSpeed() * -1);
					onlyDragonal.setSide();
				}


				if ((onlyDragonal.getTopSide()+onlyDragonal.getYSpeed()<=theBlocks[index].getBottomSide() && onlyDragonal.getTopSide()+onlyDragonal.getYSpeed()>=theBlocks[index].getTopSide() && onlyDragonal.getLeftSide()+onlyDragonal.getXSpeed()<theBlocks[index].getRightSide() && onlyDragonal.getRightSide()+onlyDragonal.getXSpeed()>theBlocks[index].getLeftSide()) || (onlyDragonal.getBottomSide()+onlyDragonal.getYSpeed()>=theBlocks[index].getTopSide() && onlyDragonal.getBottomSide()+onlyDragonal.getYSpeed()<=theBlocks[index].getBottomSide() && onlyDragonal.getLeftSide()+onlyDragonal.getXSpeed()<theBlocks[index].getRightSide() && onlyDragonal.getRightSide()+onlyDragonal.getXSpeed()>theBlocks[index].getLeftSide()))
				{
					//System.out.println("Hit block bottom or top ");
					onlyDragonal.setYSpeed(onlyDragonal.getYSpeed() * -1);
					onlyDragonal.setSide();
				}
			}
		}
		
		
		@Override
		/**
		 * For moving Bob with the arrow keys.
		 */
		public void keyPressed(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			int code = arg0.getKeyCode();
			//	Starts the boolean jump up. Can't be stopped once key pressed.
			if (code == KeyEvent.VK_UP)
			{
				if (fallingDown == false && jumpingUp == false)
				{
					jumpingUp = true;
					currentHeight = bobTheBlob.getY();
				}
			}
			//	Bob moves only while you hold down left and right keys.
			if (code == KeyEvent.VK_LEFT)
			{
				movingLeft = true;
			}
			if (code == KeyEvent.VK_RIGHT)
			{
				movingRight = true;
			}
		}

		@Override
		/**
		 * For stopping L/R movement of Bob when you let go of those arrow keys.
		 */
		public void keyReleased(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			int code = arg0.getKeyCode();
			if (code == KeyEvent.VK_LEFT)
			{
				movingLeft = false;
			}
			if (code == KeyEvent.VK_RIGHT)
			{
				movingRight = false;
			}
		}

		@Override
		/**
		 * Required to be there. Doesn't do anything. 
		 */
		public void keyTyped(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}


		/**
		 * Can Bob move up/down, or is there a Block in his way?
		 */
		public void checkBlocksUp()
		{
			//	He also needs to stop jumping up if he's reached max jump height.
			if (jumpingUp == true && bobTheBlob.getTopSide() > currentHeight-jumpHeight)
			{
				//	Checks to see if there's a Block above Bob in his way. If there is, he will fall down when he hits it
				//	even if not at max jumpHeight.
				boolean blockInWay = false;
				for(int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getRightSide() > theBlocks[index].getLeftSide() && bobTheBlob.getLeftSide() < theBlocks[index].getRightSide() && bobTheBlob.getTopSide() <= theBlocks[index].getBottomSide() && bobTheBlob.getTopSide() > theBlocks[index].getTopSide())
					{
						blockInWay = true;
					}
				}
				if (blockInWay == false)
				{
					bobTheBlob.setY(bobTheBlob.getY() - 1);
					bobTheBlob.move(0, -1);
					repaint();
				}
				//	Different than checking other directions, because checks to see if Bob at max jumpHeight too.
				if (blockInWay == true || bobTheBlob.getTopSide() == currentHeight-jumpHeight)
				{
					jumpingUp = false;
					fallingDown = true;
				}
			}
			if (fallingDown == true)
			{
				//	Checks to see if a Block below Bob in his way.
				boolean blockInWay = false;
				for (int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getBottomSide() == theBlocks[index].getTopSide() && bobTheBlob.getRightSide() > theBlocks[index].getLeftSide() && bobTheBlob.getLeftSide() < theBlocks[index].getRightSide())
					{
						blockInWay = true;
					}
				}
				if (blockInWay == false && bobTheBlob.getTopSide() < 460)
				{
					bobTheBlob.setY(bobTheBlob.getY() + 1);
					bobTheBlob.move(0, 1);
					repaint();
				}
				//	Causes Bob to stop falling down if either a Block or the hard-coded bottom y-value of the Panel (the ground)
				//	is right below him.
				if (blockInWay == true || bobTheBlob.getTopSide() == 460)
				{
					fallingDown = false;
				}
			}
			//	BOB IS BIGGER THAN THE BLOCKS!! ALL OF BOB WON'T FIT ON ONE, JUST MAKE SURE THE MIDDLE (X + 1/2DIAMETER) IS BETW BLOCK X-VALS INSTEAD!!!!!!!!!!
			//	Actually, doing if Bob is at all touching the block, but his square is bigger than circle, looks to not even be on block but is.
		}
		/**
		 * Can Bob move right, or is there a Block in his way?
		 */
		public void checkBlocksRight()
		{
			//	1000 - 40 - 1 = 959
			if (bobTheBlob.getLeftSide() <= 959 && movingRight == true)
			{
				//	A variable to see if any blocks are in the way. 
				boolean blockInWay = false;
				for(int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getRightSide() == theBlocks[index].getLeftSide() && bobTheBlob.getBottomSide() > theBlocks[index].getTopSide() && bobTheBlob.getTopSide() < theBlocks[index].getBottomSide())
					{
						blockInWay = true;
					}
				}

				if (blockInWay == false)
				{
					//	Always moves right if no Block in the way
					bobTheBlob.setX(bobTheBlob.getX() + 1);
					bobTheBlob.move(1, 0);
					//	If Bob is on top of a Block and moves off the right edge, this causes him to fall down instead of 
					//	hovering in mid-air.
					for (int index=0; index<theBlocks.length; index++)
					{
						//	CHANGED FROM > TO >=
						//	Don't want that "Walking on Sunshine" glitch :S
						if (bobTheBlob.getLeftSide() >= theBlocks[index].getRightSide() && bobTheBlob.getBottomSide() == theBlocks[index].getTopSide() && jumpingUp == false && fallingDown == false)
						{
							fallingDown = true;
						}
						repaint();
					}
				}
			}
		}
		/**
		 * Can Bob move left, or is there a Block in his way?
		 */
		public void checkBlocksLeft()
		{
			if (bobTheBlob.getLeftSide() >= 1 && movingLeft == true)
			{
				//	There is a separate blockInWay variable for each direction, should reset each method call.
				boolean blockInWay = false;
				//	Checks for blockInWay
				for(int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getLeftSide() == theBlocks[index].getRightSide() && bobTheBlob.getBottomSide() > theBlocks[index].getTopSide() && bobTheBlob.getTopSide() < theBlocks[index].getBottomSide())
					{
						blockInWay = true;
					}
				}

				if (blockInWay == false)
				{
					//	Move left if not
					bobTheBlob.setX(bobTheBlob.getX() - 1);
					bobTheBlob.move(-1, 0);
					//	Fall if past edge of Block
					for (int index=0; index<theBlocks.length; index++)
					{
						//	CHANGED FROM < TO <= 
						if (bobTheBlob.getRightSide() <= theBlocks[index].getLeftSide() && bobTheBlob.getBottomSide() == theBlocks[index].getTopSide() && jumpingUp == false && fallingDown == false) 
						{
							fallingDown = true;
						}
						repaint();
					}
				}
			}
		}
		
		
		
		/**
		 * If Bob's coordinates overlap with those of an enemy, gameOver = true;
		 */
		public void checkEnemies()
		{
			if (beatLevel == false)
			{
				if (bobTheBlob.getRightSide() >= onlyDragonal.getLeftSide() && bobTheBlob.getLeftSide() <= onlyDragonal.getRightSide() && bobTheBlob.getTopSide() <= onlyDragonal.getBottomSide() && bobTheBlob.getBottomSide() >= onlyDragonal.getTopSide())
				{
					gameOver = true;
				}
			}
		}
		
		
		/**
		 * If Bob's coordinates overlap with those of the WinBlock, beatLevel = true;
		 */
		public void checkWinBlock()
		{
			if (bobTheBlob.getRightSide() >= theWinBlock.getLeftSide() && bobTheBlob.getLeftSide() <= theWinBlock.getRightSide() && bobTheBlob.getTopSide() <= theWinBlock.getBottomSide() && bobTheBlob.getBottomSide() >= theWinBlock.getTopSide())
			{
				beatLevel = true;
			}
		}

		@Override
		/**
		 * Does nothing
		 */
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub

		}

		@Override
		/**
		 * Does nothing
		 */
		public void mouseEntered(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		/**
		 * Does nothing
		 */
		public void mouseExited(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		/**
		 * Keeps track of clicks for storyTime. 
		 * When storyTime = false; moves Bob to his starting position and starts the game. 
		 */
		public void mousePressed(MouseEvent e) 
		{
			clickCounter++;
			//	REALLY IMPT REPAINT()!!!!!!!!!!!!!
			repaint();
			
			if (storyTime == true && clickCounter==4)
			{
				bobTheBlob.setX(0);
				bobTheBlob.setY(460);
				//	Going from 400 to 0 in x-direction and 210 to 460 in y-direction
				//	500 - 40 == 460.
				bobTheBlob.move(-400, 250);
				storyTime = false;
			}
		}

		@Override
		/**
		 * Does nothing
		 */
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		/**
		 * Does nothing
		 */
		public void mouseDragged(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		/**
		 * Does nothing
		 */
		public void mouseMoved(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

			
	}



